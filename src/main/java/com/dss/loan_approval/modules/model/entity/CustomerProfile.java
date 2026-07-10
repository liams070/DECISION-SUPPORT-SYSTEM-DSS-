package com.dss.loan_approval.modules.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String passportPhotoUrl;

    private String dob;
    private String nin;
    private String idType;

}
