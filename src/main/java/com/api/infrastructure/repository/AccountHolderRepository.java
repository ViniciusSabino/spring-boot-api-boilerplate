package com.api.infrastructure.repository;

import com.api.infrastructure.entity.AccountHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolderEntity, Long> {
    AccountHolderEntity findByDocumentNumber(String documentNumber);
}
