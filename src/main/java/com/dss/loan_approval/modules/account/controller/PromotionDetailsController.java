package com.dss.loan_approval.modules.account.controller;

import com.dss.loan_approval.config.util.BaseApiResponse;
import com.dss.loan_approval.modules.account.dto.request.PromotionDetailsRequestDTO;
import com.dss.loan_approval.modules.account.service.PromotionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotion-details")
public class PromotionDetailsController {
    private final PromotionDetailsService promotionDetailsService;

    @PostMapping("/submit")
    public BaseApiResponse submitPromotionDetails(@RequestBody PromotionDetailsRequestDTO dto) {
        return promotionDetailsService.submitPromotionDetails(dto);
    }

    @GetMapping("/{id}")
    public BaseApiResponse getPromotionDetails(@PathVariable Long id) {
        return promotionDetailsService.getPromotionDetails(id);
    }

    @PutMapping("/{id}")
    public BaseApiResponse updatePromotionDetails(@PathVariable Long id, @RequestBody PromotionDetailsRequestDTO dto){
        return promotionDetailsService.updatePromotionDetails(id, dto);
    }
}
