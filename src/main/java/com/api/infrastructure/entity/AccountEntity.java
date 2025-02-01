package com.api.infrastructure.entity;

import com.api.infrastructure.constants.DefaultSchema;
import jakarta.persistence.*;

@Entity
@Table(name="account", schema = DefaultSchema.name)
public class AccountEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="number", length=10, nullable=false, unique=true)
    private String number;

    @Column(name="balance", nullable=false)
    private Float balance;

    @Column(name="account_holder_id", nullable=false, unique=true)
    private AccountHolderEntity accountId;

    @Column(name="is_active", nullable=false)
    private boolean isActive;

    @Column(name="is_unblocked", nullable=false)
    private boolean isUnblocked;

    @Column(name="daily_limit", nullable=false)
    private Float dailyLimit;
}
