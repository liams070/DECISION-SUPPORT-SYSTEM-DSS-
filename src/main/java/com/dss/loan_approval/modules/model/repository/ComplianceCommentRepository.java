package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.ComplianceComment;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceCommentRepository extends JpaRepository<ComplianceComment,Long> {
    List<ComplianceComment> findByCustomerProfile(CustomerProfile customerProfile);

    boolean existsByCustomerProfile(CustomerProfile customer);

    boolean existsByCustomerProfileId(Long customerId);
}
