package com.dss.loan_approval.modules.account.dto.request;

import com.dss.loan_approval.config.enums.Gender;
import com.dss.loan_approval.config.enums.OfficerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerRegistrationRequestDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String password;
    private OfficerRole role;
}
