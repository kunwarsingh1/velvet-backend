package com.velvet.investing.service;

import com.velvet.investing.dto.request.CashFlowRequest;
import com.velvet.investing.dto.response.CashFlowResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CashFlowService {
    
    public CashFlowResponse analyzeCashFlow(CashFlowRequest request) {
        BigDecimal monthlySalary = request.getMonthlySalary();
        BigDecimal monthlyExpense = request.getMonthlyExpense();
        
        // Net deficit = Monthly Salary - Monthly Expense
        BigDecimal netDeficit = monthlySalary.subtract(monthlyExpense);
        
        // Saving rate = (Monthly Salary - Monthly Expense) / Monthly Salary * 100
        BigDecimal savingRate = netDeficit.divide(monthlySalary, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        
        // Annual surplus = (Annual Salary - Annual Expense)
        BigDecimal annualSalary = monthlySalary.multiply(BigDecimal.valueOf(12));
        BigDecimal annualExpense = monthlyExpense.multiply(BigDecimal.valueOf(12));
        BigDecimal annualSurplus = annualSalary.subtract(annualExpense);
        
        return new CashFlowResponse(
                monthlySalary,
                monthlyExpense,
                netDeficit,
                savingRate,
                annualSurplus
        );
    }
}