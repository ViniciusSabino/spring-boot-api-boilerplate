package com.api.infrastructure.repository;

import com.api.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByAccountHolderId(Long accountHolderId);

    AccountEntity findByNumber(String number);
}
