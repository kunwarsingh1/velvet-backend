package com.velvet.investing.dto.request;

import java.util.List;

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
public class Step5Request {
    
    private String userId;
    
    private List<GoalDto> goals;
    
    @Getter
    @Setter
    public static class GoalDto {
        private String id;
        private String name;
        private Double targetAmount;
        private Integer targetYear;
        private String category;
        private Double currentExpenses;
        private Integer lifeExpectancy;
    }
}