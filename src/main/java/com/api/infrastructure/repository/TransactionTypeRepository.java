package com.api.infrastructure.repository;

import com.api.infrastructure.entity.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository  extends JpaRepository<TransactionTypeEntity, Long> {

    TransactionTypeEntity findByType(String type);
}
