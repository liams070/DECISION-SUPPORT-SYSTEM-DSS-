package com.dss.loan_approval.modules.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetailsRequestDTO {
    private String ministry;
    private String state;
    private String lastPromotionDate;
    private String promotionLetterUrl;
    private List<String> payslipUrls;

}