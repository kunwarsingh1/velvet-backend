package com.velvet.investing.service;

import com.velvet.investing.dto.request.MonthlyExpenseRequest;
import com.velvet.investing.dto.response.MonthlyExpenseResponse;
import com.velvet.investing.entity.FinancialInfo;
import com.velvet.investing.entity.LiabilityInfo;
import com.velvet.investing.entity.User;
import com.velvet.investing.repository.FinancialInfoRepository;
import com.velvet.investing.repository.LiabilityInfoRepository;
import com.velvet.investing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyExpenseService {
    
    private final FinancialInfoRepository financialInfoRepository;
    private final LiabilityInfoRepository liabilityInfoRepository;
    private final UserRepository userRepository;
    
    public MonthlyExpenseResponse calculateMonthlyExpense(MonthlyExpenseRequest request) {
        return getMonthlyExpenses(request.getUserId());
    }
    
    public MonthlyExpenseResponse getMonthlyExpensesByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return createEmptyResponse("USER_NOT_FOUND");
        return getMonthlyExpenses(user.getId());
    }
    
    public MonthlyExpenseResponse getMonthlyExpenses(String userId) {
        // Get basic monthly expenses
        FinancialInfo financialInfo = financialInfoRepository.findByUserId(userId).orElse(null);
        LiabilityInfo liabilityInfo = liabilityInfoRepository.findByUserId(userId).orElse(null);
        
        // Basic expense details
        Double monthlyHousing = financialInfo != null ? financialInfo.getMonthlyHousing() : 0.0;
        Double monthlyFood = financialInfo != null ? financialInfo.getMonthlyFood() : 0.0;
        Double monthlyTransport = financialInfo != null ? financialInfo.getMonthlyTransport() : 0.0;
        Double monthlyOthers = financialInfo != null ? financialInfo.getMonthlyOthers() : 0.0;
        Double basicExpenses = (monthlyHousing != null ? monthlyHousing : 0.0) + 
                              (monthlyFood != null ? monthlyFood : 0.0) + 
                              (monthlyTransport != null ? monthlyTransport : 0.0) + 
                              (monthlyOthers != null ? monthlyOthers : 0.0);
        
        // EMI details
        Double homeEMI = liabilityInfo != null ? liabilityInfo.getHomeEMI() : 0.0;
        Double vehicleEMI = liabilityInfo != null ? liabilityInfo.getVehicleEMI() : 0.0;
        Double personalEMI = liabilityInfo != null ? liabilityInfo.getPersonalEMI() : 0.0;
        Double creditCardMinPayment = liabilityInfo != null ? liabilityInfo.getCreditCardMinPayment() : 0.0;
        Double totalEmis = (homeEMI != null ? homeEMI : 0.0) + 
                          (vehicleEMI != null ? vehicleEMI : 0.0) + 
                          (personalEMI != null ? personalEMI : 0.0) + 
                          (creditCardMinPayment != null ? creditCardMinPayment : 0.0);
        
        Double totalMonthlyExpense = basicExpenses + totalEmis;
        
        return MonthlyExpenseResponse.builder()
                .monthlyHousing(monthlyHousing)
                .monthlyFood(monthlyFood)
                .monthlyTransport(monthlyTransport)
                .monthlyOthers(monthlyOthers)
                .basicExpenses(basicExpenses)
                .homeEMI(homeEMI)
                .vehicleEMI(vehicleEMI)
                .personalEMI(personalEMI)
                .creditCardMinPayment(creditCardMinPayment)
                .totalEmis(totalEmis)
                .totalMonthlyExpense(totalMonthlyExpense)
                .build();
    }
    
    private MonthlyExpenseResponse createEmptyResponse(String userId) {
        return MonthlyExpenseResponse.builder()
                .monthlyHousing(0.0)
                .monthlyFood(0.0)
                .monthlyTransport(0.0)
                .monthlyOthers(0.0)
                .basicExpenses(0.0)
                .homeEMI(0.0)
                .vehicleEMI(0.0)
                .personalEMI(0.0)
                .creditCardMinPayment(0.0)
                .totalEmis(0.0)
                .totalMonthlyExpense(0.0)
                .build();
    }
    
    private Double calculateBasicExpenses(FinancialInfo financialInfo) {
        if (financialInfo == null) return 0.0;
        
        return (financialInfo.getMonthlyHousing() != null ? financialInfo.getMonthlyHousing() : 0.0) +
               (financialInfo.getMonthlyFood() != null ? financialInfo.getMonthlyFood() : 0.0) +
               (financialInfo.getMonthlyTransport() != null ? financialInfo.getMonthlyTransport() : 0.0) +
               (financialInfo.getMonthlyOthers() != null ? financialInfo.getMonthlyOthers() : 0.0);
    }
    
    private Double calculateTotalEmis(LiabilityInfo liabilityInfo) {
        if (liabilityInfo == null) return 0.0;
        
        return (liabilityInfo.getHomeEMI() != null ? liabilityInfo.getHomeEMI() : 0.0) +
               (liabilityInfo.getVehicleEMI() != null ? liabilityInfo.getVehicleEMI() : 0.0) +
               (liabilityInfo.getPersonalEMI() != null ? liabilityInfo.getPersonalEMI() : 0.0) +
               (liabilityInfo.getCreditCardMinPayment() != null ? liabilityInfo.getCreditCardMinPayment() : 0.0);
    }
}