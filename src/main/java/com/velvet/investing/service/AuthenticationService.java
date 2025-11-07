package com.velvet.investing.service;

import com.velvet.investing.entity.User;
import com.velvet.investing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    
    public String getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null ? user.getId() : null;
    }
}