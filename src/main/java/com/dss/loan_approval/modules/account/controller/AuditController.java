package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.modules.model.entity.AuditLog;
import com.dss.loan_approval.modules.model.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditController {
    private final AuditRepository auditRepository;
    @GetMapping("/logs")
    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    public List<AuditLog> getAllLogs() {
        return auditRepository.findAll();
    }
}
