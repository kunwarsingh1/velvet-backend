package com.velvet.investing.controller;

import com.velvet.investing.dto.response.ProjectionResponse;
import com.velvet.investing.service.ProjectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projection")
@RequiredArgsConstructor
@Tag(name = "Projection", description = "Financial projection endpoints")
@SecurityRequirement(name = "bearerAuth")
public class ProjectionController {
    
    private final ProjectionService projectionService;
    
    @GetMapping
    @Operation(summary = "Get User Projection", description = "Get financial projection for authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProjectionResponse> getUserProjection(Authentication authentication) {
        ProjectionResponse response = projectionService.getUserProjectionByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}