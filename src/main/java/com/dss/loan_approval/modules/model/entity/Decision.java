package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.DecisionType;
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
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LoanApplication application;

    @ManyToOne
    private User md;

    @Enumerated(EnumType.STRING)
    private DecisionType decision;

    private String reason;
    private LocalDateTime decidedAt;
}
