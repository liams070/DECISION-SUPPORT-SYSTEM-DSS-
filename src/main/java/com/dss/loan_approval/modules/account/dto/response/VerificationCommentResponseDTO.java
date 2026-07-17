package com.dss.loan_approval.modules.account.dto.response;

import com.dss.loan_approval.config.enums.OfficerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCommentResponseDTO {
    private Long id;
    private Long customerId;
    private Double recommendedLoanAmount;
    private Integer recommendedLoanTenor;
    private String comment;
    private LocalDateTime createdAt;
    private String officerName;
    private OfficerRole officerRole;
}
