package com.dss.loan_approval.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileResponseDTO {
    private Long id;
    private String surname;
    private String firstName;
    private String otherName;
    private String bvn;
    private String ippisNumber;
    private String phoneNumber;
    private String stateOfOrigin;
    private String lga;
    private String town;
    private String officeAddress;
    private String residentialAddress;
    private String landmark;
    private String dob;
    private String nin;
    private String idType;

    private String passportPhotoUrl;
}
