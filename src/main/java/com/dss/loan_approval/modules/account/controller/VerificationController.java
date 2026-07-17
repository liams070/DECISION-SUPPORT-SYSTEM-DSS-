package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.VerificationCommentRequestDTO;
import com.dss.loan_approval.modules.account.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verification")
public class VerificationController {
    private final VerificationService verificationService;


    @PostMapping("/comments")
    public BaseApiResponse submitComment(@RequestBody VerificationCommentRequestDTO dto){
        return verificationService.addComment(dto);
    }

    @PostMapping("/forward/{customerId}")
    public BaseApiResponse forwardToCompliance(@PathVariable Long customerId){
        return verificationService.forwardToCompliance(customerId);
    }

    @GetMapping("/comments")
    public BaseApiResponse getAllComments() {
        return verificationService.getAllVerificationComments();
    }

    @GetMapping("/comments/{customerId}")
    public BaseApiResponse getCommentsByCustomer(@PathVariable Long customerId) {
        return verificationService.getCommentsByCustomer(customerId);
    }

}
