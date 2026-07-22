package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.CustomerStatus;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.VerificationCommentRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.VerificationCommentResponseDTO;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.entity.VerificationComment;
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
public class VerificationService {
    private final VerificationCommentRepository verificationCommentRepository;
    private final CustomerProfileRepository customerProfileRepository;

    public BaseApiResponse addComment(VerificationCommentRequestDTO dto) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOTFOUND));

            if (customer.getStatus() != CustomerStatus.SUBMITTED) {
                return new BaseApiResponse<>(UNAUTHORIZED_CODE, UNAUTHORIZED_MSG, CUSTOMER_MUST_BE_IN_SUBMITTED_STATE, null);
            }

            VerificationComment comment = VerificationComment.builder()
                    .customerProfile(customer)
                    .recommendedLoanAmount(dto.getRecommendedLoanAmount())
                    .recommendedLoanTenor(dto.getRecommendedLoanTenor())
                    .comment(dto.getComment())
                    .officerRole(OfficerRole.VERIFICATION_DESK)
                    .officerName("System Verification Officer")
                    .createdAt(LocalDateTime.now())
                    .build();

            VerificationComment saved = verificationCommentRepository.save(comment);

            VerificationCommentResponseDTO response = VerificationCommentResponseDTO.builder()
                    .id(saved.getId())
                    .customerId(saved.getCustomerProfile().getId())
                    .recommendedLoanAmount(saved.getRecommendedLoanAmount())
                    .recommendedLoanTenor(saved.getRecommendedLoanTenor())
                    .comment(saved.getComment())
                    .createdAt(saved.getCreatedAt())
                    .officerName(saved.getOfficerName())
                    .officerRole(saved.getOfficerRole())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, COMMENT_SUBMITTED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_SUBMITTING_COMMENT, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_COMMENT, null);
        }
    }


    public BaseApiResponse forwardToCompliance(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOTFOUND));


            customer.setStatus(CustomerStatus.FORWARDED_TO_COMPLIANCE);
            customerProfileRepository.save(customer);

            List<VerificationComment> comments = verificationCommentRepository.findByCustomerProfile(customer);

            List<VerificationCommentResponseDTO> commentResponses = comments.stream()
                    .map(c -> VerificationCommentResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerProfile().getId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .createdAt(c.getCreatedAt())
                            .officerName(c.getOfficerName())
                            .build())
                    .collect(Collectors.toList());

            Map<String, Object> payload = new HashMap<>();
            payload.put("customerProfile", customer);
            payload.put("verificationComments", commentResponses);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROFILE_FORWARDED_SUCCESSFULLY, payload);

        } catch (Exception e) {
            log.error(ERROR_FORWARDING_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FORWARD_PROFILE, null);
        }
    }

    public BaseApiResponse getAllVerificationComments() {
        try {
            List<VerificationComment> comments = verificationCommentRepository.findAll();

            List<VerificationCommentResponseDTO> responses = comments.stream()
                    .map(c -> VerificationCommentResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerProfile().getId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .createdAt(c.getCreatedAt())
                            .officerName(c.getOfficerName())
                            .build())
                    .collect(Collectors.toList());

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, DATA_FETCHED_SUCCESSFULLY, responses);
        } catch (Exception e) {
            log.error(ERROR_FETCHING_DATA, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_DATA, null);
        }
    }

    public BaseApiResponse getCommentsByCustomer(Long customerId) {
        try {
            CustomerProfile customer = customerProfileRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOTFOUND));

            List<VerificationComment> comments = verificationCommentRepository.findByCustomerProfile(customer);

            List<VerificationCommentResponseDTO> responses = comments.stream()
                    .map(c -> VerificationCommentResponseDTO.builder()
                            .id(c.getId())
                            .customerId(c.getCustomerProfile().getId())
                            .recommendedLoanAmount(c.getRecommendedLoanAmount())
                            .recommendedLoanTenor(c.getRecommendedLoanTenor())
                            .comment(c.getComment())
                            .createdAt(c.getCreatedAt())
                            .officerName(c.getOfficerName())
                            .build())
                    .collect(Collectors.toList());

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, DATA_FETCHED_SUCCESSFULLY, responses);
        } catch (Exception e) {
            log.error(ERROR_FETCHING_DATA, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_DATA, null);
        }
    }
}