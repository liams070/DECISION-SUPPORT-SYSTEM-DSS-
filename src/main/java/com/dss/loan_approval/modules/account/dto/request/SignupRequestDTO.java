package com.dss.loan_approval.modules.account.dto.request;

import com.dss.loan_approval.config.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequestDTO {
    private String email;
    private String password;
    private Role role;
}
