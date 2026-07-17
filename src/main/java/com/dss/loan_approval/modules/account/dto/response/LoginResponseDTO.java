package com.dss.loan_approval.modules.account.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private String role;
    private String fullName;
    private String email;
}
