package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.PromotionDetailsRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.PromotionDetailsResponseDTO;
import com.dss.loan_approval.modules.model.entity.PromotionDetails;
import com.dss.loan_approval.modules.model.repository.PromotionDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionDetailsService {
    private final PromotionDetailsRepository promotionDetailsRepository;

    public BaseApiResponse<Void> submitPromotionDetails(PromotionDetailsRequestDTO dto) {
        try {
            PromotionDetails details = PromotionDetails.builder()
                    .ministry(dto.getMinistry())
                    .state(dto.getState())
                    .lastPromotionDate(dto.getLastPromotionDate())
                    .promotionLetterUrl(dto.getPromotionLetterUrl())
                    .payslipUrls(dto.getPayslipUrls())
                    .build();

            promotionDetailsRepository.save(details);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROMOTION_DETAILS_SUBMITTED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_SAVING_PROMOTION_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_PROMOTION_DETAILS, null);
        }
    }

    public BaseApiResponse <PromotionDetailsResponseDTO> getPromotionDetails(Long id) {
        try {
            PromotionDetails details = promotionDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(PROMOTION_DETAILS_NOT_FOUND));

            PromotionDetailsResponseDTO response = PromotionDetailsResponseDTO.builder()
                    .id(details.getId())
                    .ministry(details.getMinistry())
                    .state(details.getState())
                    .lastPromotionDate(details.getLastPromotionDate())
                    .promotionLetterUrl(details.getPromotionLetterUrl())
                    .payslipUrls(details.getPayslipUrls())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROMOTION_DETAILS_FETCHED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_PROMOTION_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_PROMOTION_DETAILS, null);
        }
    }

    public BaseApiResponse <Void> updatePromotionDetails(Long id, PromotionDetailsRequestDTO dto) {
        try {
            PromotionDetails details = promotionDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(PROMOTION_DETAILS_NOT_FOUND));

            details.setMinistry(dto.getMinistry());
            details.setState(dto.getState());
            details.setLastPromotionDate(dto.getLastPromotionDate());
            details.setPromotionLetterUrl(dto.getPromotionLetterUrl());
            details.setPayslipUrls(dto.getPayslipUrls());

            promotionDetailsRepository.save(details);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROMOTION_DETAILS_UPDATED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_UPDATING_PROMOTION_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_UPDATE_PROMOTION_DETAILS, null);
        }
    }

}
