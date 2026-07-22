package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.CustomerStatus;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.CreditAdminRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.CreditAdminResponseDTO;
import com.dss.loan_approval.modules.model.entity.CreditAdminComment;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.repository.ComplianceCommentRepository;
import com.dss.loan_approval.modules.model.repository.CreditAdminRepository;
import com.dss.loan_approval.modules.model.repository.CustomerProfileRepository;
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
public class CreditAdminService {
    private final CreditAdminRepository creditAdminRepository;
    private final ComplianceCommentRepository complianceCommentRepository;
    private final CustomerProfileRepository customerProfileRepository;

    public BaseApiResponse<Void> submitComment(CreditAdminRequestDTO dto) {
        try {
            boolean hasCompliance = complianceCommentRepository.existsByCustomerProfileId(dto.getCustomerId());
            if (!hasCompliance) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, COMPLIANCE_COMMENT_MUST_BE_SUBMITTED, null);
            }
            CreditAdminComment comment = CreditAdminComment.builder()
                    .customerId(dto.getCustomerId())
                    .recommendedLoanAmount(dto.getRecommendedLoanAmount())
                    .recommendedLoanTenor(dto.getRecommendedLoanTenor())
                    .comment(dto.getComment())
                    .officerName(dto.getOfficerName())
                    .officerRole(OfficerRole.CREDIT_ADMIN)
                    .createdAt(LocalDateTime.now().toString())
                    .build();

            creditAdminRepository.save(comment);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, CREDIT_ADMIN_COMMENTS_FETCHED_SUCCESSFULLY, null);
        } catch (Exception e) {
            log.error(ERROR_SAVING_CREDIT_ADMIN_COMMENT, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_CREDIT_ADMIN_COMMENT, null);
        }
    }

    public BaseApiResponse<Map<String, Object>> forwardToMD(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            boolean hasCreditAdmin = creditAdminRepository.existsByCustomerId(customerId);
            if (!hasCreditAdmin) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, CREDIT_ADMIN_COMMENT_MUST_BE_SUBMITTED_BEFORE_FORWARDING_TO_MD, null);
            }

            customer.setStatus(CustomerStatus.FORWARDED_TO_MD);
            customerProfileRepository.save(customer);

            List<CreditAdminComment> creditAdminComments = creditAdminRepository.findByCustomerId(customerId);

            List<CreditAdminResponseDTO> creditAdminResponses = creditAdminComments.stream()
                    .map(c -> CreditAdminResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerId())
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
            payload.put("creditAdminComments", creditAdminResponses);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROFILE_FORWARDED_TO_MD_SUCCESSFULLY, payload);

        } catch (Exception e) {
            log.error(ERROR_FORWARDING_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG,
                    FAILED_TO_FORWARD_PROFILE, null);
        }
    }

    public BaseApiResponse<List<CreditAdminResponseDTO>> getComments(Long customerId) {
        try {
            List<CreditAdminResponseDTO> comments = creditAdminRepository.findByCustomerId(customerId)
                    .stream()
                    .map(c -> CreditAdminResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .officerName(c.getOfficerName())
                            .officerRole(c.getOfficerRole())
                            .createdAt(c.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, CREDIT_ADMIN_COMMENTS_FETCHED_SUCCESSFULLY, comments);
        } catch (Exception e) {
            log.error(ERROR_SAVING_CREDIT_ADMIN_COMMENT, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_CREDIT_ADMIN_COMMENTS, null);
        }
    }
}
