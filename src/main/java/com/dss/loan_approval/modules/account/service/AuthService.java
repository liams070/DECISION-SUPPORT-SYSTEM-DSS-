package com.dss.loan_approval.modules.account.service;

import java.security.SecureRandom;
import java.util.NoSuchElementException;

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

                OfficerRegistration savedMd = officerRepository.save(md);

                OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                        .id(savedMd.getId())
                        .fullName(savedMd.getFullName())
                        .email(savedMd.getEmail())
                        .phoneNumber(savedMd.getPhoneNumber())
                        .gender(savedMd.getGender())
                        .role(savedMd.getRole())
                        .status(savedMd.getStatus())
                        .build();

                auditService.logAction(LogAction.REGISTER, savedMd.getEmail(), MD_REGISTERED_SUCCESSFULLY);

                return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, MD_REGISTERATION_SUCCESSFUL, response);
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

            OfficerRegistration savedOfficer = officerRepository.save(officer);

            OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                    .id(savedOfficer.getId())
                    .fullName(savedOfficer.getFullName())
                    .email(savedOfficer.getEmail())
                    .phoneNumber(savedOfficer.getPhoneNumber())
                    .gender(savedOfficer.getGender())
                    .role(savedOfficer.getRole())
                    .status(savedOfficer.getStatus())
                    .build();

            auditService.logAction(LogAction.REGISTER, savedOfficer.getEmail(), OFFICER_REGISTERED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, REGISTRATION_SUCCESSFUL, response);

        } catch (Exception e) {
            log.error(ERROR_REGISTERING_OFFICER, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_REGISTER_OFFICER, null);
        }
    }

    public BaseApiResponse<LoginResponseDTO> loginOfficer(String email, String password) {
        try {
            OfficerRegistration officer = officerRepository.findByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException(OFFICER_NOT_FOUND));

            if (officer.getStatus() != OfficerVerificationStatus.VERIFIED) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, OFFICER_NOT_VERIFIED, null);
            }

            if (!passwordEncoder.matches(password, officer.getPassword())) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, INVALID_CREDENTIALS, null);
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

        } catch (NoSuchElementException e) {
            return new BaseApiResponse<>(NOT_FOUND_CODE, NOT_FOUND_MSG, e.getMessage(), null);
        } catch (Exception e) {
            log.error(ERROR_LOGGING_IN, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_LOGIN, null);
        }
    }


    public BaseApiResponse<String> logoutOfficer(String email) {
        auditService.logAction(LogAction.LOGOUT, email, LOGOUT_SUCCESSFULLY);

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOGOUT_SUCCESSFULLY, null);
    }


    public BaseApiResponse<String> forgotPassword(String email) {
        try {
            OfficerRegistration officer = officerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOT_FOUND));

            SecureRandom random = new SecureRandom();
            int resetCode = 100000 + random.nextInt(900000);

            officer.setResetCode(String.valueOf(resetCode));
            officerRepository.save(officer);

            log.info("Generated reset code for {}: {}", email, resetCode);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, RESET_CODE_GENERATED_SUCCESSFULLY, RESET_CODE_SENT_TO_EMAIL);
        } catch (Exception e) {
            log.error(ERROR_GENERATING_RESET_CODE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_GENERATE_RESET_CODE, null);
        }
    }

    public BaseApiResponse<String> resetPassword(String email, String resetCode, String newPassword) {
        try {
            OfficerRegistration officer = officerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOT_FOUND));

            if (!resetCode.equals(officer.getResetCode())) {

                auditService.logAction(LogAction.RESET_PASSWORD, email, INVALID_RESET_CODE);

                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, INVALID_RESET_CODE, null);
            }

            officer.setPassword(passwordEncoder.encode(newPassword));
            officer.setResetCode(null);
            officerRepository.save(officer);

            auditService.logAction(LogAction.RESET_PASSWORD, officer.getEmail(), PASSWORD_RESET_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PASSWORD_RESET_SUCCESSFULLY, PASSWORD_UPDATED_SUCCESSFULLY);
        } catch (Exception e) {
            log.error(ERROR_RESETTING_PASSWORD, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_RESET_PASSWORD, null);
        }
    }

}