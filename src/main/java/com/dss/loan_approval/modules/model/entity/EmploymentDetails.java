package com.dss.loan_approval.modules.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String officeName;
    private String officeAddressEmployment;
    private String yearOfEmployment;
    private String employmentLetterUrl;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
