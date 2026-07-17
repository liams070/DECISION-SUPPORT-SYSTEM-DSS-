package com.dss.loan_approval.modules.model.repository;

import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.entity.MDComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MDCommentRepository extends JpaRepository<MDComment,Long> {
    List<MDComment> findByCustomerProfile(CustomerProfile customerProfile);
}
