package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.request.MonthlyExpenseRequest;
import com.velvet.investing.dto.response.MonthlyExpenseResponse;
import com.velvet.investing.service.MonthlyExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/monthly-expense")
@RequiredArgsConstructor
@Tag(name = "Monthly Expense", description = "Monthly expense calculation endpoints")
@SecurityRequirement(name = "bearerAuth")
public class MonthlyExpenseController {
    
    private final MonthlyExpenseService monthlyExpenseService;
    
    @PostMapping("/calculate")
    @Operation(summary = "Calculate Monthly Expense", description = "Calculate total monthly expense including basic expenses and EMIs")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MonthlyExpenseResponse> calculateMonthlyExpense(@RequestBody MonthlyExpenseRequest request) {
        MonthlyExpenseResponse response = monthlyExpenseService.calculateMonthlyExpense(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Get Monthly Expenses", description = "Get monthly expenses for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MonthlyExpenseResponse> getMonthlyExpenses(Authentication authentication) {
        MonthlyExpenseResponse response = monthlyExpenseService.getMonthlyExpensesByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}