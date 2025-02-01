package com.api.infrastructure.entity;


import com.api.infrastructure.constants.DefaultSchema;
import jakarta.persistence.*;

@Entity
@Table(name="account_holder", schema = DefaultSchema.name)
public class AccountHolderEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="document_number", length=14, nullable=false, unique=true)
    private String documentNumber;
}
