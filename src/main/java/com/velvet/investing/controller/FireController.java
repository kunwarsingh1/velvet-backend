package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.request.FireRequest;
import com.velvet.investing.dto.response.FireResponse;
import com.velvet.investing.service.FireService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fire")
@RequiredArgsConstructor
@Tag(name = "FIRE", description = "Financial Independence Retire Early endpoints")
@SecurityRequirement(name = "bearerAuth")
public class FireController {
    
    private final FireService fireService;

    @GetMapping
    @Operation(summary = "Get User FIRE Data", description = "Get FIRE calculation data for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FireResponse> getUserFire(Authentication authentication) {
        FireResponse response = fireService.getUserFireByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/calculate")
    @Operation(summary = "Calculate FIRE", description = "Calculate FIRE number based on expenses and portfolio")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FireResponse> calculateFire(@RequestBody FireRequest request) {
        FireResponse response = fireService.calculateFire(request);
        return ResponseEntity.ok(response);
    }
}