# Complete API Curl Commands for Velvet Investment Platform

Base URL: `http://localhost:8080`

## Authentication APIs

### 1. Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "Password123!"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "userId": "user123",
  "username": "testuser",
  "email": "test@example.com",
  "expiresAt": "2024-01-02T10:30:00Z"
}
```

### 2. Login User
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Password123!"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "userId": "user123",
  "username": "testuser",
  "email": "test@example.com",
  "expiresAt": "2024-01-02T10:30:00Z"
}
```

### 3. Get Current User Profile
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
```json
{
  "userId": "user123",
  "username": "testuser",
  "email": "test@example.com",
  "name": "Test User",
  "age": 30,
  "occupation": "Software Engineer"
}
```

### 4. Logout User
```bash
curl -X POST http://localhost:8080/api/auth/logout \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
```json
{
  "message": "Logged out successfully"
}
```

## Asset Management APIs

### 5. Get User Assets
```bash
curl -X GET http://localhost:8080/api/assets \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
```json
{
  "userId": "user123",
  "cashAndBank": 500000.0,
  "fixedDeposits": 1000000.0,
  "mutualFunds": 800000.0,
  "stocks": 600000.0,
  "realEstate": 3000000.0,
  "gold": 200000.0,
  "totalAssets": 6100000.0
}
```

## Cash Flow APIs

### 6. Analyze Cash Flow
```bash
curl -X POST http://localhost:8080/api/cashflow/analyze \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "monthlySalary": 50000.00,
    "monthlyExpense": 30000.00
  }'
```

**Response:**
```json
{
  "monthlySalary": 50000.00,
  "monthlyExpense": 30000.00,
  "netDeficit": 20000.00,
  "savingRate": 40.0,
  "annualSurplus": 240000.00
}
```

## FIRE (Financial Independence Retire Early) APIs

### 7. Get User FIRE Data
```bash
curl -X GET http://localhost:8080/api/fire \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 8. Calculate FIRE
```bash
curl -X POST http://localhost:8080/api/fire/calculate \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "currentAge": 30,
    "retirementAge": 60,
    "monthlyExpenses": 50000,
    "currentSavings": 1000000,
    "monthlyInvestment": 25000,
    "expectedReturn": 12.0
  }'
```

## Goal Management APIs

### 9. Create Goal
```bash
curl -X POST http://localhost:8080/api/goals \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "goalName": "House Purchase",
    "targetAmount": 5000000,
    "targetAge": 35,
    "priority": "HIGH"
  }'
```

### 10. Get User Goals
```bash
curl -X GET http://localhost:8080/api/goals \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 11. Calculate Goals
```bash
curl -X GET "http://localhost:8080/api/goals/calculate/user123?currentAge=30" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 12. Delete Goal
```bash
curl -X DELETE http://localhost:8080/api/goals/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Insurance APIs

### 13. Get User Insurance
```bash
curl -X GET http://localhost:8080/api/insurance \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Liability Management APIs

### 14. Get User Liabilities
```bash
curl -X GET http://localhost:8080/api/liabilities \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Monthly Expense APIs

### 15. Calculate Monthly Expense
```bash
curl -X POST http://localhost:8080/api/monthly-expense/calculate \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "basicExpenses": 25000,
    "emiAmount": 15000,
    "otherExpenses": 5000
  }'
```

### 16. Get Monthly Expenses
```bash
curl -X GET http://localhost:8080/api/monthly-expense \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Net Worth APIs

### 17. Get Net Worth
```bash
curl -X GET http://localhost:8080/api/networth/user123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 18. Get Portfolio Overview (via NetWorth)
```bash
curl -X GET http://localhost:8080/api/networth/portfolio/user123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Onboarding APIs

### 19. Onboarding Step 1 - Basic Info
```bash
curl -X POST http://localhost:8080/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "email": "test@example.com",
    "name": "Test User",
    "age": 30,
    "occupation": "Software Engineer",
    "annualIncome": 1200000
  }'
```

**Response:**
```json
{
  "userId": "user123"
}
```

### 20. Onboarding Step 2 - Financial Info
```bash
curl -X POST http://localhost:8080/api/onboarding/step2 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "monthlyIncome": 100000,
    "monthlyExpenses": {
      "food": 15000,
      "transport": 8000,
      "utilities": 5000,
      "entertainment": 7000,
      "others": 5000
    }
  }'
```

**Response:**
```json
{
  "status": "saved"
}
```

### 21. Onboarding Step 3 - Additional Info
```bash
curl -X POST http://localhost:8080/api/onboarding/step3 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "currentAssets": {
      "cash": 500000,
      "bankDeposits": 1000000,
      "mutualFunds": 800000,
      "stocks": 600000,
      "realEstate": 3000000,
      "gold": 200000,
      "others": 100000
    }
  }'
```

### 22. Onboarding Step 4 - Goals
```bash
curl -X POST http://localhost:8080/api/onboarding/step4 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "currentLiabilities": {
      "homeLoan": 2000000,
      "carLoan": 500000,
      "personalLoan": 200000,
      "creditCard": 50000,
      "others": 100000
    },
    "loans": [
      {
        "loanType": "HOME_LOAN",
        "principalAmount": 2000000,
        "interestRate": 8.5,
        "tenureMonths": 240,
        "emiAmount": 20000
      }
    ]
  }'
```

### 23. Onboarding Step 5 - Assets
```bash
curl -X POST http://localhost:8080/api/onboarding/step5 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "goals": [
      {
        "goalName": "House Purchase",
        "targetAmount": 5000000,
        "targetAge": 35,
        "priority": "HIGH"
      }
    ]
  }'
```

### 24. Onboarding Step 6 - Liabilities
```bash
curl -X POST http://localhost:8080/api/onboarding/step6 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "insurance": [
      {
        "insuranceType": "LIFE",
        "coverageAmount": 5000000,
        "premiumAmount": 50000,
        "policyTerm": 20
      }
    ]
  }'
```

### 25. Onboarding Step 7 - Final Step
```bash
curl -X POST http://localhost:8080/api/onboarding/step7 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "riskTolerance": "MODERATE",
    "investmentExperience": "INTERMEDIATE",
    "preferredInvestmentHorizon": "LONG_TERM"
  }'
```

### 26. Get Onboarding Summary
```bash
curl -X GET http://localhost:8080/api/onboarding/summary/user123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Portfolio APIs

### 27. Get Portfolio Overview
```bash
curl -X POST http://localhost:8080/api/portfolio/overview \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": "user123",
    "includeProjections": true,
    "timeHorizon": "5_YEARS"
  }'
```

## Projection APIs

### 28. Get User Projection
```bash
curl -X GET http://localhost:8080/api/projection \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## User Profile APIs

### 29. Get User Profile
```bash
curl -X GET http://localhost:8080/api/user/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Usage Instructions

1. **First, register a user** using API #1
2. **Login** using API #2 to get the JWT token
3. **Replace `YOUR_JWT_TOKEN`** in all subsequent requests with the actual token received from login
4. **Replace `user123`** with the actual user ID returned from registration or step 1 of onboarding
5. **Adjust request payloads** as needed based on your specific data requirements

## Authentication Flow

```bash
# Step 1: Register
REGISTER_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "email": "test@example.com", "password": "Password123!"}')

# Step 2: Login and extract token
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com", "password": "Password123!"}')

# Extract token (assuming response contains "token" field)
TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.token')

# Step 3: Use token in subsequent requests
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer $TOKEN"
```

### Standard Response Patterns

**Success Responses:** HTTP 200 with JSON data  
**Error Responses:** HTTP 4xx/5xx with error details:
```json
{
  "error": "Error message",
  "timestamp": "2024-01-01T10:00:00Z",
  "status": 400
}
```

**Onboarding Steps 2-7 Response:**
```json
{
  "status": "saved"
}
```

**Step 7 Final Response:**
```json
{
  "status": "completed"
}
```

## Notes

- All authenticated endpoints require the `Authorization: Bearer YOUR_JWT_TOKEN` header
- Replace placeholder values in request bodies with actual data
- The server runs on port 8080 by default
- JWT tokens expire after 24 hours (86400 seconds)
- Some endpoints require specific user roles (USER role is required for most endpoints)
- Response examples show typical successful responses - actual values may vary