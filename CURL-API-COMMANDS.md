# 🚀 VELVET INVESTMENT API - CURL COMMANDS & RESPONSES

## 📍 Base URLs
- **Local**: `http://localhost:8080`
- **Ngrok**: `https://derek-pseudoarticulate-overlusciously.ngrok-free.dev`

---

## 1. 👤 ONBOARDING CONTROLLER

### Step 1 - User Registration
```bash
curl -X POST http://localhost:8080/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "city": "Mumbai",
    "mobileNumber": "9876543210",
    "email": "john.doe@example.com",
    "dateOfBirth": "1990-05-15",
    "retirementYear": 2055
  }'
```
**Response:**
```json
{"userId": "4369"}
```

### Step 2 - Monthly Expenses
```bash
curl -X POST http://localhost:8080/api/onboarding/step2 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "monthlyExpenses": {
      "housing": 25000,
      "food": 8000,
      "transportation": 5000,
      "utilities": 3000,
      "entertainment": 4000,
      "healthcare": 2000,
      "other": 3000
    }
  }'
```
**Response:**
```json
{"status": "saved"}
```

### Step 3 - Current Assets
```bash
curl -X POST http://localhost:8080/api/onboarding/step3 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "currentAssets": {
      "cash": 100000,
      "bankDeposits": 500000,
      "mutualFunds": 300000,
      "stocks": 200000,
      "realEstate": 2000000,
      "gold": 150000,
      "other": 50000
    }
  }'
```
**Response:**
```json
{"status": "saved"}
```

### Step 4 - Current Liabilities
```bash
curl -X POST http://localhost:8080/api/onboarding/step4 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "currentLiabilities": {
      "homeLoan": 1500000,
      "carLoan": 300000,
      "personalLoan": 100000,
      "creditCard": 50000,
      "other": 25000
    },
    "loans": [{
      "type": "Home Loan",
      "amount": 1500000,
      "interestRate": 8.5,
      "tenure": 20
    }]
  }'
```
**Response:**
```json
{"status": "saved"}
```

### Step 5 - Financial Goals
```bash
curl -X POST http://localhost:8080/api/onboarding/step5 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "goals": [
      {
        "name": "Child Education",
        "targetAmount": 2000000,
        "targetYear": 2040,
        "priority": "High"
      },
      {
        "name": "House Purchase",
        "targetAmount": 5000000,
        "targetYear": 2030,
        "priority": "Medium"
      }
    ]
  }'
```
**Response:**
```json
{"status": "saved"}
```

### Step 6 - Insurance Information
```bash
curl -X POST http://localhost:8080/api/onboarding/step6 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "insurances": [
      {
        "type": "Life Insurance",
        "coverAmount": 5000000,
        "premium": 50000,
        "provider": "LIC"
      },
      {
        "type": "Health Insurance",
        "coverAmount": 1000000,
        "premium": 25000,
        "provider": "Star Health"
      }
    ]
  }'
```
**Response:**
```json
{"status": "saved"}
```

### Step 7 - Final Completion
```bash
curl -X POST http://localhost:8080/api/onboarding/step7 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "riskTolerance": "Moderate",
    "investmentExperience": "Beginner",
    "preferredInvestmentStyle": "Balanced"
  }'
```
**Response:**
```json
{"status": "completed"}
```

### Get Onboarding Summary
```bash
curl -X GET http://localhost:8080/api/onboarding/summary/4369
```
**Response:**
```json
{
  "user": {
    "id": "4369",
    "name": "John Doe",
    "city": "Mumbai",
    "mobileNumber": "9876543210",
    "email": "john.doe@example.com",
    "dateOfBirth": "1990-05-15",
    "retirementYear": 2055,
    "createdAt": "2025-11-04T15:36:57.715350Z",
    "updatedAt": "2025-11-04T15:36:57.715350Z"
  },
  "financialInfo": {...},
  "assetInfo": {...},
  "liabilityInfo": {...},
  "loans": [...],
  "goals": [...],
  "insurance": {...},
  "completion": {...}
}
```

---

## 2. 💰 CASH FLOW CONTROLLER

### Analyze Cash Flow
```bash
curl -X POST http://localhost:8080/api/cashflow/analyze \
  -H "Content-Type: application/json" \
  -d '{
    "monthlySalary": 75000,
    "monthlyExpense": 50000
  }'
```
**Response:**
```json
{
  "monthlySalary": 75000,
  "monthlyExpense": 50000,
  "netDeficit": 25000,
  "savingRate": 33.33,
  "annualSurplus": 300000
}
```

---

## 3. 🔥 FIRE CONTROLLER

### Calculate FIRE Number
```bash
curl -X POST http://localhost:8080/api/fire/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "annualExpenses": 600000,
    "currentPortfolioValue": 5000000
  }'
```
**Response:**
```json
{
  "fireNumber": 18000000,
  "firePercentage": 27.77777777777778
}
```

---

## 4. 📊 MONTHLY EXPENSE CONTROLLER

### Calculate Monthly Expense
```bash
curl -X POST http://localhost:8080/api/monthly-expense/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "basicExpenses": 40000,
    "emiPayments": 15000
  }'
```
**Response:**
```json
{
  "basicExpenses": 40000,
  "totalEmis": 15000,
  "totalMonthlyExpense": 55000
}
```

---

## 5. 🎯 GOAL CONTROLLER

### Calculate Goals
```bash
curl -X GET "http://localhost:8080/api/goals/calculate/4369?currentAge=30"
```
**Response:**
```json
[
  {
    "goalName": "Child Education",
    "targetAmount": 2000000,
    "targetYear": 2040,
    "yearsToGoal": 15,
    "monthlyInvestmentRequired": 8500,
    "currentProgress": 0,
    "feasibilityScore": "High"
  },
  {
    "goalName": "House Purchase",
    "targetAmount": 5000000,
    "targetYear": 2030,
    "yearsToGoal": 5,
    "monthlyInvestmentRequired": 75000,
    "currentProgress": 0,
    "feasibilityScore": "Medium"
  }
]
```

---

## 6. 💎 NET WORTH CONTROLLER

### Get Net Worth
```bash
curl -X GET http://localhost:8080/api/networth/4369
```
**Response:**
```json
{
  "totalAssets": 3300000,
  "totalLiabilities": 1975000,
  "netWorth": 1325000,
  "assetBreakdown": {
    "cash": 100000,
    "bankDeposits": 500000,
    "mutualFunds": 300000,
    "stocks": 200000,
    "realEstate": 2000000,
    "gold": 150000,
    "other": 50000
  },
  "liabilityBreakdown": {
    "homeLoan": 1500000,
    "carLoan": 300000,
    "personalLoan": 100000,
    "creditCard": 50000,
    "other": 25000
  }
}
```

### Get Portfolio Overview (GET)
```bash
curl -X GET http://localhost:8080/api/networth/portfolio/4369
```
**Response:**
```json
{
  "totalPortfolioValue": 3300000,
  "assetAllocation": {
    "equity": 15.15,
    "debt": 15.15,
    "realEstate": 60.61,
    "gold": 4.55,
    "cash": 4.55
  },
  "monthlyIncome": 75000,
  "monthlyExpenses": 50000,
  "investmentCapacity": 25000,
  "riskProfile": "Moderate"
}
```

---

## 7. 📈 PORTFOLIO OVERVIEW CONTROLLER

### Portfolio Overview (POST)
```bash
curl -X POST http://localhost:8080/api/portfolio/overview \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "4369",
    "year": 2024
  }'
```
**Response:**
```json
{
  "year": 2024,
  "portfolioValue": 3300000,
  "assetBreakdown": {
    "equity": 500000,
    "debt": 500000,
    "realEstate": 2000000,
    "gold": 150000,
    "cash": 150000
  },
  "performance": {
    "totalReturn": 12.5,
    "yearToDateReturn": 8.2,
    "volatility": 15.3
  },
  "recommendations": [
    "Consider increasing equity allocation",
    "Diversify into international funds",
    "Review real estate exposure"
  ]
}
```

---

## 🚨 ERROR HANDLING EXAMPLES

### Invalid Data (Negative Salary)
```bash
curl -X POST http://localhost:8080/api/cashflow/analyze \
  -H "Content-Type: application/json" \
  -d '{
    "monthlySalary": -1000,
    "monthlyExpense": 50000
  }'
```
**Response:**
```json
{
  "error": "Validation failed",
  "message": "Monthly salary cannot be negative",
  "status": 400
}
```

### Missing Required Fields
```bash
curl -X POST http://localhost:8080/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -d '{
    "city": "Mumbai",
    "email": "test@example.com"
  }'
```
**Response:**
```json
{
  "error": "Validation failed",
  "message": "Name is required",
  "status": 400
}
```

### Non-existent User
```bash
curl -X GET http://localhost:8080/api/onboarding/summary/non-existent-user
```
**Response:**
```json
{
  "error": "User not found",
  "message": "No user found with ID: non-existent-user",
  "status": 404
}
```

---

## 🔧 UTILITY ENDPOINTS

### Health Check (if available)
```bash
curl -X GET http://localhost:8080/actuator/health
```
**Response:**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "H2",
        "validationQuery": "isValid()"
      }
    }
  }
}
```

### H2 Database Console
```bash
# Access via browser
http://localhost:8080/h2-console
```

### Swagger UI
```bash
# Access via browser  
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 QUICK TEST SCRIPT

```bash
#!/bin/bash
# Complete API test flow

# Step 1: Create user
USER_ID=$(curl -s -X POST http://localhost:8080/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","city":"Mumbai","email":"test@test.com","dateOfBirth":"1990-01-01","retirementYear":2050}' \
  | jq -r '.userId')

echo "Created user: $USER_ID"

# Step 2: Test cash flow
curl -X POST http://localhost:8080/api/cashflow/analyze \
  -H "Content-Type: application/json" \
  -d '{"monthlySalary":75000,"monthlyExpense":50000}'

# Step 3: Test FIRE calculation
curl -X POST http://localhost:8080/api/fire/calculate \
  -H "Content-Type: application/json" \
  -d '{"annualExpenses":600000,"currentPortfolioValue":5000000}'

# Step 4: Get net worth
curl -X GET "http://localhost:8080/api/networth/$USER_ID"
```

---

## 🌐 NGROK USAGE

Replace `http://localhost:8080` with your ngrok URL:
```bash
# Example with ngrok
curl -X POST https://derek-pseudoarticulate-overlusciously.ngrok-free.dev/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Remote User","city":"Global","email":"remote@test.com"}'
```

All APIs are fully functional and ready for integration! 🚀