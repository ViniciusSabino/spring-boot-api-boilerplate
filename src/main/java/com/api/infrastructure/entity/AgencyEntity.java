package com.api.infrastructure.entity;

import com.api.infrastructure.constants.DefaultSchema;
import jakarta.persistence.*;

@Entity
@Table(name="agency", schema = DefaultSchema.name)
public class AgencyEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="number", nullable=false, unique=true)
    private Integer number;
}
