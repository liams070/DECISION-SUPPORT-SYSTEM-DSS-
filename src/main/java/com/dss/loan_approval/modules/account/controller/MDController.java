package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.MDCommentRequestDTO;
import com.dss.loan_approval.modules.account.service.MDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/md")
public class MDController {
    private final MDService mdService;

    @PostMapping("/comments")
    public BaseApiResponse submitComment(@RequestBody MDCommentRequestDTO dto) {
        return mdService.submitMDComment(dto);
    }

    @PostMapping("/approve/{customerId}")
    public BaseApiResponse approve(@PathVariable Long customerId) {
        return mdService.approveCustomer(customerId);
    }

    @PostMapping("/decline/{customerId}")
    public BaseApiResponse decline(@PathVariable Long customerId) {
        return mdService.declineCustomer(customerId);
    }

    @GetMapping("/profile/{customerId}")
    public BaseApiResponse getProfileWithComments(@PathVariable Long customerId) {
        return mdService.getCustomerProfileWithComments(customerId);
    }
}
