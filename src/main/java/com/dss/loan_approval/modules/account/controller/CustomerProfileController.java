package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.CustomerProfileRequestDTO;
import com.dss.loan_approval.modules.account.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer-profile")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;

    @PostMapping("/submit")
    public BaseApiResponse submitProfile(@RequestBody CustomerProfileRequestDTO dto){
        return customerProfileService.saveProfile(dto);
    }

    @GetMapping("/{id}")
    public BaseApiResponse getProfile(@PathVariable Long id){
        return customerProfileService.getProfile(id);
    }

    @GetMapping("/getAllProfile")
    public BaseApiResponse getAllProfiles() {
        return customerProfileService.getAllProfiles();
    }

    @PutMapping("/{id}")
    public BaseApiResponse updateProfile(@PathVariable Long id, @RequestBody CustomerProfileRequestDTO dto){
        return customerProfileService.updateProfile(id, dto);
    }
}
