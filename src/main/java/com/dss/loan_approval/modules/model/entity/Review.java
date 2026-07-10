package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LoanApplication application;

    @ManyToOne
    private User reviewer;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String comments;
    private String recommendation;
    private LocalDateTime submittedAt;
}
