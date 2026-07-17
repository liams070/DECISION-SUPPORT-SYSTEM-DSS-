package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.OfficerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customerProfile;

    private Double recommendedLoanAmount;
    private Integer recommendedLoanTenor;

    @Column(length = 1000)
    private String comment;

    private String officerName;

    @Enumerated(EnumType.STRING)
    private OfficerRole officerRole;

    private LocalDateTime createdAt;
}
