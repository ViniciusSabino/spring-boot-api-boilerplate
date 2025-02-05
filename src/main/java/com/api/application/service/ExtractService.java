package com.api.application.service;

import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.infrastructure.entity.AccountEntity;

import java.time.Instant;
import java.util.List;

public interface ExtractService {
    void registerTransaction(AccountTransactionRequestDTO accountTransactionRequest, AccountEntity account);

    List<TransactionDTO> withdrawalsOfTheDay(Long accountId);

    List<TransactionDTO> extractPerPeriod(String number, String startDate, String endDate);

    Float sumValueOfTransactions(List<TransactionDTO> transactions);
}
