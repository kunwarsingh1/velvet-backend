package com.velvet.investing.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.*;
import com.velvet.investing.entity.*;
import com.velvet.investing.repository.*;
import com.velvet.investing.dto.request.*;

@Service
public class OnboardingService {

    private final UserRepository userRepository;
    private final FinancialInfoRepository financialInfoRepository;
    private final AssetInfoRepository assetInfoRepository;
    private final LiabilityInfoRepository liabilityInfoRepository;
    private final LoanRepository loanRepository;
    private final GoalRepository goalRepository;
    private final InsuranceRepository insuranceRepository;
    private final OnboardingCompletionRepository completionRepository;

    public OnboardingService(UserRepository userRepository,
            FinancialInfoRepository financialInfoRepository,
            AssetInfoRepository assetInfoRepository,
            LiabilityInfoRepository liabilityInfoRepository,
            LoanRepository loanRepository,
            GoalRepository goalRepository,
            InsuranceRepository insuranceRepository,
            OnboardingCompletionRepository completionRepository) {
        this.userRepository = userRepository;
        this.financialInfoRepository = financialInfoRepository;
        this.assetInfoRepository = assetInfoRepository;
        this.liabilityInfoRepository = liabilityInfoRepository;
        this.loanRepository = loanRepository;
        this.goalRepository = goalRepository;
        this.insuranceRepository = insuranceRepository;
        this.completionRepository = completionRepository;
    }

    @Transactional
    public String saveStep1(Step1Request req) {
        String userId = req.getUserId();
        User user;
        if (userId == null || userId.isEmpty() || !userRepository.existsById(userId)) {
            user = User.builder()
                    .name(req.getName())
                    .city(req.getCity())
                    .mobileNumber(req.getMobileNumber())
                    .email(req.getEmail())
                    .dateOfBirth(req.getDateOfBirth())
                    .retirementYear(req.getRetirementYear())
                    .build();
        } else {
            user = userRepository.findById(userId).orElseGet(() -> User.builder().id(userId).build());
            user.setName(req.getName());
            user.setCity(req.getCity());
            user.setMobileNumber(req.getMobileNumber());
            user.setEmail(req.getEmail());
            user.setDateOfBirth(req.getDateOfBirth());
            user.setRetirementYear(req.getRetirementYear());
        }
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public void saveStep2(Step2Request req) {
        FinancialInfo fi = financialInfoRepository.findByUserId(req.getUserId()).orElse(FinancialInfo.builder().userId(req.getUserId()).build());
        fi.setAnnualIncome(req.getAnnualIncome());
        if (req.getMonthlyExpenses() != null) {
            fi.setMonthlyHousing(req.getMonthlyExpenses().getHousing());
            fi.setMonthlyFood(req.getMonthlyExpenses().getFood());
            fi.setMonthlyTransport(req.getMonthlyExpenses().getTransport());
            fi.setMonthlyOthers(req.getMonthlyExpenses().getOthers());
        }
        financialInfoRepository.save(fi);
    }

    @Transactional
    public void saveStep3(Step3Request req) {
        AssetInfo ai = assetInfoRepository.findByUserId(req.getUserId()).orElse(AssetInfo.builder().userId(req.getUserId()).build());
        if (req.getCurrentAssets() != null) {
            ai.setMutualFunds(req.getCurrentAssets().getMutualFunds());
            ai.setStocks(req.getCurrentAssets().getStocks());
            ai.setFixedDeposits(req.getCurrentAssets().getFixedDeposits());
            ai.setRealEstate(req.getCurrentAssets().getRealEstate());
            ai.setGold(req.getCurrentAssets().getGold());
            ai.setCashAndBank(req.getCurrentAssets().getCashAndBank());
        }
        assetInfoRepository.save(ai);
    }

    @Transactional
    public void saveStep4(Step4Request req) {
        LiabilityInfo li = liabilityInfoRepository.findByUserId(req.getUserId()).orElse(LiabilityInfo.builder().userId(req.getUserId()).build());
        if (req.getCurrentLiabilities() != null) {
            li.setHomeLoan(req.getCurrentLiabilities().getHomeLoan());
            li.setVehicleLoan(req.getCurrentLiabilities().getVehicleLoan());
            li.setPersonalLoan(req.getCurrentLiabilities().getPersonalLoan());
            li.setCreditCard(req.getCurrentLiabilities().getCreditCard());
            li.setHomeEMI(req.getCurrentLiabilities().getHomeEMI());
            li.setVehicleEMI(req.getCurrentLiabilities().getVehicleEMI());
            li.setPersonalEMI(req.getCurrentLiabilities().getPersonalEMI());
            li.setCreditCardMinPayment(req.getCurrentLiabilities().getCreditCardMinPayment());
        }
        liabilityInfoRepository.save(li);

        // replace loans for the user (simple approach)
        List<Loan> existing = loanRepository.findByUserId(req.getUserId());
        loanRepository.deleteAll(existing);
        if (req.getLoans() != null) {
            for (Step4Request.LoanDto dto : req.getLoans()) {
                Loan loan = Loan.builder()
                        .userId(req.getUserId())
                        .loanId(dto.getId())
                        .type(dto.getType())
                        .customName(dto.getCustomName())
                        .outstandingAmount(dto.getOutstandingAmount())
                        .emi(dto.getEmi())
                        .tenureMonths(dto.getTenure())
                        .build();
                loanRepository.save(loan);
            }
        }
    }

    @Transactional
    public void saveStep5(Step5Request req) {
        // replace goals
        List<Goal> existing = goalRepository.findByUserId(req.getUserId());
        goalRepository.deleteAll(existing);
        if (req.getGoals() != null) {
            for (Step5Request.GoalDto g : req.getGoals()) {
                Goal goal = Goal.builder()
                        .userId(req.getUserId())
                        .goalId(g.getId())
                        .name(g.getName())
                        .targetAmount(g.getTargetAmount())
                        .targetYear(g.getTargetYear())
                        .category(g.getCategory())
                        .currentExpenses(g.getCurrentExpenses())
                        .lifeExpectancy(g.getLifeExpectancy())
                        .build();
                goalRepository.save(goal);
            }
        }
    }

    @Transactional
    public void saveStep6(Step6Request req) {
        Insurance ins = insuranceRepository.findByUserId(req.getUserId()).orElse(Insurance.builder().userId(req.getUserId()).build());
        if (req.getInsurance() != null) {
            ins.setTermLife(req.getInsurance().getTermLife());
            ins.setHealth(req.getInsurance().getHealth());
            ins.setCriticalIllness(req.getInsurance().getCriticalIllness());
        }
        insuranceRepository.save(ins);
    }

    @Transactional
    public void saveStep7(Step7Request req) {
        OnboardingCompletion comp = completionRepository.findByUserId(req.getUserId()).orElse(OnboardingCompletion.builder().userId(req.getUserId()).build());
        comp.setCompletedAt(Instant.now());
        completionRepository.save(comp);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getSummary(String userId) {
        Map<String, Object> out = new HashMap<>();
        userRepository.findById(userId).ifPresent(u -> out.put("user", u));
        financialInfoRepository.findByUserId(userId).ifPresent(fi -> out.put("financialInfo", fi));
        assetInfoRepository.findByUserId(userId).ifPresent(ai -> out.put("assetInfo", ai));
        liabilityInfoRepository.findByUserId(userId).ifPresent(li -> out.put("liabilityInfo", li));
        out.put("loans", loanRepository.findByUserId(userId));
        out.put("goals", goalRepository.findByUserId(userId));
        insuranceRepository.findByUserId(userId).ifPresent(ins -> out.put("insurance", ins));
        completionRepository.findByUserId(userId).ifPresent(c -> out.put("completion", c));
        return out;
    }
}
