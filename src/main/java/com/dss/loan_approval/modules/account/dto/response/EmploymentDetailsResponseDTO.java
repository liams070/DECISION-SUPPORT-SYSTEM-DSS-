package com.dss.loan_approval.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDetailsResponseDTO {
    private Long id;
    private Long customerId;
    private String officeName;
    private String officeAddress;
    private String yearOfEmployment;
    private String employmentLetterUrl;
}