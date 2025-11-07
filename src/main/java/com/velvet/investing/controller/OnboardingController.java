package com.velvet.investing.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvet.investing.dto.request.Step1Request;
import com.velvet.investing.dto.request.Step2Request;
import com.velvet.investing.dto.request.Step3Request;
import com.velvet.investing.dto.request.Step4Request;
import com.velvet.investing.dto.request.Step5Request;
import com.velvet.investing.dto.request.Step6Request;
import com.velvet.investing.dto.request.Step7Request;
import com.velvet.investing.service.OnboardingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/onboarding")
@Tag(name = "Onboarding", description = "User onboarding endpoints")
@SecurityRequirement(name = "bearerAuth")
public class OnboardingController {

    private final OnboardingService service;

    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    @PostMapping("/step1")
    @Operation(summary = "Step 1 - Basic Info", description = "Submit user basic information")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step1(@RequestBody Step1Request req) {
        try {
            String userId = service.saveStep1(req);
            return ResponseEntity.ok(Map.of("userId", userId));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage(), "type", e.getClass().getSimpleName()));
        }
    }

    @PostMapping("/step2")
    @Operation(summary = "Step 2 - Financial Info", description = "Submit user financial information")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step2(@RequestBody Step2Request req) {
        service.saveStep2(req);
        return ResponseEntity.ok(Map.of("status", "saved"));
    }

    @PostMapping("/step3")
    @Operation(summary = "Step 3 - Additional Info", description = "Submit additional user information")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step3(@RequestBody Step3Request req) {
        service.saveStep3(req);
        return ResponseEntity.ok(Map.of("status", "saved"));
    }

    @PostMapping("/step4")
    @Operation(summary = "Step 4 - Goals", description = "Submit user goals")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step4(@RequestBody Step4Request req) {
        service.saveStep4(req);
        return ResponseEntity.ok(Map.of("status", "saved"));
    }

    @PostMapping("/step5")
    @Operation(summary = "Step 5 - Assets", description = "Submit user assets")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step5(@RequestBody Step5Request req) {
        service.saveStep5(req);
        return ResponseEntity.ok(Map.of("status", "saved"));
    }

    @PostMapping("/step6")
    @Operation(summary = "Step 6 - Liabilities", description = "Submit user liabilities")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step6(@RequestBody Step6Request req) {
        service.saveStep6(req);
        return ResponseEntity.ok(Map.of("status", "saved"));
    }

    @PostMapping("/step7")
    @Operation(summary = "Step 7 - Final Step", description = "Complete onboarding process")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> step7(@RequestBody Step7Request req) {
        service.saveStep7(req);
        return ResponseEntity.ok(Map.of("status", "completed"));
    }

    @GetMapping("/summary/{userId}")
    @Operation(summary = "Get Onboarding Summary", description = "Get onboarding summary for a user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> summary(@PathVariable String userId) {
        Map<String, Object> summary = service.getSummary(userId);
        return ResponseEntity.ok(summary);
    }
}
