package com.api.application.service.impl;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.service.AccountService;
import com.api.application.service.ExtractService;
import com.api.application.utils.MapperUtils;
import com.api.domain.enums.ErrorTypeEnum;
import com.api.domain.exception.AccountException;
import com.api.domain.utils.FieldUtil;
import com.api.infrastructure.entity.AccountEntity;
import com.api.infrastructure.entity.AccountHolderEntity;
import com.api.infrastructure.entity.AgencyEntity;
import com.api.infrastructure.repository.AccountHolderRepository;
import com.api.infrastructure.repository.AccountRepository;
import com.api.infrastructure.repository.AgencyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountHolderRepository accountHolderRepository;
    private final AgencyRepository agencyRepository;

    private final ExtractService extractService;

    @Override
    public AccountDTO createAccount(AccountRequestDTO accountRequest) throws AccountException {

        if (Objects.nonNull(repository.findByNumber(accountRequest.number()))) {
            throw new AccountException("Account number already exists", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_NUMBER_ALREADY_EXISTS);
        }

        String documentNumber = FieldUtil.cleanDocumentNumber(accountRequest.accountHolderDocumentNumber());

        AccountHolderEntity accountHolder = accountHolderRepository.findByDocumentNumber(documentNumber);

        if(Objects.isNull(accountHolder)) {
            throw new AccountException("Account holder not found", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_HOLDER_NOT_FOUND);
        }

        if (Objects.nonNull(repository.findByAccountHolderId(accountHolder.getId()))) {
            throw new AccountException("Account holder as an account", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_HOLDER_HAS_ACCOUNT);
        }

        AgencyEntity agency = agencyRepository.findByNumber(accountRequest.agencyNumber());

        if (Objects.isNull(agency)) {
            throw new AccountException("Agency not Found", HttpStatus.BAD_REQUEST, ErrorTypeEnum.AGENCY_NOT_FOUND);
        }

        AccountEntity entity = new AccountEntity();

        entity.setAgency(agency);
        entity.setAccountHolder(accountHolder);
        entity.setNumber(accountRequest.number());
        entity.setBalance(0.0F);
        entity.setActive(false);
        entity.setDailyLimit(2000F);


        ZoneId userTimeZone = ZoneId.of("America/Sao_Paulo");

        entity.setCreatedAt(Instant.now().atZone(userTimeZone).toInstant());
        entity.setUpdatedAt(Instant.now().atZone(userTimeZone).toInstant());

        AccountEntity savedAccount = repository.save(entity);

        AccountDTO account = MapperUtils.mapAccountEntityToDTO(savedAccount);

        account.setAgencyNumber(accountRequest.agencyNumber());
        account.setAccountHolderDocumentNumber(accountRequest.accountHolderDocumentNumber());


        return account;
    }

    @Override
    public AccountBalanceDTO getAccountBalance(String number) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(Objects.isNull(accountEntity)) {
            throw new AccountException("Account not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_NOT_FOUND);
        }

        Optional<AccountHolderEntity> accountHolder = accountHolderRepository.findById(accountEntity.getAccountHolder().getId());
        Optional<AgencyEntity> agency = agencyRepository.findById(accountEntity.getAgency().getId());

        AccountDTO account = MapperUtils.mapAccountEntityToDTO(accountEntity);

        account.setAgencyNumber(agency.map(AgencyEntity::getNumber).orElse(null));
        account.setAccountHolderDocumentNumber(accountHolder.map(AccountHolderEntity::getDocumentNumber).orElse(null));

        AccountBalanceDTO balance = new AccountBalanceDTO();

        balance.setAccount(account);
        balance.setAccountHolder(MapperUtils.mapAccountHolderEntityToDTO(accountHolder.orElse(null)));

        return balance;
    }

    @Override
    public void activateAccount(String number) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(Objects.isNull(accountEntity)) {
            throw new AccountException("Account is not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_NOT_FOUND);
        }

        accountEntity.setActive(true);
        accountEntity.setUpdatedAt(Instant.now());

        repository.save(accountEntity);
    }

    @Override
    public void inactivateAccount(String number) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(Objects.isNull(accountEntity)) {
            throw new AccountException("Account is not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_NOT_FOUND);
        }

        accountEntity.setActive(false);
        accountEntity.setUpdatedAt(Instant.now());

        repository.save(accountEntity);
    }

    @Override
    public void unblockedAccount(String number) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(Objects.isNull(accountEntity)) {
            throw new AccountException("Account is not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_NOT_FOUND);
        }

        accountEntity.setUnblocked(true);
        accountEntity.setUpdatedAt(Instant.now());

        repository.save(accountEntity);
    }

    @Override
    public void blockedAccount(String number) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(Objects.isNull(accountEntity)) {
            throw new AccountException("Account is not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_NOT_FOUND);
        }

        accountEntity.setUnblocked(false);
        accountEntity.setUpdatedAt(Instant.now());

        repository.save(accountEntity);
    }

    @Override
    public void deposit(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(!accountEntity.isActive() || !accountEntity.isUnblocked()) {
            throw new AccountException("Account is not active", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_IS_NOT_ACTIVE);
        }

        float currentBalance = accountEntity.getBalance();

        accountEntity.setBalance(currentBalance + accountTransactionRequest.value());

        repository.save(accountEntity);

        extractService.registerTransaction(accountTransactionRequest, accountEntity);
    }

    @Override
    public void withdraw(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException {
        AccountEntity accountEntity = repository.findByNumber(number);

        if(!accountEntity.isActive() || !accountEntity.isUnblocked()) {
            throw new AccountException("Account is not active", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_IS_NOT_ACTIVE);
        }

        float currentBalance = accountEntity.getBalance();

        float updatedValue = currentBalance - accountTransactionRequest.value();

        if(updatedValue < 0) {
            throw new AccountException("Unavailable balance", HttpStatus.BAD_REQUEST, ErrorTypeEnum.UNAVAILABLE_BALANCE);
        }

        List<TransactionDTO> withdrawalsOfTheDay = extractService.withdrawalsOfTheDay(accountEntity.getId());

        Float amountWithDrawn = extractService.sumValueOfTransactions(withdrawalsOfTheDay);

        if((amountWithDrawn + accountTransactionRequest.value()) > accountEntity.getDailyLimit()) {
            throw new AccountException("Daily Limit Exceeded", HttpStatus.BAD_REQUEST, ErrorTypeEnum.DAILY_LIMIT_EXCEEDED);
        }

        accountEntity.setBalance(updatedValue);

        extractService.registerTransaction(accountTransactionRequest, accountEntity);
    }
}