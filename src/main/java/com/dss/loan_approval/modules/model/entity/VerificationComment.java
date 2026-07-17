package com.dss.loan_approval.modules.model.entity;


import com.dss.loan_approval.config.enums.OfficerRole;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customerProfile;

    @Column(name = "recommended_loan_amount", nullable = false)
    private Double recommendedLoanAmount;

    @Column(name = "recommended_loan_tenor", nullable = false)
    private Integer recommendedLoanTenor;

    @Column(length = 1000)
    private String comment;

    @Column(name = "officer_name", nullable = false)
    private String officerName = "System Officer"; // default to avoid null errors

    @Enumerated(EnumType.STRING)
    @Column(name = "officer_role")
    private OfficerRole officerRole;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
