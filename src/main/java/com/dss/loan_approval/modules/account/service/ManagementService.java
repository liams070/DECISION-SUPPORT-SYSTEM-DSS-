package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.LogAction;
import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import com.dss.loan_approval.modules.model.repository.ManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagementService {
    private final ManagementRepository managementRepository;
    private final AuditService auditService;

    public BaseApiResponse<OfficerRegistrationResponseDTO> approveOfficer(Long id) {
        OfficerRegistration officer = managementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(OFFICER_NOTFOUND));

        String beforeState = officer.toString();

        officer.setStatus(OfficerVerificationStatus.VERIFIED);
        OfficerRegistration updated = managementRepository.save(officer);

        String afterState = updated.toString();

        OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                .id(updated.getId())
                .fullName(updated.getFullName())
                .email(updated.getEmail())
                .role(updated.getRole())
                .status(updated.getStatus())
                .build();

        auditService.logAction(LogAction.APPROVE_OFFICER, updated.getEmail(), id.toString(),
                beforeState, afterState, null, null, OFFICER_APPROVED_SUCCESSFULLY);

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICER_APPROVED_SUCCESSFULLY, response);
    }


    public BaseApiResponse<List<OfficerRegistrationResponseDTO>> getPendingOfficers() {
        List<OfficerRegistration> pending = managementRepository.findByStatus(OfficerVerificationStatus.PENDING_VERIFICATION);

        List<OfficerRegistrationResponseDTO> response = pending.stream()
                .map(officer -> OfficerRegistrationResponseDTO.builder()
                        .id(officer.getId())
                        .fullName(officer.getFullName())
                        .email(officer.getEmail())
                        .role(officer.getRole())
                        .status(officer.getStatus())
                        .build())
                .toList();

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PENDING_OFFICER_RETRIEVED, response);
    }

    public BaseApiResponse<List<OfficerRegistrationResponseDTO>> getAllStaff() {
        try {
            List<OfficerRegistration> staff = managementRepository.findAll();

            List<OfficerRegistrationResponseDTO> response = staff.stream()
                    .map(officer -> OfficerRegistrationResponseDTO.builder()
                            .id(officer.getId())
                            .fullName(officer.getFullName())
                            .email(officer.getEmail())
                            .phoneNumber(officer.getPhoneNumber())
                            .gender(officer.getGender())
                            .role(officer.getRole())
                            .status(officer.getStatus())
                            .build())
                    .toList();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, OFFICERS_FETCHED_SUCCESSFULLY, response);
        } catch (Exception e) {
            log.error(ERROR_FETCHING_OFFICERS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCHED_OFFICER, null);
        }
    }

    public BaseApiResponse<Void> enableStaff(Long id) {
        try {
            OfficerRegistration officer = managementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOTFOUND));

            String beforeState = officer.toString();

            officer.setStatus(OfficerVerificationStatus.VERIFIED);
            OfficerRegistration updated = managementRepository.save(officer);

            String afterState = updated.toString();

            auditService.logAction(LogAction.ENABLE_STAFF, updated.getEmail(), id.toString(),
                    beforeState, afterState, null, null, STAFF_ENABLED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, STAFF_ENABLED_SUCCESSFULLY, null);
        } catch (Exception e) {
            log.error(ERROR_ENABLING_OFFICER, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_ENABLE_OFFICER, null);
        }
    }

    public BaseApiResponse<Void> disableStaff(Long id) {
        try {
            OfficerRegistration officer = managementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOTFOUND));

            String beforeState = officer.toString();

            officer.setStatus(OfficerVerificationStatus.DISABLED);
            OfficerRegistration updated = managementRepository.save(officer);

            String afterState = updated.toString();

            auditService.logAction(LogAction.DISABLE_STAFF, updated.getEmail(), id.toString(),
                    beforeState, afterState, null, null, STAFF_DISABLED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, STAFF_DISABLED_SUCCESSFULLY, null);
        } catch (Exception e) {
            log.error(ERROR_DISABLING_OFFICER, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_DISABLE_OFFICER, null);
        }
    }

    public BaseApiResponse<Void> deleteStaff(Long id) {
        try {
            OfficerRegistration officer = managementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(OFFICER_NOTFOUND));

            String beforeState = officer.toString();

            managementRepository.deleteById(id);

            auditService.logAction(LogAction.DELETE_STAFF, officer.getEmail(), id.toString(),
                    beforeState, "Deleted", null, null, STAFF_DELETED_SUCCESSFULLY);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, STAFF_DELETED_SUCCESSFULLY, null);
        } catch (Exception e) {
            log.error(ERROR_DELETING_OFFICER, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_DELETE_OFFICER, null);
        }
    }


}
