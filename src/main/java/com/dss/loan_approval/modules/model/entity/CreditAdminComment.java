package com.dss.loan_approval.modules.model.entity;


import com.dss.loan_approval.config.enums.OfficerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_admin_comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditAdminComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Double recommendedLoanAmount;

    private Integer recommendedLoanTenor;

    private String comment;

    private String officerName;

    private OfficerRole officerRole;

    private String createdAt;
}
