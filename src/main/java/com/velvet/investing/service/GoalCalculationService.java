package com.velvet.investing.service;

import com.velvet.investing.dto.response.GoalCalculationResponse;
import com.velvet.investing.entity.Goal;
import com.velvet.investing.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalCalculationService {
    
    private final GoalRepository goalRepository;
    
    public List<GoalCalculationResponse> calculateGoals(String userId, Integer currentAge) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        
        return goals.stream()
                .map(goal -> calculateGoal(goal, currentAge))
                .collect(Collectors.toList());
    }
    
    private GoalCalculationResponse calculateGoal(Goal goal, Integer currentAge) {
        Double requiredSIP;
        
        if ("retirement".equalsIgnoreCase(goal.getCategory())) {
            requiredSIP = calculateRetirementSIP(goal, currentAge);
        } else {
            requiredSIP = calculateOtherGoalSIP(goal);
        }
        
        return GoalCalculationResponse.builder()
                .goalId(goal.getGoalId())
                .goalName(goal.getName())
                .category(goal.getCategory())
                .requiredMonthlySIP(requiredSIP)
                .build();
    }
    
    private Double calculateRetirementSIP(Goal goal, Integer currentAge) {
        int retirementAge = goal.getTargetYear();
        int yearsToRetirement = retirementAge - currentAge;
        double monthlyExpenses = goal.getCurrentExpenses();
        int lifeExpectancy = goal.getLifeExpectancy();
        
        if (Math.pow(1.1, yearsToRetirement) - 1 == 0) {
            return 0.0;
        }
        
        double numerator = (648.0/11.0) * monthlyExpenses * Math.pow(1.06, yearsToRetirement) * 
                          (1 - Math.pow(1.0188679, lifeExpectancy - retirementAge));
        double denominator = Math.pow(1.1, yearsToRetirement) - 1;
        
        return numerator / denominator;
    }
    
    private Double calculateOtherGoalSIP(Goal goal) {
        int currentYear = LocalDate.now().getYear();
        int yearsToGoal = goal.getTargetYear() - currentYear;
        double currentValue = goal.getTargetAmount();
        
        if (Math.pow(1.1, yearsToGoal) - 1 == 0) {
            return 0.0;
        }
        
        double numerator = currentValue * Math.pow(1.08, yearsToGoal);
        double denominator = 11 * (Math.pow(1.1, yearsToGoal) - 1);
        
        return numerator / denominator;
    }
}