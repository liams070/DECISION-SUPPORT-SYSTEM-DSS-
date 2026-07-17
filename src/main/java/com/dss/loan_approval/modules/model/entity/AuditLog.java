package com.dss.loan_approval.modules.model.entity;

import com.dss.loan_approval.config.enums.LogAction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LogAction action;

    private String performedBy;
    private String targetId;
    private String beforeState;
    private String afterState;
    private String ipAddress;
    private String browserType;
    private LocalDateTime timestamp;
    private String details;
}
