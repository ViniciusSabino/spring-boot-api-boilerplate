package com.api.infrastructure.repository;

import com.api.infrastructure.entity.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {

    AgencyEntity findByNumber(Integer number);
}
