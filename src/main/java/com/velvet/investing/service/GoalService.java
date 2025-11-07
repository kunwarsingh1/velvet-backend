package com.velvet.investing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.velvet.investing.dto.request.CreateGoalRequest;
import com.velvet.investing.dto.response.CreateGoalResponse;
import com.velvet.investing.entity.Goal;
import com.velvet.investing.entity.User;
import com.velvet.investing.repository.GoalRepository;
import com.velvet.investing.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {
    
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    
    public CreateGoalResponse createGoal(CreateGoalRequest request) {
        Goal goal = Goal.builder()
                .userId(request.getUserId())
                .goalId(request.getGoalId())
                .name(request.getName())
                .targetAmount(request.getTargetAmount())
                .targetYear(request.getTargetYear())
                .category(request.getCategory())
                .currentExpenses(request.getCurrentExpenses())
                .lifeExpectancy(request.getLifeExpectancy())
                .build();
        
        Goal savedGoal = goalRepository.save(goal);
        
        return CreateGoalResponse.builder()
                .id(savedGoal.getId())
                .userId(savedGoal.getUserId())
                .goalId(savedGoal.getGoalId())
                .name(savedGoal.getName())
                .targetAmount(savedGoal.getTargetAmount())
                .targetYear(savedGoal.getTargetYear())
                .category(savedGoal.getCategory())
                .currentExpenses(savedGoal.getCurrentExpenses())
                .lifeExpectancy(savedGoal.getLifeExpectancy())
                .message("Goal created successfully")
                .build();
    }
    
    public List<CreateGoalResponse> getUserGoals(String userId) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        return goals.stream()
                .map(goal -> CreateGoalResponse.builder()
                        .id(goal.getId())
                        .userId(goal.getUserId())
                        .goalId(goal.getGoalId())
                        .name(goal.getName())
                        .targetAmount(goal.getTargetAmount())
                        .targetYear(goal.getTargetYear())
                        .category(goal.getCategory())
                        .currentExpenses(goal.getCurrentExpenses())
                        .lifeExpectancy(goal.getLifeExpectancy())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }
    
    public List<CreateGoalResponse> getUserGoalsByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return java.util.Collections.emptyList();
        return getUserGoals(user.getId());
    }
    
    public boolean deleteGoal(Long goalId, String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;
        
        Goal goal = goalRepository.findById(goalId).orElse(null);
        if (goal == null) return false;
        
        // Verify the goal belongs to the user
        if (!goal.getUserId().equals(user.getId())) {
            return false;
        }
        
        goalRepository.deleteById(goalId);
        return true;
    }
}