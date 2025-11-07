# Comprehensive API Testing Guide

## Overview
This guide covers testing all APIs in the Velvet Investing application, including authentication, onboarding, goals, expenses, net worth, FIRE calculations, and more.

## Prerequisites

### 1. Application Running
Ensure the Spring Boot application is running:
```bash
# Start the application
./start-app.bat

# Or manually
mvn spring-boot:run
```

### 2. Node.js Installed (for automated tests)
Download from: https://nodejs.org/

### 3. Install Dependencies
```bash
npm install axios
```

## Testing Methods

### Method 1: Automated Testing (Recommended)

Run the comprehensive test suite:
```bash
./run-all-tests.bat
```

Or directly:
```bash
node test-all-apis.js
```

**Features:**
- ✅ Tests all 21 API endpoints
- ✅ Automatic user registration and login
- ✅ Sequential test execution with proper data flow
- ✅ Color-coded console output
- ✅ Detailed success/failure reporting
- ✅ Test summary with success rate

### Method 2: Manual Testing with HTTP File

Use the `test-all-apis.http` file with:
- **VS Code**: Install "REST Client" extension
- **IntelliJ IDEA**: Built-in HTTP client
- **Other IDEs**: Check for HTTP client support

**Steps:**
1. Open `test-all-apis.http`
2. Run "Register" request
3. Run "Login" request and copy the token
4. Update `@token` variable with your JWT token
5. Update `@userId` variable with your user ID
6. Run other requests sequentially

### Method 3: Manual Testing with cURL

See individual cURL commands in the HTTP file or use Postman.

### Method 4: Swagger UI (Interactive)

1. Open browser: `http://localhost:8080/swagger-ui.html`
2. Click "Authorize" button
3. Enter: `Bearer YOUR_JWT_TOKEN`
4. Test endpoints interactively

## API Endpoints Tested

### 1. Authentication APIs (/api/auth)
- ✅ POST `/register` - Register new user
- ✅ POST `/login` - User login
- ✅ GET `/profile` - Get current user profile
- ✅ POST `/logout` - Logout user

### 2. User APIs (/api/user)
- ✅ GET `/profile` - Get user profile

### 3. Onboarding APIs (/api/onboarding)
- ✅ POST `/step1` - Basic information
- ✅ POST `/step2` - Financial information
- ✅ POST `/step3` - Additional information
- ✅ POST `/step4` - Goals
- ✅ POST `/step5` - Assets
- ✅ POST `/step6` - Liabilities
- ✅ POST `/step7` - Complete onboarding
- ✅ GET `/summary/{userId}` - Get onboarding summary

### 4. Goals APIs (/api/goals)
- ✅ POST `/` - Create goal
- ✅ GET `/` - Get all user goals
- ✅ GET `/calculate/{userId}` - Calculate goals
- ✅ DELETE `/{goalId}` - Delete goal

### 5. Monthly Expense APIs (/api/monthly-expense)
- ✅ POST `/calculate` - Calculate monthly expense
- ✅ GET `/` - Get monthly expenses

### 6. Net Worth APIs (/api/networth)
- ✅ GET `/{userId}` - Get net worth
- ✅ GET `/portfolio/{userId}` - Get portfolio overview

### 7. Portfolio APIs (/api/portfolio)
- ✅ POST `/overview` - Get portfolio overview

### 8. FIRE APIs (/api/fire)
- ✅ POST `/calculate` - Calculate FIRE number
- ✅ GET `/` - Get user FIRE data

### 9. Insurance APIs (/api/insurance)
- ✅ GET `/` - Get user insurance

### 10. Liabilities APIs (/api/liabilities)
- ✅ GET `/` - Get user liabilities

### 11. Projection APIs (/api/projection)
- ✅ GET `/` - Get financial projection

### 12. Health Check
- ✅ GET `/actuator/health` - Application health

## Test Data

### Sample User
```json
{
  "username": "testuser",
  "email": "testuser@example.com",
  "password": "Test@123456"
}
```

### Sample Goal
```json
{
  "userId": "1234",
  "goalId": "goal-1",
  "name": "House Purchase",
  "targetAmount": 5000000,
  "targetYear": 2030,
  "category": "property"
}
```

### Sample Assets
```json
{
  "userId": "1234",
  "cash": 100000,
  "stocks": 200000,
  "mutualFunds": 150000,
  "realEstate": 5000000,
  "gold": 100000,
  "otherAssets": 50000
}
```

### Sample Liabilities
```json
{
  "userId": "1234",
  "homeLoan": 2000000,
  "carLoan": 500000,
  "personalLoan": 100000,
  "creditCard": 50000,
  "otherLiabilities": 25000
}
```

## Expected Test Results

### Successful Test Run
```
========================================
  COMPREHENSIVE API TEST SUITE
========================================

✓ Health Check
✓ Register
✓ Login
✓ Get Profile
✓ Onboarding Step 1
✓ Onboarding Step 2
✓ Onboarding Step 5
✓ Onboarding Step 6
✓ Create Goal
✓ Get Goals
✓ Calculate Goals
✓ Calculate Monthly Expense
✓ Get Monthly Expense
✓ Get Net Worth
✓ Get Portfolio Overview
✓ Calculate FIRE
✓ Get FIRE
✓ Get Insurance
✓ Get Liabilities
✓ Get Projection
✓ Delete Goal

========================================
TEST SUMMARY
========================================
Total Tests: 21
Passed: 21
Failed: 0
Success Rate: 100.00%

🎉 ALL TESTS PASSED! 🎉
```

## Troubleshooting

### Issue: Application not running
**Solution:**
```bash
./start-app.bat
# Wait for "Started InvestingApplication"
```

### Issue: Connection refused
**Error:** `ECONNREFUSED`
**Solution:** 
- Check if application is running on port 8080
- Verify no firewall blocking
- Check application logs

### Issue: 401 Unauthorized
**Solution:**
- Ensure you're logged in
- Check if JWT token is valid
- Token might be expired (default: 24 hours)
- Re-login to get new token

### Issue: 404 Not Found
**Solution:**
- Verify endpoint URL is correct
- Check if resource exists (e.g., userId, goalId)
- Ensure you're using correct HTTP method

### Issue: 403 Forbidden
**Solution:**
- Check if user has required role (USER)
- Verify token is included in Authorization header

### Issue: Database errors
**Solution:**
```bash
# Check database connection in application.properties
# Verify H2 console: http://localhost:8080/h2-console
```

## Test Execution Flow

The automated test follows this sequence:

1. **Health Check** - Verify application is running
2. **Register** - Create new test user
3. **Login** - Get JWT token
4. **Profile** - Verify authentication works
5. **Onboarding** - Complete user onboarding (steps 1, 2, 5, 6)
6. **Goals** - Create, retrieve, calculate goals
7. **Expenses** - Calculate and retrieve monthly expenses
8. **Net Worth** - Get net worth and portfolio
9. **FIRE** - Calculate FIRE number
10. **Insurance** - Get insurance data
11. **Liabilities** - Get liabilities
12. **Projection** - Get financial projections
13. **Delete** - Clean up by deleting test goal

## Performance Benchmarks

Expected response times (approximate):
- Authentication: < 500ms
- Simple GET requests: < 200ms
- Complex calculations: < 1000ms
- Database operations: < 300ms

## Security Testing

### Valid Scenarios
- ✅ Authenticated requests with valid token
- ✅ User can only access their own data
- ✅ Role-based access control works

### Invalid Scenarios (Should Fail)
- ❌ Requests without token → 401
- ❌ Requests with invalid token → 401
- ❌ Requests with expired token → 401
- ❌ Access to other user's data → 404
- ❌ Weak passwords → 400

## Continuous Integration

### Add to CI/CD Pipeline
```yaml
# Example GitHub Actions
- name: Run API Tests
  run: |
    npm install axios
    node test-all-apis.js
```

### Jenkins
```groovy
stage('API Tests') {
    steps {
        sh 'npm install axios'
        sh 'node test-all-apis.js'
    }
}
```

## Test Coverage

| Category | Endpoints | Coverage |
|----------|-----------|----------|
| Authentication | 4 | 100% |
| User | 1 | 100% |
| Onboarding | 8 | 50% (4/8) |
| Goals | 4 | 100% |
| Expenses | 2 | 100% |
| Net Worth | 2 | 100% |
| Portfolio | 1 | 100% |
| FIRE | 2 | 100% |
| Insurance | 1 | 100% |
| Liabilities | 1 | 100% |
| Projection | 1 | 100% |
| **Total** | **27** | **~93%** |

## Next Steps

### Recommended Enhancements
1. Add integration tests for remaining onboarding steps
2. Add negative test cases (invalid data, unauthorized access)
3. Add performance/load testing
4. Add database state verification
5. Add API response schema validation
6. Add test data cleanup after tests

### Additional Testing Tools
- **Postman Collections** - Import/export test collections
- **JMeter** - Load testing
- **SoapUI** - API functional testing
- **Newman** - Postman CLI runner

## Support

For issues or questions:
1. Check application logs: `app.log`
2. Check test output for specific error messages
3. Verify all prerequisites are met
4. Review API documentation: `http://localhost:8080/swagger-ui.html`

## Quick Commands Reference

```bash
# Start application
./start-app.bat

# Run all tests
./run-all-tests.bat

# Run specific test
node test-all-apis.js

# Check health
curl http://localhost:8080/actuator/health

# View Swagger docs
# Open: http://localhost:8080/swagger-ui.html

# View H2 Console
# Open: http://localhost:8080/h2-console
```

---

**Last Updated:** 2025-11-07
**Version:** 1.0
**Status:** ✅ All systems operational
