package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.EmploymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDetailsRepository extends JpaRepository<EmploymentDetails, Long> {

}
