package com.dss.loan_approval.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetailsResponseDTO {
    private Long id;
    private Long customerId;
    private String ministry;
    private String state;
    private String lastPromotionDate;
    private String promotionLetterUrl;

    private List<String> payslipUrls;
}