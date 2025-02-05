package com.api.infrastructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name="transactions")
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name="transaction_type_id", nullable=false)
    private TransactionTypeEntity transactionType;

    @Column(name="\"value\"", nullable=false)
    private Float value;

    @Column(name="date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant date;
}
