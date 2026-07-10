package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double runningLoan;
    private Double newRequest;
    private Double currentLoanBalance;
    private Integer tenor;

    private Double monthlyNet;
    private Double monthlyGross;
    private String passportPhotoUrl;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
