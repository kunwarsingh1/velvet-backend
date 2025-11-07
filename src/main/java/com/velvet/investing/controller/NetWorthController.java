package com.velvet.investing.controller;

import com.velvet.investing.dto.response.NetWorthResponse;
import com.velvet.investing.dto.response.PortfolioOverviewResponse;
import com.velvet.investing.service.NetWorthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/networth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class NetWorthController {
    
    private final NetWorthService netWorthService;
    
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<NetWorthResponse> getNetWorth(@PathVariable String userId) {
        NetWorthResponse netWorth = netWorthService.calculateNetWorth(userId);
        return ResponseEntity.ok(netWorth);
    }
    
    @GetMapping("/portfolio/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PortfolioOverviewResponse> getPortfolioOverview(@PathVariable String userId) {
        PortfolioOverviewResponse portfolio = netWorthService.getPortfolioOverview(userId);
        return ResponseEntity.ok(portfolio);
    }
}