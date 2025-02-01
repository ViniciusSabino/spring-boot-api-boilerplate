package com.api.infrastructure.entity;


import com.api.infrastructure.constants.DefaultSchema;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="release", schema = DefaultSchema.name)
public class ReleaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="release_type_id", nullable=false)
    private ReleaseTypeEntity releaseType;

    @Column(name="value", nullable=false)
    private Float value;

    @Column(name="date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant date;

    @Column(name="account_id", nullable=false)
    private AccountEntity account;
}
