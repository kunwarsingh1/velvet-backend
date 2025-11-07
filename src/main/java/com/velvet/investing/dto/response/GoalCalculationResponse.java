package com.velvet.investing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalCalculationResponse {
    private String goalId;
    private String goalName;
    private String category;
    private Double requiredMonthlySIP;
}