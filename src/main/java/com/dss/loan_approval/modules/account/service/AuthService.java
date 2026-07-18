package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.LogAction;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import com.dss.loan_approval.config.security.JwtUtil;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.LoginRequestDTO;
import com.dss.loan_approval.modules.account.dto.request.OfficerRegistrationRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.LoginResponseDTO;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import com.dss.loan_approval.modules.model.repository.OfficerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor

public class AuthService {
    private final OfficerRepository officerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuditService auditService;

    public BaseApiResponse<OfficerRegistrationResponseDTO> registerOfficer(OfficerRegistrationRequestDTO dto) {
        try {
            if (officerRepository.existsByEmail(dto.getEmail())) {
                return new BaseApiResponse<>(CONFLICT_CODE, CONFLICT_MSG, EMAIL_ALREADY_EXISTS, null);
            }

            if (dto.getRole() == OfficerRole.MANAGING_DIRECTOR) {
                boolean mdExists = officerRepository.existsByRole(OfficerRole.MANAGING_DIRECTOR);
                if (mdExists) {
                    return new BaseApiResponse<>(CONFLICT_CODE, CONFLICT_MSG, MD_ALREADY_REGISTERED, null);
                }

                String hashedPassword = passwordEncoder.encode(dto.getPassword());

                OfficerRegistration md = OfficerRegistration.builder()
                        .fullName(dto.getFullName())
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .gender(dto.getGender())
                        .password(hashedPassword)
                        .role(OfficerRole.MANAGING_DIRECTOR)
                        .status(OfficerVerificationStatus.VERIFIED)
                        .build();

                OfficerRegistration saved = officerRepository.save(md);

                OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                        .id(saved.getId())
                        .fullName(saved.getFullName())
                        .email(saved.getEmail())
                        .phoneNumber(saved.getPhoneNumber())
                        .gender(saved.getGender())
                        .role(saved.getRole())
                        .status(saved.getStatus())
                        .build();

                return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, MD_REGISTERATION_SUCCESSFUL , response);
            }

            String hashedPassword = passwordEncoder.encode(dto.getPassword());

            OfficerRegistration officer = OfficerRegistration.builder()
                    .fullName(dto.getFullName())
                    .email(dto.getEmail())
                    .phoneNumber(dto.getPhoneNumber())
                    .gender(dto.getGender())
                    .password(hashedPassword)
                    .role(dto.getRole())
                    .status(OfficerVerificationStatus.PENDING_VERIFICATION)
                    .build();

            OfficerRegistration saved = officerRepository.save(officer);

            OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                    .id(saved.getId())
                    .fullName(saved.getFullName())
                    .email(saved.getEmail())
                    .phoneNumber(saved.getPhoneNumber())
                    .gender(saved.getGender())
                    .role(saved.getRole())
                    .status(saved.getStatus())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, REGISTRATION_SUCCESSFUL, response);

        } catch (Exception e) {
            log.error(ERROR_REGISTERING_OFFICER, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_REGISTER_OFFICER, null);
        }
    }

    public BaseApiResponse<LoginResponseDTO> loginOfficer(String email, String password) {
        try {
            OfficerRegistration officer = officerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOT_FOUND));

            if (officer.getStatus() != OfficerVerificationStatus.VERIFIED) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG,
                        OFFICER_NOT_VERIFIED, null);
            }

            if (!passwordEncoder.matches(password, officer.getPassword())) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG,
                        INVALID_CREDENTIALS, null);
            }

            String token = jwtUtil.generateToken(officer.getEmail(), officer.getRole().name());

            LoginResponseDTO response = LoginResponseDTO.builder()
                    .token(token)
                    .role(officer.getRole().name())
                    .fullName(officer.getFullName())
                    .email(officer.getEmail())
                    .build();

            auditService.logAction(LogAction.LOGIN, officer.getEmail(), LOGIN_SUCCESSFUL);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOGIN_SUCCESSFUL, response);

        } catch (Exception e) {
            log.error(ERROR_LOGGING_IN, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_LOGIN, null);
        }
    }

    public BaseApiResponse<String> logoutOfficer(String email) {
        auditService.logAction(LogAction.LOGOUT, email, LOGOUT_SUCCESSFULLY);

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOGOUT_SUCCESSFULLY, null);
    }
}