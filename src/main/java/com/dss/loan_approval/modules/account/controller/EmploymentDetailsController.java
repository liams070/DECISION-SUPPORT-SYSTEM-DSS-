package com.dss.loan_approval.modules.account.controller;


import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.EmploymentDetailsRequestDTO;
import com.dss.loan_approval.modules.account.service.EmploymentDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employment-details")
public class EmploymentDetailsController {
    private final EmploymentDetailsService employmentDetailsService;

    @PostMapping("/submit")
    public BaseApiResponse submitEmploymentDetails(@RequestBody EmploymentDetailsRequestDTO dto){
        return employmentDetailsService.submitEmploymentDetails(dto);
    }

    @GetMapping("/{id}")
    public BaseApiResponse getEmploymentDetails(@PathVariable Long id){
        return employmentDetailsService.getEmploymentDetails(id);
    }

    @PutMapping("/{id}")
    public BaseApiResponse updateEmploymentDetails(@PathVariable Long id, @RequestBody EmploymentDetailsRequestDTO dto){
        return employmentDetailsService.updateEmploymentDetails(id, dto);
    }

}
