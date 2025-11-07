package com.velvet.investing.service;

import com.velvet.investing.dto.response.UserProfileResponse;
import com.velvet.investing.entity.User;
import com.velvet.investing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserProfileResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        
        if (user == null) {
            return UserProfileResponse.builder()
                    .id(userId)
                    .username("User not found")
                    .email("N/A")
                    .build();
        }
        
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .mobileNumber(user.getMobileNumber())
                .retirementYear(user.getRetirementYear())
                .build();
    }
    
    public UserProfileResponse getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return UserProfileResponse.builder()
                    .id("USER_NOT_FOUND")
                    .username("User not found")
                    .email(email)
                    .build();
        }
        return getUserProfile(user.getId());
    }
}