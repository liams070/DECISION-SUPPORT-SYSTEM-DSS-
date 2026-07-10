package com.dss.loan_approval.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanApplicationResponseDTO {
    private Long id;
    private Double runningLoan;
    private Double currentLoanBalance;
    private Double newRequest;
    private Integer tenor;
    private Double monthlyNet;
    private Double monthlyGross;
    private String passportPhotoUrl;

}
