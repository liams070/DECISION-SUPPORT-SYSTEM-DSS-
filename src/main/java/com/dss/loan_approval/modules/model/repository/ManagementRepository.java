package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementRepository extends JpaRepository<OfficerRegistration, Long> {
    List<OfficerRegistration> findByStatus(OfficerVerificationStatus status);
    boolean existsByEmail(String email);
}
