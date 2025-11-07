package com.velvet.investing.service;

import com.velvet.investing.config.JwtUtils;
import com.velvet.investing.dto.request.LoginRequest;
import com.velvet.investing.dto.request.RegisterRequest;
import com.velvet.investing.dto.response.AuthResponse;
import com.velvet.investing.dto.response.UserProfileResponse;
import com.velvet.investing.entity.User;
import com.velvet.investing.exception.AuthenticationException;
import com.velvet.investing.repository.UserRepository;
import com.velvet.investing.service.UserDetailsServiceImpl.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException("Email is already in use");
        }
        
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AuthenticationException("Username is already taken");
        }
        
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        
        user = userRepository.save(user);
        
        String jwt = jwtUtils.generateTokenFromUsername(user.getEmail());
        
        return AuthResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .expiresAt(jwtUtils.getExpirationFromToken(jwt))
                .build();
    }
    
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByEmail(userPrincipal.getEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));
        
        return AuthResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .expiresAt(jwtUtils.getExpirationFromToken(jwt))
                .build();
    }
    
    public UserProfileResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        User user = userRepository.findByEmail(userPrincipal.getEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));
        
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .city(user.getCity())
                .mobileNumber(user.getMobileNumber())
                .dateOfBirth(user.getDateOfBirth())
                .retirementYear(user.getRetirementYear())
                .createdAt(user.getCreatedAt())
                .build();
    }
}