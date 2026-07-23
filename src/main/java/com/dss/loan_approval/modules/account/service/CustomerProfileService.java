package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.CustomerStatus;
import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.CustomerProfileRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.CustomerProfileResponseDTO;
import com.dss.loan_approval.modules.model.entity.CustomerProfile;
import com.dss.loan_approval.modules.model.repository.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.dss.loan_approval.config.util.AppCodeConstants.*;
import static com.dss.loan_approval.config.util.AppTextConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerProfileService {
    private final CustomerProfileRepository customerProfileRepository;

    public BaseApiResponse<CustomerProfileResponseDTO> saveProfile(CustomerProfileRequestDTO dto) {
        try {
            CustomerProfile profile = CustomerProfile.builder()
                    .surname(dto.getSurname())
                    .firstName(dto.getFirstName())
                    .otherName(dto.getOtherName())
                    .bvn(dto.getBvn())
                    .ippisNumber(dto.getIppisNumber())
                    .phoneNumber(dto.getPhoneNumber())
                    .stateOfOrigin(dto.getStateOfOrigin())
                    .lga(dto.getLga())
                    .town(dto.getTown())
                    .officeAddress(dto.getOfficeAddress())
                    .residentialAddress(dto.getResidentialAddress())
                    .landmark(dto.getLandmark())
                    .dob(LocalDate.parse(dto.getDob()))
                    .nin(dto.getNin())
                    .idType(dto.getIdType())
                    .passportPhotoUrl(dto.getPassportPhotoUrl())
                    .status(CustomerStatus.SUBMITTED)
                    .build();

            CustomerProfile saved = customerProfileRepository.save(profile);

            CustomerProfileResponseDTO response = CustomerProfileResponseDTO.builder()
                    .id(saved.getId())
                    .surname(saved.getSurname())
                    .firstName(saved.getFirstName())
                    .otherName(saved.getOtherName())
                    .bvn(saved.getBvn())
                    .ippisNumber(saved.getIppisNumber())
                    .phoneNumber(saved.getPhoneNumber())
                    .stateOfOrigin(saved.getStateOfOrigin())
                    .lga(saved.getLga())
                    .town(saved.getTown())
                    .officeAddress(saved.getOfficeAddress())
                    .residentialAddress(saved.getResidentialAddress())
                    .landmark(saved.getLandmark())
                    .dob(saved.getDob().toString())
                    .nin(saved.getNin())
                    .idType(saved.getIdType())
                    .passportPhotoUrl(saved.getPassportPhotoUrl())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROFILE_SUBMITTED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_SAVING_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_SUBMIT_PROFILE, null);
        }
    }


    public BaseApiResponse <CustomerProfileResponseDTO> getProfile(Long id) {
        try {
            CustomerProfile profile = customerProfileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOTFOUND));

            CustomerProfileResponseDTO response = CustomerProfileResponseDTO.builder()
                    .id(profile.getId())
                    .surname(profile.getSurname())
                    .firstName(profile.getFirstName())
                    .otherName(profile.getOtherName())
                    .bvn(profile.getBvn())
                    .ippisNumber(profile.getIppisNumber())
                    .phoneNumber(profile.getPhoneNumber())
                    .stateOfOrigin(profile.getStateOfOrigin())
                    .lga(profile.getLga())
                    .town(profile.getTown())
                    .officeAddress(profile.getOfficeAddress())
                    .residentialAddress(profile.getResidentialAddress())
                    .landmark(profile.getLandmark())
                    .dob(profile.getDob().toString())
                    .nin(profile.getNin())
                    .idType(profile.getIdType())
                    .passportPhotoUrl(profile.getPassportPhotoUrl())
                    .build();

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROFILE_FETCHED_SUCCESSFULLY, response);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_PROFILE,null);
        }

    }

    public BaseApiResponse<List<CustomerProfileResponseDTO>> getAllProfiles() {
        try {
            List<CustomerProfileResponseDTO> profiles = customerProfileRepository.findAll()
                    .stream()
                    .map(profile -> CustomerProfileResponseDTO.builder()
                            .id(profile.getId())
                            .surname(profile.getSurname())
                            .firstName(profile.getFirstName())
                            .otherName(profile.getOtherName())
                            .bvn(profile.getBvn())
                            .ippisNumber(profile.getIppisNumber())
                            .phoneNumber(profile.getPhoneNumber())
                            .stateOfOrigin(profile.getStateOfOrigin())
                            .lga(profile.getLga())
                            .town(profile.getTown())
                            .officeAddress(profile.getOfficeAddress())
                            .residentialAddress(profile.getResidentialAddress())
                            .landmark(profile.getLandmark())
                            .dob(profile.getDob().toString())
                            .nin(profile.getNin())
                            .idType(profile.getIdType())
                            .passportPhotoUrl(profile.getPassportPhotoUrl())
                            .build())
                    .collect(Collectors.toList());

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, ALL_PROFILE_RETRIVED_SUCCESSFULLY, profiles);

        } catch (Exception e) {
            log.error(ERROR_FETCHING_ALL_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_FETCH_ALL_PROFILE, null);
        }
    }


    public BaseApiResponse <Void> updateProfile(Long id, CustomerProfileRequestDTO dto) {
        try {
            CustomerProfile profile = customerProfileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(PROFILE_NOTFOUND));

            profile.setSurname(dto.getSurname());
            profile.setFirstName(dto.getFirstName());
            profile.setOtherName(dto.getOtherName());
            profile.setBvn(dto.getBvn());
            profile.setIppisNumber(dto.getIppisNumber());
            profile.setPhoneNumber(dto.getPhoneNumber());
            profile.setStateOfOrigin(dto.getStateOfOrigin());
            profile.setLga(dto.getLga());
            profile.setTown(dto.getTown());
            profile.setOfficeAddress(dto.getOfficeAddress());
            profile.setResidentialAddress(dto.getResidentialAddress());
            profile.setLandmark(dto.getLandmark());
            profile.setDob(LocalDate.parse(dto.getDob()));
            profile.setNin(dto.getNin());
            profile.setIdType(dto.getIdType());
            profile.setPassportPhotoUrl(dto.getPassportPhotoUrl());

            customerProfileRepository.save(profile);

            return new BaseApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, PROFILE_UPDATED_SUCCESSFULLY, null);

        } catch (Exception e) {
            log.error(ERROR_UPDATING_PROFILE, e);
            return new BaseApiResponse<>(SERVER_ERROR_CODE, SERVER_ERROR_MSG, FAILED_TO_UPDATE_PROFILE, null);
        }
    }

}
