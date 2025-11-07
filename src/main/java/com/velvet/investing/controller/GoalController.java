package com.velvet.investing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.request.CreateGoalRequest;
import com.velvet.investing.dto.response.CreateGoalResponse;
import com.velvet.investing.dto.response.GoalCalculationResponse;
import com.velvet.investing.service.GoalCalculationService;
import com.velvet.investing.service.GoalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@Tag(name = "Goals", description = "Goal management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class GoalController {
    
    private final GoalCalculationService goalCalculationService;
    private final GoalService goalService;
    
    @PostMapping
    @Operation(summary = "Create Goal", description = "Create a new financial goal for a user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CreateGoalResponse> createGoal(@RequestBody CreateGoalRequest request) {
        CreateGoalResponse response = goalService.createGoal(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Get User Goals", description = "Get all goals for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CreateGoalResponse>> getUserGoals(Authentication authentication) {
        List<CreateGoalResponse> goals = goalService.getUserGoalsByEmail(authentication.getName());
        return ResponseEntity.ok(goals);
    }
    
    @GetMapping("/calculate/{userId}")
    @Operation(summary = "Calculate Goals", description = "Calculate goal requirements for a user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<GoalCalculationResponse>> calculateGoals(
            @PathVariable String userId,
            @RequestParam Integer currentAge) {
        List<GoalCalculationResponse> calculations = goalCalculationService.calculateGoals(userId, currentAge);
        return ResponseEntity.ok(calculations);
    }
    
    @DeleteMapping("/{goalId}")
    @Operation(summary = "Delete Goal", description = "Delete a goal for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteGoal(
            @PathVariable Long goalId,
            Authentication authentication) {
        boolean deleted = goalService.deleteGoal(goalId, authentication.getName());
        
        if (deleted) {
            return ResponseEntity.ok(java.util.Map.of(
                "message", "Goal deleted successfully",
                "goalId", goalId
            ));
        } else {
            return ResponseEntity.status(404).body(java.util.Map.of(
                "error", "Goal not found or you don't have permission to delete it",
                "goalId", goalId
            ));
        }
    }
}
