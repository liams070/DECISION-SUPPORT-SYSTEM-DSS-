package com.dss.loan_approval.modules.account.service;

import com.dss.loan_approval.config.enums.LogAction;
import com.dss.loan_approval.modules.model.entity.AuditLog;
import com.dss.loan_approval.modules.model.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@Slf4j
@RequiredArgsConstructor
public class AuditService {
    private final AuditRepository auditRepository;
    public void logAction(LogAction action, String performedBy, String targetId,
                          String beforeState, String afterState,
                          String ipAddress, String browserType, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setTargetId(targetId != null ? targetId : "N/A");
        log.setBeforeState(beforeState != null ? beforeState : "N/A");
        log.setAfterState(afterState != null ? afterState : "N/A");
        log.setIpAddress(ipAddress != null ? ipAddress : "N/A");
        log.setBrowserType(browserType != null ? browserType : "N/A");
        log.setDetails(details != null ? details : "N/A");
        log.setTimestamp(LocalDateTime.now());
        auditRepository.save(log);
    }

    public void logAction(LogAction action, String performedBy, String details) {
        logAction(action, performedBy, "N/A", "N/A", "N/A", "N/A", "N/A", details);
    }
}
