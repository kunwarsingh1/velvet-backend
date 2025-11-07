package com.velvet.investing.controller;

import com.velvet.investing.dto.request.CashFlowRequest;
import com.velvet.investing.dto.response.CashFlowResponse;
import com.velvet.investing.service.CashFlowService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cashflow")
@RequiredArgsConstructor
@Tag(name = "Cash Flow", description = "Cash flow analysis endpoints")
public class CashFlowController {
    
    private final CashFlowService cashFlowService;
    
    @PostMapping("/analyze")
    @Operation(summary = "Analyze Cash Flow", description = "Calculate monthly cash flow analysis")
    public ResponseEntity<CashFlowResponse> analyzeCashFlow(@Valid @RequestBody CashFlowRequest request) {
        CashFlowResponse response = cashFlowService.analyzeCashFlow(request);
        return ResponseEntity.ok(response);
    }
}