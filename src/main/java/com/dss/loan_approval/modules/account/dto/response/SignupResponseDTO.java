package com.dss.loan_approval.modules.account.dto.response;

import com.dss.loan_approval.config.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
@Getter
public class SignupResponseDTO {
    private Long userId;
    private String email;
    private Role role;
}
