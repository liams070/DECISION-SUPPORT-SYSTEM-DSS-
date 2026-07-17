package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import com.dss.loan_approval.modules.model.repository.ManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dss.loan_approval.config.util.AppCodeConstants.SUCCESS_CODE;
import static com.dss.loan_approval.config.util.AppCodeConstants.SUCCESS_MSG;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagementService {
    private final ManagementRepository managementRepository;

    public BaseApiResponse<OfficerRegistrationResponseDTO> approveOfficer(Long id) {
        OfficerRegistration officer = managementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Officer not found"));

        officer.setStatus(OfficerVerificationStatus.VERIFIED);
        managementRepository.save(officer);

        OfficerRegistrationResponseDTO response = OfficerRegistrationResponseDTO.builder()
                .id(officer.getId())
                .fullName(officer.getFullName())
                .email(officer.getEmail())
                .role(officer.getRole())
                .status(officer.getStatus())
                .build();

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, "Officer approved successfully", response);
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

        return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, "Pending officers retrieved", response);
    }
}
