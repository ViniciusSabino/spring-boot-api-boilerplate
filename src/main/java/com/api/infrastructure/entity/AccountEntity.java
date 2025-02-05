package com.api.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name="account")
public class AccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private AgencyEntity agency;

    @OneToOne
    @JoinColumn(name = "account_holder_id", nullable = false, unique = true)
    private AccountHolderEntity accountHolder;

    @Column(name="number", length=30, nullable=false, unique=true)
    private String number;

    @Column(name="balance", nullable=false)
    private Float balance;

    @Column(name="is_active", nullable=false)
    private boolean isActive;

    @Column(name="is_unblocked", nullable=false)
    private boolean isUnblocked;

    @Column(name="daily_limit", nullable=false)
    private Float dailyLimit;

    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @Column(name="updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updatedAt;
}
