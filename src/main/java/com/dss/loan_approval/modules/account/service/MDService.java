package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.CustomerStatus;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.MDCommentRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.ComplianceCommentResponseDTO;
import com.dss.loan_approval.modules.account.dto.response.CreditAdminResponseDTO;
import com.dss.loan_approval.modules.account.dto.response.MDCommentResponseDTO;
import com.dss.loan_approval.modules.account.dto.response.VerificationCommentResponseDTO;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.entity.MDComment;
import com.dss.loan_approval.modules.model.repository.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MDService {
    private final MDCommentRepository mdCommentRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final VerificationCommentRepository verificationCommentRepository;
    private final ComplianceCommentRepository complianceCommentRepository;
    private final CreditAdminRepository creditAdminRepository;

    public BaseApiResponse<MDCommentResponseDTO> submitMDComment(MDCommentRequestDTO dto) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            MDComment comment = MDComment.builder()
                    .customerProfile(customer)
                    .recommendedLoanAmount(dto.getRecommendedLoanAmount())
                    .recommendedLoanTenor(dto.getRecommendedLoanTenor())
                    .comment(dto.getComment())
                    .officerName(dto.getOfficerName())
                    .officerRole(OfficerRole.MANAGING_DIRECTOR)
                    .createdAt(LocalDateTime.now())
                    .build();

            MDComment saved = mdCommentRepository.save(comment);

            MDCommentResponseDTO response = MDCommentResponseDTO.builder()
                    .id(saved.getId())
                    .customerId(saved.getCustomerProfile().getId())
                    .recommendedLoanAmount(saved.getRecommendedLoanAmount())
                    .recommendedLoanTenor(saved.getRecommendedLoanTenor())
                    .comment(saved.getComment())
                    .officerName(saved.getOfficerName())
                    .officerRole(saved.getOfficerRole())
                    .createdAt(saved.getCreatedAt())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, MD_COMMENT_SUBMITTED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_SUBMITTING_MD_COMMENT, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_MD_COMMENT, null);
        }
    }
    public BaseApiResponse<CustomerProfile> approveCustomer(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            customer.setStatus(CustomerStatus.APPROVED);
            customerProfileRepository.save(customer);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG,MD_APPROVED_SUCCESSFULLY, customer);

        } catch (Exception e) {
            log.error(ERROR_APPROVING_MD, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG,FAILED_TO_APPROVE_MD, null);
        }
    }

    public BaseApiResponse<CustomerProfile> declineCustomer(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            customer.setStatus(CustomerStatus.REJECTED);
            customerProfileRepository.save(customer);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG,MD_DECLINED_SUCCESSFULLY, customer);

        } catch (Exception e) {
            log.error(ERROR_DECLINING_MD, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG,FAILED_TO_DECLINE_MD, null);
        }
    }
    public BaseApiResponse<Map<String, Object>> getCustomerProfileWithComments(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOT_FOUND));

            List<VerificationCommentResponseDTO> verificationResponses =
                    verificationCommentRepository.findByCustomerProfile(customer).stream()
                            .map(c -> VerificationCommentResponseDTO.builder()
                                    .id(c.getId())
                                    .customerId(customer.getId())
                                    .recommendedLoanAmount(c.getRecommendedLoanAmount())
                                    .recommendedLoanTenor(c.getRecommendedLoanTenor())
                                    .comment(c.getComment())
                                    .officerName(c.getOfficerName())
                                    .officerRole(c.getOfficerRole())
                                    .createdAt(c.getCreatedAt())
                                    .build())
                            .collect(Collectors.toList());

            List<ComplianceCommentResponseDTO> complianceResponses =
                    complianceCommentRepository.findByCustomerProfile(customer).stream()
                            .map(c -> ComplianceCommentResponseDTO.builder()
                                    .id(c.getId())
                                    .customerId(customer.getId())
                                    .recommendedLoanAmount(c.getRecommendedLoanAmount())
                                    .recommendedLoanTenor(c.getRecommendedLoanTenor())
                                    .comment(c.getComment())
                                    .officerName(c.getOfficerName())
                                    .officerRole(c.getOfficerRole())
                                    .createdAt(c.getCreatedAt())
                                    .build())
                            .collect(Collectors.toList());

            List<MDCommentResponseDTO> mdResponses =
                    mdCommentRepository.findByCustomerProfile(customer).stream()
                            .map(c -> MDCommentResponseDTO.builder()
                                    .id(c.getId())
                                    .customerId(customer.getId())
                                    .recommendedLoanAmount(c.getRecommendedLoanAmount())
                                    .recommendedLoanTenor(c.getRecommendedLoanTenor())
                                    .comment(c.getComment())
                                    .officerName(c.getOfficerName())
                                    .officerRole(c.getOfficerRole())
                                    .createdAt(c.getCreatedAt())
                                    .build())
                            .collect(Collectors.toList());

            List<CreditAdminResponseDTO> creditAdminResponses =
                    creditAdminRepository.findByCustomerId(customer.getId()).stream()
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
            payload.put("verificationComments", verificationResponses);
            payload.put("complianceComments", complianceResponses);
            payload.put("mdComments", mdResponses);
            payload.put("creditAdminComments", creditAdminResponses);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, DATA_FETCHED_SUCCESSFULLY, payload);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_DATA, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_DATA, null);
        }
    }
}
