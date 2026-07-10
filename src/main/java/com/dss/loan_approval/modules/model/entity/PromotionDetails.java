package com.dss.loan_approval.modules.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ministry;
    private String state;
    private String lastPromotionDate;
    private String promotionLetterUrl;
    private String payslipUrls;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
