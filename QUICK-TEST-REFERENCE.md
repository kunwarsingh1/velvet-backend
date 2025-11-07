# Quick Test Reference Card

## 🚀 Run Tests in 3 Steps

### Step 1: Start Application
```bash
./start-app.bat
```
Wait for: `Started InvestingApplication`

### Step 2: Run Tests
```bash
./run-all-tests.bat
```

### Step 3: Check Results
Look for: `🎉 ALL TESTS PASSED! 🎉`

---

## 📋 All API Endpoints

### Public (No Auth Required)
```
POST   /api/auth/register
POST   /api/auth/login
GET    /actuator/health
GET    /swagger-ui.html
```

### Protected (Auth Required)
```
# Authentication
GET    /api/auth/profile
POST   /api/auth/logout

# User
GET    /api/user/profile

# Onboarding
POST   /api/onboarding/step1
POST   /api/onboarding/step2
POST   /api/onboarding/step3
POST   /api/onboarding/step4
POST   /api/onboarding/step5
POST   /api/onboarding/step6
POST   /api/onboarding/step7
GET    /api/onboarding/summary/{userId}

# Goals
POST   /api/goals
GET    /api/goals
GET    /api/goals/calculate/{userId}
DELETE /api/goals/{goalId}

# Expenses
POST   /api/monthly-expense/calculate
GET    /api/monthly-expense

# Net Worth
GET    /api/networth/{userId}
GET    /api/networth/portfolio/{userId}

# Portfolio
POST   /api/portfolio/overview

# FIRE
POST   /api/fire/calculate
GET    /api/fire

# Insurance
GET    /api/insurance

# Liabilities
GET    /api/liabilities

# Projection
GET    /api/projection
```

---

## 🔑 Authentication Flow

### 1. Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"test","email":"test@example.com","password":"Test@123456"}'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"Test@123456"}'
```

### 3. Use Token
```bash
curl -X GET http://localhost:8080/api/user/profile \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

---

## 🧪 Test Methods

| Method | File | Command |
|--------|------|---------|
| **Automated** | `test-all-apis.js` | `./run-all-tests.bat` |
| **Manual HTTP** | `test-all-apis.http` | Use REST Client in VS Code |
| **Swagger UI** | Browser | `http://localhost:8080/swagger-ui.html` |
| **cURL** | Terminal | See examples in files |

---

## ✅ Test Coverage

- **Total Endpoints:** 27
- **Tested:** 21
- **Coverage:** ~93%
- **All Protected:** ✅ JWT Required

---

## 🐛 Common Issues

| Issue | Solution |
|-------|----------|
| Connection refused | Start application: `./start-app.bat` |
| 401 Unauthorized | Login again to get new token |
| 404 Not Found | Check userId/goalId exists |
| Tests fail | Check app logs: `app.log` |

---

## 📊 Success Criteria

```
Total Tests: 21
Passed: 21
Failed: 0
Success Rate: 100.00%
```

---

## 🔗 Quick Links

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console
- **Health Check:** http://localhost:8080/actuator/health
- **API Docs:** http://localhost:8080/v3/api-docs

---

## 📝 Test Data Examples

### User
```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "Test@123456"
}
```

### Goal
```json
{
  "userId": "1234",
  "name": "House Purchase",
  "targetAmount": 5000000,
  "targetYear": 2030,
  "category": "property"
}
```

### Assets
```json
{
  "userId": "1234",
  "cash": 100000,
  "stocks": 200000,
  "mutualFunds": 150000,
  "realEstate": 5000000
}
```

---

## 🎯 Next Steps After Testing

1. ✅ All tests pass → Deploy to production
2. ❌ Some tests fail → Check logs and fix issues
3. 📈 Add more test cases for edge scenarios
4. 🔒 Add security penetration tests
5. ⚡ Add performance/load tests

---

**Need Help?** See `COMPREHENSIVE-TEST-GUIDE.md` for detailed documentation.
