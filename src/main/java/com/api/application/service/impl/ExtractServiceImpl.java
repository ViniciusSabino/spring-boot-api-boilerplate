package com.api.application.service.impl;

import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.service.ExtractService;
import com.api.application.utils.MapperUtils;
import com.api.domain.enums.TransactionTypeEnum;
import com.api.infrastructure.entity.AccountEntity;
import com.api.infrastructure.entity.TransactionTypeEntity;
import com.api.infrastructure.entity.TransactionsEntity;
import com.api.infrastructure.repository.AccountRepository;
import com.api.infrastructure.repository.TransactionTypeRepository;
import com.api.infrastructure.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ExtractServiceImpl implements ExtractService {
    private final TransactionsRepository transactionsRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final AccountRepository accountRepository;

    @Override
    public void registerTransaction(AccountTransactionRequestDTO accountTransactionRequest, AccountEntity account) {
        TransactionsEntity transactionsEntity = new TransactionsEntity();

        TransactionTypeEntity transactionType = transactionTypeRepository.findByType(accountTransactionRequest.type());

        transactionsEntity.setTransactionType(transactionType);
        transactionsEntity.setValue(accountTransactionRequest.value());
        transactionsEntity.setAccount(account);
        transactionsEntity.setDate(Instant.now());

        transactionsRepository.save(transactionsEntity);
    }

    @Override
    public List<TransactionDTO> withdrawalsOfTheDay(Long accountId) {
        ZoneId userTimeZone = ZoneId.of("America/Sao_Paulo");

        LocalDate currentDate = LocalDate.now();

        Instant startDate = currentDate.atStartOfDay().atZone(userTimeZone).toInstant();
        Instant endDate = currentDate.atTime(23, 59, 59).atZone(userTimeZone).toInstant();

        TransactionTypeEntity transactionType = transactionTypeRepository.findByType(String.valueOf(TransactionTypeEnum.OUTPUT));

        List<TransactionsEntity> transactions = transactionsRepository.findTransactionsByPeriodAndTypeId(accountId, startDate, endDate, transactionType.getId());

        return transactions.stream().map(MapperUtils::mapTransactionEntityToDTO).toList();
    }

    @Override
    public List<TransactionDTO> extractPerPeriod(String number, String startDate, String endDate) {
        ZoneId userTimeZone = ZoneId.of("America/Sao_Paulo");

        AccountEntity account = accountRepository.findByNumber(number);

        Instant instantStartDate = Instant.parse(startDate).atZone(userTimeZone).toInstant();
        Instant instantEndDate = Instant.parse(endDate).atZone(userTimeZone).toInstant();

        List<TransactionsEntity> transactions = transactionsRepository.findTransactionsByPeriod(account.getId(), instantStartDate, instantEndDate);

        return transactions.stream().map(MapperUtils::mapTransactionEntityToDTO).toList();
    }

    @Override
    public Float sumValueOfTransactions(List<TransactionDTO> transactions) {
        AtomicReference<Float> sum = new AtomicReference<>(0F);

        transactions.forEach(transaction -> {
            sum.updateAndGet(v -> v + transaction.getValue());
        });

        return sum.get();
    }
}
