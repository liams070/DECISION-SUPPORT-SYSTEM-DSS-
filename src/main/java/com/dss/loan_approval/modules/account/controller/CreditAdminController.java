package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.CreditAdminRequestDTO;
import com.dss.loan_approval.modules.account.dto.response.CreditAdminResponseDTO;
import com.dss.loan_approval.modules.account.service.CreditAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-admin")
public class CreditAdminController {
    private final CreditAdminService creditAdminService;

    @PostMapping("/comments")
    public BaseApiResponse<Void> submitComment(@RequestBody CreditAdminRequestDTO dto) {
        return creditAdminService.submitComment(dto);
    }

    @GetMapping("/comments/{customerId}")
    public BaseApiResponse<List<CreditAdminResponseDTO>> getComments(@PathVariable Long customerId) {
        return creditAdminService.getComments(customerId);
    }
}
