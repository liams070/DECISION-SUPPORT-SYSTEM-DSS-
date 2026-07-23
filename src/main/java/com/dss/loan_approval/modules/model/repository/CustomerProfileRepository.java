package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
}
