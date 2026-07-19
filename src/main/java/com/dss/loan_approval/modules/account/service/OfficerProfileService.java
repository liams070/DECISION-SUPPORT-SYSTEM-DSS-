package com.dss.loan_approval.modules.account.service;


import com.dss.loan_approval.config.enums.LogAction;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import com.dss.loan_approval.modules.model.repository.OfficerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OfficerProfileService {
    private final OfficerRepository officerRepository;
    private final AuditService auditService;

    public BaseApiResponse<OfficerRegistration> getCurrentOfficer(Authentication authentication) {
        try {
            String email = authentication.getName();
            Optional<OfficerRegistration> officer = officerRepository.findByEmail(email);

            if (officer.isPresent()) {
                auditService.logAction(LogAction.FETCH_PROFILE, email, OFFICER_PROFILE_FETCHED_SUCCESSFULLY);
                return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICER_FETCHED_SUCCESSFULLY, officer.get());
            } else {
                return new BaseApiResponse<>(NOT_FOUND_CODE, NOT_FOUND_MSG, OFFICER_NOTFOUND, null);
            }
        } catch (Exception e) {
            log.error(ERROR_FETCHING_OFFICERS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCHED_OFFICER, null);
        }
    }

    public BaseApiResponse<OfficerRegistrationResponseDTO> updateOfficer(Long id, OfficerRegistrationResponseDTO dto) {
        try {
            Optional<OfficerRegistration> officerOpt = officerRepository.findById(id);
            if (officerOpt.isEmpty()) {
                return new BaseApiResponse<>(NOT_FOUND_CODE, NOT_FOUND_MSG, OFFICER_NOT_FOUND, null);
            }

            OfficerRegistration officer = officerOpt.get();
            String beforeState = officer.toString();

            officer.setFullName(dto.getFullName());
            officer.setPhoneNumber(dto.getPhoneNumber());
            officer.setGender(dto.getGender());
            officer.setRole(dto.getRole());
            officer.setStatus(dto.getStatus());

            OfficerRegistration updated = officerRepository.save(officer);
            String afterState = updated.toString();

            OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                    .id(updated.getId())
                    .fullName(updated.getFullName())
                    .email(updated.getEmail())
                    .phoneNumber(updated.getPhoneNumber())
                    .gender(updated.getGender())
                    .role(updated.getRole())
                    .status(updated.getStatus())
                    .build();

            auditService.logAction(LogAction.UPDATE_PROFILE, updated.getEmail(), id.toString(),
                    beforeState, afterState, null, null, OFFICER_PROFILE_UPDATED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICER_PROFILE_UPDATED_SUCCESSFULLY, response);
        } catch (Exception e) {
            log.error(ERROR_FETCHING_UPDATE_OFFICERS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_UPDATE_OFFICER, null);
        }
    }

    public BaseApiResponse<List<OfficerRegistration>> getAllOfficer() {
        try {
            List<OfficerRegistration> officers = officerRepository.findAll();

            String performedBy = SecurityContextHolder.getContext().getAuthentication().getName();
            auditService.logAction(LogAction.FETCH_ALL_OFFICERS, performedBy, OFFICER_FETCHED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICER_FETCHED_SUCCESSFULLY, officers);
        } catch (Exception e) {
            log.error(ERROR_FETCHING_OFFICERS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCHED_OFFICER, null);
        }
    }


    public BaseApiResponse<OfficerRegistration> getOfficer(Long id) {
        try {
            Optional<OfficerRegistration> officerOpt = officerRepository.findById(id);
            if (officerOpt.isPresent()) {
                auditService.logAction(LogAction.FETCH_OFFICER_BY_ID, officerOpt.get().getEmail(), OFFICER_FETCHED_SUCCESSFULLY);
                return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICER_FETCHED_SUCCESSFULLY, officerOpt.get());
            } else {
                return new BaseApiResponse<>(NOT_FOUND_CODE, NOT_FOUND_MSG, OFFICER_NOT_FOUND, null);
            }
        } catch (Exception e) {
            log.error(ERROR_FETCHING_OFFICERBYID, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCHED_OFFICER, null);
        }
    }

}
