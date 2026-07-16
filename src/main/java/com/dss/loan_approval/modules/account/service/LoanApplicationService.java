package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.LoanApplicationRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.LoanApplicationResponseDTO;
import com.dss.loan_approval.modules.model.entity.LoanApplication;
import com.dss.loan_approval.modules.model.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanApplicationService {
    private final LoanApplicationRepository loanApplicationRepository;

    public BaseApiResponse <Void> submitLoanApplication(LoanApplicationRequestDTO dto) {
        try {
            LoanApplication application = LoanApplication.builder()
                    .runningLoan(dto.getRunningLoan())
                    .newRequest(dto.getNewRequest())
                    .currentLoanBalance(dto.getCurrentLoanBalance())
                    .tenor(dto.getTenor())
                    .monthlyNet(dto.getMonthlyNet())
                    .monthlyGross(dto.getMonthlyGross())
                    .passportPhotoUrl(dto.getPassportPhotoUrl())
                    .build();

            loanApplicationRepository.save(application);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOAN_DETAILS_SUBMITTED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_SAVING_LOAN_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_LOAN_DETAILS, null);
        }
    }
    public BaseApiResponse <LoanApplicationResponseDTO> getLoanApplication(Long id) {
        try {
            LoanApplication application = loanApplicationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(LOAN_DETAILS_NOT_FOUND));

            LoanApplicationResponseDTO response = LoanApplicationResponseDTO.builder()
                    .id(application.getId())
                    .newRequest(application.getNewRequest())
                    .runningLoan(application.getRunningLoan())
                    .currentLoanBalance(application.getCurrentLoanBalance())
                    .tenor(application.getTenor())
                    .monthlyNet(application.getMonthlyNet())
                    .monthlyGross(application.getMonthlyGross())
                    .passportPhotoUrl(application.getPassportPhotoUrl())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOAN_DETAILS_FETCHED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_LOAN_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_LOAN_DETAILS, null);
        }
    }

    public BaseApiResponse<List<LoanApplicationResponseDTO>> getAllLoanApplications() {
        try {
            List<LoanApplicationResponseDTO> applications = loanApplicationRepository.findAll()
                    .stream()
                    .map(application -> LoanApplicationResponseDTO.builder()
                            .id(application.getId())
                            .runningLoan(application.getRunningLoan())
                            .currentLoanBalance(application.getCurrentLoanBalance())
                            .newRequest(application.getNewRequest())
                            .tenor(application.getTenor())
                            .monthlyNet(application.getMonthlyNet())
                            .monthlyGross(application.getMonthlyGross())
                            .passportPhotoUrl(application.getPassportPhotoUrl())
                            .build())
                    .collect(Collectors.toList());

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOAN_DETAILS_FETCHED_SUCCESSFULLY, applications);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_LOAN_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_LOAN_DETAILS, null);
        }
    }


    public BaseApiResponse<Void> updateLoanApplication(Long id, LoanApplicationRequestDTO dto) {
        try {
            LoanApplication application = loanApplicationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(LOAN_DETAILS_NOT_FOUND));

            application.setRunningLoan(dto.getRunningLoan());
            application.setNewRequest(dto.getNewRequest());
            application.setCurrentLoanBalance(dto.getCurrentLoanBalance());
            application.setTenor(dto.getTenor());
            application.setMonthlyNet(dto.getMonthlyNet());
            application.setMonthlyGross(dto.getMonthlyGross());
            application.setPassportPhotoUrl(dto.getPassportPhotoUrl());

            loanApplicationRepository.save(application);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, LOAN_DETAILS_UPDATED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_UPDATING_LOAN_DETAILS, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_UPDATE_LOAN_DETAILS, null);
        }
    }


}
