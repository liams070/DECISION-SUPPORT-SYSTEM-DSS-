package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.CreditAdminRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.CreditAdminResponseDTO;
import com.dss.loan_approval.modules.model.entity.CreditAdminComment;
import com.dss.loan_approval.modules.model.repository.CreditAdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditAdminService {
    private final CreditAdminRepository creditAdminRepository;

    public BaseApiResponse<Void> submitComment(CreditAdminRequestDTO dto) {
        try {
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
