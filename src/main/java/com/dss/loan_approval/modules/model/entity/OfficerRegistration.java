package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.Gender;
import com.dss.loan_approval.config.enums.OfficerRole;
import com.dss.loan_approval.config.enums.OfficerVerificationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "officer_registration")
@Builder
public class OfficerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;

    private String password;

    @Enumerated(EnumType.STRING)
    private OfficerRole role;

    @Enumerated(EnumType.STRING)
    private OfficerVerificationStatus status;

//    private boolean emailVerified;

    private String resetCode;
}
