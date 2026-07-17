package com.dss.loan_approval.modules.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceCommentRequestDTO {
    private Long customerId;
    private Double recommendedLoanAmount;
    private Integer recommendedLoanTenor;
    private String comment;
    private String officerName;
}
