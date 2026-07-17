package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<OfficerRegistration, Long> {
    Optional<OfficerRegistration> findByEmail(String email);
    boolean existsByEmail(String email);
}
