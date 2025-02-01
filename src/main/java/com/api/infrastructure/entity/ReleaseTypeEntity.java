package com.api.infrastructure.entity;

import com.api.domain.enums.ReleaseTypeEnum;
import com.api.infrastructure.constants.DefaultSchema;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="release_type", schema = DefaultSchema.name)
public class ReleaseTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="type", nullable=false, unique = true)
    @Enumerated(EnumType.STRING)
    private ReleaseTypeEnum type;
}
