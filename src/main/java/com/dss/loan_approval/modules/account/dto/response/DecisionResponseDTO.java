package com.dss.loan_approval.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DecisionResponseDTO {
    private Long applicationId;
    private String decision;
    private String reason;
    private LocalDateTime decidedAt;
}
