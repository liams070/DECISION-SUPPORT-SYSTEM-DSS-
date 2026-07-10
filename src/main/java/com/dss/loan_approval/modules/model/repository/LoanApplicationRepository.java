package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

}
