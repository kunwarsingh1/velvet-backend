package com.velvet.investing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId;
    
    private String goalId; // frontend id
    
    private String name;
    
    private Double targetAmount;
    
    private Integer targetYear;
    
    private String category;
    
    private Double currentExpenses; // optional for retirement
    
    private Integer lifeExpectancy; // optional for retirement
}