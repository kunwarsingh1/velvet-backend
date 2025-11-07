package com.velvet.investing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.response.UserProfileResponse;
import com.velvet.investing.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User profile management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/profile")
    @Operation(summary = "Get User Profile", description = "Get authenticated user profile information")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication) {
        UserProfileResponse response = userService.getUserProfileByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}