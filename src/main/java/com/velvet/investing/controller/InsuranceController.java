package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.response.InsuranceResponse;
import com.velvet.investing.service.InsuranceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
@Tag(name = "Insurance", description = "User insurance management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class InsuranceController {
    
    private final InsuranceService insuranceService;
    
    @GetMapping
    @Operation(summary = "Get User Insurance", description = "Get all insurance information for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<InsuranceResponse> getUserInsurance(Authentication authentication) {
        InsuranceResponse response = insuranceService.getUserInsuranceByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}