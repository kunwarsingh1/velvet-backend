package com.velvet.investing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velvet.investing.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(String userId);
}