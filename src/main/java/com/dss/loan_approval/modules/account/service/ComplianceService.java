package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.CustomerStatus;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.ComplianceCommentRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.ComplianceCommentResponseDTO;
import com.dss.loan_approval.modules.account.dto.response.VerificationCommentResponseDTO;
import com.dss.loan_approval.modules.model.entity.ComplianceComment;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.entity.VerificationComment;
import com.dss.loan_approval.modules.model.repository.ComplianceCommentRepository;
import com.dss.loan_approval.modules.model.repository.CustomerProfileRepository;
import com.dss.loan_approval.modules.model.repository.VerificationCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComplianceService {
    private final ComplianceCommentRepository complianceCommentRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final VerificationCommentRepository verificationCommentRepository;

    public BaseApiResponse submitComplianceComment(ComplianceCommentRequestDTO dto) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            ComplianceComment comment = ComplianceComment.builder()
                    .customerProfile(customer)
                    .recommendedLoanAmount(dto.getRecommendedLoanAmount())
                    .recommendedLoanTenor(dto.getRecommendedLoanTenor())
                    .comment(dto.getComment())
                    .officerName(dto.getOfficerName())
                    .officerRole(OfficerRole.COMPLIANCE_DESK)
                    .createdAt(LocalDateTime.now())
                    .build();

            ComplianceComment saved = complianceCommentRepository.save(comment);

            ComplianceCommentResponseDTO response = ComplianceCommentResponseDTO.builder()
                    .id(saved.getId())
                    .customerId(saved.getCustomerProfile().getId())
                    .recommendedLoanAmount(saved.getRecommendedLoanAmount())
                    .recommendedLoanTenor(saved.getRecommendedLoanTenor())
                    .comment(saved.getComment())
                    .officerName(saved.getOfficerName())
                    .officerRole(saved.getOfficerRole())
                    .createdAt(saved.getCreatedAt())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG,COMPLIANCE_COMMENT_SUBMITTED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_SUBMITTING_COMPLIANCE_COMMENT, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_COMPLIANCE_COMMENT, null);
        }

    }
    public BaseApiResponse approveCustomer(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            customer.setStatus(CustomerStatus.REVIEWED_BY_COMPLIANCE);
            customerProfileRepository.save(customer);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, COMPLIANCE_APPROVED_SUCCESSFULLY, customer);

        } catch (Exception e) {
            log.error(ERROR_APPROVING_COMPLIANCE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG,FAILED_TO_APPROVE_COMPLIANCE,null);
        }
    }

    public BaseApiResponse<CustomerProfile> declineCustomer(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            customer.setStatus(CustomerStatus.REJECTED);
            customerProfileRepository.save(customer);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, COMPLIANCE_DECLINED_SUCCESSFULLY, customer);

        } catch (Exception e) {
            log.error(ERROR_DECLINING_COMPLIANCE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_DECLINE_COMPLIANCE, null);
        }
    }

    public BaseApiResponse<Map<String, Object>> getCustomerProfileWithComments(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            List<VerificationComment> verificationComments =
                    verificationCommentRepository.findByCustomerProfile(customer);

            List<VerificationCommentResponseDTO> verificationResponses = verificationComments.stream()
                    .map(c -> VerificationCommentResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerProfile().getId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .officerName(c.getOfficerName())
                            .officerRole(c.getOfficerRole())
                            .createdAt(c.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            List<ComplianceComment> complianceComments =
                    complianceCommentRepository.findByCustomerProfile(customer);

            List<ComplianceCommentResponseDTO> complianceResponses = complianceComments.stream()
                    .map(c -> ComplianceCommentResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerProfile().getId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .officerName(c.getOfficerName())
                            .officerRole(c.getOfficerRole())
                            .createdAt(c.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            Map<String, Object> payload = new HashMap<>();
            payload.put("customerProfile", customer);
            payload.put("verificationComments", verificationResponses);
            payload.put("complianceComments", complianceResponses);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, DATA_FETCHED_SUCCESSFULLY, payload);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_DATA, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE,SERVER_ERROR_MSG, FAILED_TO_FETCH_DATA, null);
        }
    }

}
