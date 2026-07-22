package com.dss.loan_approval.modules.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDTO {
    private Long customerId;
    private Double runningLoan;
    private Double newRequest;
    private Double currentLoanBalance;
    private Integer tenor;
    private Double monthlyNet;
    private Double monthlyGross;
    private String passportPhotoUrl;
}
