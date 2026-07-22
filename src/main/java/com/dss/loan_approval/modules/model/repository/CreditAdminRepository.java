package com.dss.loan_approval.modules.model.repository;


import com.dss.loan_approval.modules.model.entity.CreditAdminComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditAdminRepository extends JpaRepository<CreditAdminComment, Long> {
    List<CreditAdminComment> findByCustomerId(Long customerId);

    boolean existsByCustomerId(Long customerId);
}
