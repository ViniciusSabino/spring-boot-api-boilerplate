package com.api.application.usecase.impl;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.dto.request.ExtractRequestDTO;
import com.api.application.service.AccountService;
import com.api.application.service.ExtractService;
import com.api.application.usecase.AccountUseCase;
import com.api.domain.enums.ErrorTypeEnum;
import com.api.domain.enums.TransactionTypeEnum;
import com.api.domain.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountUseCaseImpl implements AccountUseCase {
    private final AccountService service;
    private final ExtractService extractService;

    @Override
    public AccountDTO createAccount(AccountRequestDTO accountRequest) throws AccountException {
        return service.createAccount(accountRequest);
    }

    @Override
    public AccountBalanceDTO getAccountBalance(String number) throws AccountException {
        return service.getAccountBalance(number);
    }

    @Override
    public void activateAccount(String number) throws AccountException {
        service.activateAccount(number);
    }

    @Override
    public void inactivateAccount(String number) throws AccountException {
        service.inactivateAccount(number);
    }

    @Override
    public void unblockedAccount(String number) throws AccountException {
        service.unblockedAccount(number);
    }

    @Override
    public void blockedAccount(String number) throws AccountException {
        service.blockedAccount(number);
    }

    @Override
    public void transaction(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException {
        TransactionTypeEnum transactionType;

        try {
            transactionType = TransactionTypeEnum.valueOf(accountTransactionRequest.type());

        }catch (Exception e) {
            throw new AccountException("Invalid transaction type", HttpStatus.BAD_REQUEST, ErrorTypeEnum.INVALID_TRANSACTION_TYPE);
        }

        if(transactionType == TransactionTypeEnum.DEPOSIT) {
            service.deposit(number, accountTransactionRequest);
        }

        if(transactionType == TransactionTypeEnum.OUTPUT) {
            service.withdraw(number, accountTransactionRequest);
        }
    }

    @Override
    public List<TransactionDTO> extract(String number, ExtractRequestDTO extractRequest) {

        return extractService.extractPerPeriod(number, extractRequest.startDate(), extractRequest.endDate());
    }
}