package com.velvet.investing.service;

import com.velvet.investing.dto.response.LiabilityResponse;
import com.velvet.investing.entity.LiabilityInfo;
import com.velvet.investing.entity.User;
import com.velvet.investing.repository.LiabilityInfoRepository;
import com.velvet.investing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiabilityService {
    
    private final LiabilityInfoRepository liabilityInfoRepository;
    private final UserRepository userRepository;
    
    public LiabilityResponse getUserLiabilitiesByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return createEmptyResponse("USER_NOT_FOUND");
        }
        return getUserLiabilities(user.getId());
    }
    
    public LiabilityResponse getUserLiabilities(String userId) {
        LiabilityInfo liabilityInfo = liabilityInfoRepository.findByUserId(userId).orElse(null);
        
        if (liabilityInfo == null) {
            return createEmptyResponse(userId);
        }
        
        Double homeLoan = liabilityInfo.getHomeLoan() != null ? liabilityInfo.getHomeLoan() : 0.0;
        Double vehicleLoan = liabilityInfo.getVehicleLoan() != null ? liabilityInfo.getVehicleLoan() : 0.0;
        Double personalLoan = liabilityInfo.getPersonalLoan() != null ? liabilityInfo.getPersonalLoan() : 0.0;
        Double creditCard = liabilityInfo.getCreditCard() != null ? liabilityInfo.getCreditCard() : 0.0;
        
        Double homeEMI = liabilityInfo.getHomeEMI() != null ? liabilityInfo.getHomeEMI() : 0.0;
        Double vehicleEMI = liabilityInfo.getVehicleEMI() != null ? liabilityInfo.getVehicleEMI() : 0.0;
        Double personalEMI = liabilityInfo.getPersonalEMI() != null ? liabilityInfo.getPersonalEMI() : 0.0;
        Double creditCardMinPayment = liabilityInfo.getCreditCardMinPayment() != null ? liabilityInfo.getCreditCardMinPayment() : 0.0;
        
        Double totalLiabilities = homeLoan + vehicleLoan + personalLoan + creditCard;
        Double totalEMIs = homeEMI + vehicleEMI + personalEMI + creditCardMinPayment;
        
        return LiabilityResponse.builder()
                .userId(userId)
                .homeLoan(homeLoan)
                .vehicleLoan(vehicleLoan)
                .personalLoan(personalLoan)
                .creditCard(creditCard)
                .homeEMI(homeEMI)
                .vehicleEMI(vehicleEMI)
                .personalEMI(personalEMI)
                .creditCardMinPayment(creditCardMinPayment)
                .totalLiabilities(totalLiabilities)
                .totalEMIs(totalEMIs)
                .build();
    }
    
    private LiabilityResponse createEmptyResponse(String userId) {
        return LiabilityResponse.builder()
                .userId(userId)
                .homeLoan(0.0)
                .vehicleLoan(0.0)
                .personalLoan(0.0)
                .creditCard(0.0)
                .homeEMI(0.0)
                .vehicleEMI(0.0)
                .personalEMI(0.0)
                .creditCardMinPayment(0.0)
                .totalLiabilities(0.0)
                .totalEMIs(0.0)
                .build();
    }
}