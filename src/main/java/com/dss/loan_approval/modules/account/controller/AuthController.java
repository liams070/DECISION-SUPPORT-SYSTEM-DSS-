package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.LoginRequestDTO;
import com.dss.loan_approval.modules.account.dto.request.OfficerRegistrationRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.LoginResponseDTO;
import com.dss.loan_approval.modules.account.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public BaseApiResponse register(@RequestBody OfficerRegistrationRequestDTO dto) {
        return authService.registerOfficer(dto);
    }

    @PostMapping("/login")
    public BaseApiResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return authService.loginOfficer(dto.getEmail(), dto.getPassword());
    }

    @PostMapping("/logout")
    public BaseApiResponse<String> logout(@RequestParam String email) {
        return authService.logoutOfficer(email);
    }

}
