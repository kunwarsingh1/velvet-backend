package com.velvet.investing.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGoalRequest {
    
    private String userId;
    private String goalId;
    private String name;
    private Double targetAmount;
    private Integer targetYear;
    private String category;
    private Double currentExpenses;
    private Integer lifeExpectancy;
}