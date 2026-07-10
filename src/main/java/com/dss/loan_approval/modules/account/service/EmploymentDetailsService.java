package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.EmploymentDetailsRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.EmploymentDetailsResponseDTO;
import com.dss.loan_approval.modules.model.entity.EmploymentDetails;
import com.dss.loan_approval.modules.model.repository.EmploymentDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmploymentDetailsService {
    private final EmploymentDetailsRepository employmentDetailsRepository;

    public BaseApiResponse<Void> submitEmploymentDetails(EmploymentDetailsRequestDTO dto) {
        try {
            EmploymentDetails details = EmploymentDetails.builder()
                    .officeName(dto.getOfficeName())
                    .officeAddressEmployment(dto.getOfficeAddressEmployment())
                    .yearOfEmployment(dto.getYearOfEmployment())
                    .employmentLetterUrl(dto.getEmploymentLetterUrl())
                    .build();

            employmentDetailsRepository.save(details);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, EMPLOYMENT_DETAILS_SUBMITTED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_SAVING_EMPLOYMENT_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_EMPLOYMENT_DETAILS, null);
        }
    }


    public BaseApiResponse <EmploymentDetailsResponseDTO> getEmploymentDetails(Long id) {
        try {
            EmploymentDetails details = employmentDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(EMPLOYMENT_DETAILS_NOT_FOUND));

            EmploymentDetailsResponseDTO response = EmploymentDetailsResponseDTO.builder()
                    .id(details.getId())
                    .officeName(details.getOfficeName())
                    .officeAddress(details.getOfficeAddressEmployment())
                    .yearOfEmployment(details.getYearOfEmployment())
                    .employmentLetterUrl(details.getEmploymentLetterUrl())
                    .build();
            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, EMPLOYMENT_DETAILS_FETCHED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_EMPLOYMENT_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_EMPLOYMENT_DETAILS, null);
        }
    }

    public BaseApiResponse<Void> updateEmploymentDetails(Long id, EmploymentDetailsRequestDTO dto) {
        try {
            EmploymentDetails details = employmentDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(EMPLOYMENT_DETAILS_NOT_FOUND));

            details.setOfficeName(dto.getOfficeName());
            details.setOfficeAddressEmployment(dto.getOfficeAddressEmployment());
            details.setYearOfEmployment(dto.getYearOfEmployment());
            details.setEmploymentLetterUrl(dto.getEmploymentLetterUrl());

            employmentDetailsRepository.save(details);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, EMPLOYMENT_DETAILS_UPDATED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_UPDATING_EMPLOYMENT_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_UPDATE_EMPLOYMENT_DETAILS, null);
        }
    }


}
