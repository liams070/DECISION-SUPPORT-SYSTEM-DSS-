package com.dss.loan_approval.modules.account.dto.response;

import com.dss.loan_approval.config.enums.Gender;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerRegistrationResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private OfficerRole role;
    private OfficerVerificationStatus status;
}
