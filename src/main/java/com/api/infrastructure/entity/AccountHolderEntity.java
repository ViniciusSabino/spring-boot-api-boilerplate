package com.api.infrastructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name="account_holder")
@Data
public class AccountHolderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="document_number", length=14, nullable=false, unique=true)
    private String documentNumber;

    @Transient
    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @Transient
    @Column(name="updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updatedAt;
}
