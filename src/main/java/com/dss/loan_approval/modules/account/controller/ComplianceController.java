package com.dss.loan_approval.modules.account.controller;


import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.ComplianceCommentRequestDTO;
import com.dss.loan_approval.modules.account.service.ComplianceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/compliance")
public class ComplianceController {
    private final ComplianceService complianceService;

    @PostMapping("/comments")
    public BaseApiResponse submitComment(@RequestBody ComplianceCommentRequestDTO dto) {
        return complianceService.submitComplianceComment(dto);
    }

    @PostMapping("/approve/{customerId}")
    public BaseApiResponse approve(@PathVariable Long customerId) {
        return complianceService.approveCustomer(customerId);
    }

    @PostMapping("/decline/{customerId}")
    public BaseApiResponse decline(@PathVariable Long customerId) {
        return complianceService.declineCustomer(customerId);
    }

    @GetMapping("/profile/{customerId}")
    public BaseApiResponse getProfileWithComments(@PathVariable Long customerId) {
        return complianceService.getCustomerProfileWithComments(customerId);
    }
}
