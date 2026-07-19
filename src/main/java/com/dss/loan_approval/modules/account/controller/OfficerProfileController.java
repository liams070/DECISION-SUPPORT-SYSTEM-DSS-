package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.response.OfficerRegistrationResponseDTO;
import com.dss.loan_approval.modules.account.service.OfficerProfileService;
import com.dss.loan_approval.modules.model.entity.OfficerRegistration;
import com.dss.loan_approval.modules.model.repository.OfficerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/officers")
public class OfficerProfileController {
    private final OfficerProfileService officerProfileService;

    @GetMapping("/getAllOfficer")
    public BaseApiResponse getAllOfficer() {
        return officerProfileService.getAllOfficer();
    }

    @GetMapping("/{id}")
    public BaseApiResponse getOfficer(@PathVariable Long id){
        return officerProfileService.getOfficer(id);
    }

    @GetMapping("/auth/me")
    public BaseApiResponse getCurrentOfficer(Authentication authentication) {
        return officerProfileService.getCurrentOfficer(authentication);
    }

    @PutMapping("/{id}")
    public BaseApiResponse updateOfficer(@PathVariable Long id, @RequestBody OfficerRegistrationResponseDTO dto) {
        return officerProfileService.updateOfficer(id, dto);
    }

}
