package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.OfficerRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MDComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customerProfile;

    private Double recommendedLoanAmount;
    private Integer recommendedLoanTenor;

    private String comment;
    private String officerName;

    @Enumerated(EnumType.STRING)
    private OfficerRole officerRole;

    private LocalDateTime createdAt;
}
