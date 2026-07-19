package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.account.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/md")
public class MDActionController {

    private final ManagementService managementService;

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @PostMapping("/approve-officer/{id}")
    public BaseApiResponse<?> approveOfficer(@PathVariable Long id) {
        return managementService.approveOfficer(id);
    }

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @GetMapping("/pending-officer")
    public BaseApiResponse<List<OfficerRegistrationResponseDTO>> getPendingOfficers() {
        return managementService.getPendingOfficers();
    }

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @GetMapping("/staff")
    public BaseApiResponse<List<OfficerRegistrationResponseDTO>> getAllStaff() {
        return managementService.getAllStaff();
    }

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @PutMapping("/staff/{id}/enable")
    public BaseApiResponse<?> enableStaff(@PathVariable Long id) {
        return managementService.enableStaff(id);
    }

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @PutMapping("/staff/{id}/disable")
    public BaseApiResponse<?> disableStaff(@PathVariable Long id) {
        return managementService.disableStaff(id);
    }

    @PreAuthorize("hasRole('MANAGING_DIRECTOR')")
    @DeleteMapping("/staff/{id}")
    public BaseApiResponse<?> deleteStaff(@PathVariable Long id) {
        return managementService.deleteStaff(id);
    }
}
