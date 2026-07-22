package com.dss.loan_approval.modules.account.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDetailsRequestDTO {
    private Long customerId;
    private String officeName;
    private String officeAddressEmployment;
    private String yearOfEmployment;
    private String employmentLetterUrl;
}