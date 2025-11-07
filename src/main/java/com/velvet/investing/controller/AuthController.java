package com.velvet.investing.controller;

import com.velvet.investing.dto.request.LoginRequest;
import com.velvet.investing.dto.request.RegisterRequest;
import com.velvet.investing.dto.response.AuthResponse;
import com.velvet.investing.dto.response.UserProfileResponse;
import com.velvet.investing.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and authorization endpoints")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    @Operation(summary = "Logout user", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> logout() {
        // In a stateless JWT implementation, logout is handled client-side by removing the token
        // For token blacklisting, you would add the token to a blacklist here
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
    
    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileResponse> getCurrentUser() {
        UserProfileResponse response = authService.getCurrentUser();
        return ResponseEntity.ok(response);
    }
}