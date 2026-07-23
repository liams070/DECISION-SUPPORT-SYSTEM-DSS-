package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_profiles")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "other_name")
    private String otherName;
    private String bvn;
    @Column(name = "ippis_number")
    private String ippisNumber;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "state_of_origin")
    private String stateOfOrigin;

    private String lga;
    private String town;

    @Column(name = "office_address")
    private String officeAddress;

    @Column(name = "residential_address")
    private String residentialAddress;

    private String landmark;

    @Column(name = "passport_photo")
    private String passportPhotoUrl;

    @Column(name = "dob")
    private LocalDate dob;

    private String nin;

    @Column(name = "id_type")
    private String idType;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
}
