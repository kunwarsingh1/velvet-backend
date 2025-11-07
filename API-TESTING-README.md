# Velvet Investment API Testing Guide

## Overview
This guide provides comprehensive testing for all Velvet Investment APIs. The application has 5 main controllers with 13 total endpoints.

## API Endpoints Summary

### 1. Onboarding Controller (`/api/onboarding`)
- `POST /step1` - User basic information
- `POST /step2` - Monthly expenses  
- `POST /step3` - Current assets
- `POST /step4` - Current liabilities
- `POST /step5` - Financial goals
- `POST /step6` - Insurance information
- `POST /step7` - Final completion
- `GET /summary/{userId}` - Get onboarding summary

### 2. Cash Flow Controller (`/api/cashflow`)
- `POST /analyze` - Analyze monthly cash flow

### 3. FIRE Controller (`/api/fire`)
- `POST /calculate` - Calculate FIRE number and percentage

### 4. Goal Controller (`/api/goals`)
- `GET /calculate/{userId}?currentAge={age}` - Calculate financial goals

### 5. Net Worth Controller (`/api/networth`)
- `GET /{userId}` - Get net worth calculation
- `GET /portfolio/{userId}?year={year}` - Get portfolio overview

## Prerequisites

1. **Start the Application**
   ```bash
   mvnw spring-boot:run
   ```
   The application will run on `http://localhost:8080`

2. **Database Setup**
   - Ensure MySQL is running on localhost:3306
   - Database: `onboarding_db`
   - Username: `root`
   - Password: `Geet@123`

## Testing Methods

### Method 1: Automated Node.js Tests (Recommended)

1. **Install Node.js** (if not already installed)
2. **Run the test script:**
   ```bash
   run-tests.bat
   ```
   This will:
   - Install required dependencies (axios)
   - Run comprehensive API tests
   - Provide detailed results and success rate

### Method 2: REST Client (VS Code)

1. **Install REST Client extension** in VS Code
2. **Open `api-tests.http`** file
3. **Click "Send Request"** above each test
4. **Update userId** in subsequent requests with the response from step1

### Method 3: Command Line with curl

1. **Run the curl test script:**
   ```bash
   test-with-curl.bat
   ```

### Method 4: Postman

1. **Import the requests** from `api-tests.http`
2. **Create environment variables:**
   - `baseUrl`: `http://localhost:8080`
   - `userId`: (update after step1 response)

## Test Scenarios Covered

### ✅ Happy Path Tests
- Complete onboarding flow (7 steps)
- Cash flow analysis with valid data
- FIRE calculation
- Goal and net worth calculations (requires existing data)

### ✅ Error Handling Tests
- Invalid data validation (negative values)
- Missing required fields
- Non-existent user IDs

### ✅ Integration Tests
- End-to-end onboarding process
- Data persistence across steps
- Cross-controller data dependencies

## Expected Results

### Successful Responses
- **Onboarding Step 1**: Returns `{"userId": "generated-id"}`
- **Other Steps**: Returns `{"status": "saved"}` or `{"status": "completed"}`
- **Cash Flow**: Returns analysis with deficit, saving rate, surplus
- **FIRE**: Returns fire number and percentage
- **Summary**: Returns complete user financial profile

### Error Responses
- **400 Bad Request**: Invalid input data
- **404 Not Found**: Non-existent user
- **500 Internal Server Error**: Database/server issues

## Troubleshooting

### Common Issues

1. **Connection Refused**
   - Ensure Spring Boot app is running
   - Check port 8080 is not blocked

2. **Database Errors**
   - Verify MySQL is running
   - Check database credentials in `application.properties`

3. **Validation Errors**
   - Check request body format matches DTOs
   - Ensure required fields are provided

### Debug Steps

1. **Check application logs** for detailed error messages
2. **Verify database connectivity:**
   ```sql
   SHOW DATABASES;
   USE onboarding_db;
   SHOW TABLES;
   ```
3. **Test individual endpoints** before running full suite

## API Documentation

The application includes Swagger/OpenAPI documentation. Once running, visit:
- `http://localhost:8080/swagger-ui.html` (if configured)
- `http://localhost:8080/v3/api-docs` (OpenAPI JSON)

## Test Data Examples

### Sample User Data
```json
{
  "name": "John Doe",
  "city": "Mumbai",
  "mobileNumber": "9876543210", 
  "email": "john.doe@example.com",
  "dateOfBirth": "1990-05-15",
  "retirementYear": 2055
}
```

### Sample Cash Flow Data
```json
{
  "monthlySalary": 75000,
  "monthlyExpense": 50000
}
```

### Sample FIRE Data
```json
{
  "annualExpenses": 600000,
  "currentPortfolioValue": 5000000
}
```

## Success Metrics

A successful test run should show:
- ✅ All onboarding steps complete successfully
- ✅ Cash flow and FIRE calculations return valid results
- ✅ Error handling works for invalid inputs
- ✅ Database operations persist data correctly
- ✅ Overall success rate > 80%

## Next Steps

After confirming APIs work:
1. **Add unit tests** for service layer
2. **Implement integration tests** with TestContainers
3. **Add performance testing** with load testing tools
4. **Set up CI/CD pipeline** with automated testing
5. **Add API monitoring** and health checks