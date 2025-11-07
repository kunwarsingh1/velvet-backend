package com.velvet.investing.service;

import com.velvet.investing.dto.response.InsuranceResponse;
import com.velvet.investing.entity.Insurance;
import com.velvet.investing.repository.InsuranceRepository;
import com.velvet.investing.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    
    private final InsuranceRepository insuranceRepository;
    private final AuthenticationService authenticationService;
    
    public InsuranceResponse getUserInsurance(String userId) {
        Insurance insurance = insuranceRepository.findByUserId(userId).orElse(null);
        
        if (insurance == null) {
            return InsuranceResponse.builder()
                    .userId(userId)
                    .termLife(0.0)
                    .health(0.0)
                    .criticalIllness(0.0)
                    .totalInsurance(0.0)
                    .build();
        }
        
        Double termLife = insurance.getTermLife() != null ? insurance.getTermLife() : 0.0;
        Double health = insurance.getHealth() != null ? insurance.getHealth() : 0.0;
        Double criticalIllness = insurance.getCriticalIllness() != null ? insurance.getCriticalIllness() : 0.0;
        Double totalInsurance = termLife + health + criticalIllness;
        
        return InsuranceResponse.builder()
                .userId(userId)
                .termLife(termLife)
                .health(health)
                .criticalIllness(criticalIllness)
                .totalInsurance(totalInsurance)
                .build();
    }
    
    public InsuranceResponse getUserInsuranceByEmail(String email) {
        String userId = authenticationService.getUserIdByEmail(email);
        if (userId == null) return createEmptyResponse("USER_NOT_FOUND");
        return getUserInsurance(userId);
    }
    
    private InsuranceResponse createEmptyResponse(String userId) {
        return InsuranceResponse.builder()
                .userId(userId)
                .termLife(0.0)
                .health(0.0)
                .criticalIllness(0.0)
                .totalInsurance(0.0)
                .build();
    }
}