package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.entity.VerificationComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationCommentRepository extends JpaRepository<VerificationComment, Long> {
    List<VerificationComment> findByCustomerProfile(CustomerProfile customerProfile);

    boolean existsByCustomerProfile(CustomerProfile customer);

}
