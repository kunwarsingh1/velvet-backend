package com.velvet.investing.controller;

import com.velvet.investing.dto.response.AssetResponse;
import com.velvet.investing.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
@Tag(name = "Assets", description = "User asset management endpoints")
public class AssetController {
    
    private final AssetService assetService;
    
    @GetMapping
    @Operation(summary = "Get User Assets", description = "Get all asset information for authenticated user")
    public ResponseEntity<AssetResponse> getUserAssets(Authentication authentication) {
        AssetResponse response = assetService.getUserAssetsByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }
}