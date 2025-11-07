package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.response.LiabilityResponse;
import com.velvet.investing.service.LiabilityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/liabilities")
@RequiredArgsConstructor
@Tag(name = "Liabilities", description = "User liability management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class LiabilityController {
    
    private final LiabilityService liabilityService;
    
    @GetMapping
    @Operation(summary = "Get User Liabilities", description = "Get all liability information for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LiabilityResponse> getUserLiabilities(Authentication authentication) {
        LiabilityResponse response = liabilityService.getUserLiabilitiesByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}