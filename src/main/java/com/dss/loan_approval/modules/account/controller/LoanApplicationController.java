package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.LoanApplicationRequestDTO;
import com.dss.loan_approval.modules.account.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/loan-application")

public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;

    @PostMapping("/submit")
    public BaseApiResponse submitLoanApplication(@RequestBody LoanApplicationRequestDTO dto){
        return loanApplicationService.submitLoanApplication(dto);
    }

    @GetMapping("/{id}")
    public BaseApiResponse getLoanApplication(@PathVariable Long id){
        return loanApplicationService.getLoanApplication(id);
    }

    @PutMapping("/{id}")
    public BaseApiResponse updateLoanApplication(@PathVariable Long id, LoanApplicationRequestDTO dto){
        return loanApplicationService.updateLoanApplication(id, dto);
    }

}
