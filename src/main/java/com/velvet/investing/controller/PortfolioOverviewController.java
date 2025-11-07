package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.request.PortfolioOverviewRequest;
import com.velvet.investing.dto.response.PortfolioOverviewResponse;
import com.velvet.investing.service.PortfolioOverviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Tag(name = "Portfolio", description = "Portfolio overview endpoints")
@SecurityRequirement(name = "bearerAuth")
public class PortfolioOverviewController {
    
    private final PortfolioOverviewService portfolioOverviewService;
    
    @PostMapping("/overview")
    @Operation(summary = "Get Portfolio Overview", description = "Get detailed breakdown of all assets")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PortfolioOverviewResponse> getPortfolioOverview(@RequestBody PortfolioOverviewRequest request) {
        PortfolioOverviewResponse response = portfolioOverviewService.getPortfolioOverview(request);
        return ResponseEntity.ok(response);
    }
}