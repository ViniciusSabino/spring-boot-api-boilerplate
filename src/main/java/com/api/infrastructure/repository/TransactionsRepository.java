package com.api.infrastructure.repository;

import com.api.domain.enums.TransactionTypeEnum;
import com.api.infrastructure.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionsRepository  extends JpaRepository<TransactionsEntity, Long> {

    @Query("SELECT t from TransactionsEntity t WHERE t.date >= ?2 AND t.date <= ?3 AND t.transactionType.id = ?4 AND t.account.id = ?1")
    List<TransactionsEntity> findTransactionsByPeriodAndTypeId(Long accountId, Instant startDate, Instant endDate, Long transactionTypeId);

    @Query("SELECT t from TransactionsEntity t WHERE t.date >= ?2 AND t.date <= ?3 AND t.account.id = ?1 ORDER BY t.date DESC")
    List<TransactionsEntity> findTransactionsByPeriod(Long accountId, Instant startDate, Instant endDate);
}
