# 🚀 QUICK API TESTING GUIDE

## Prerequisites

1. **Start the Spring Boot Application**
   ```bash
   mvnw spring-boot:run
   ```
   Wait for the application to start on `http://localhost:8080`

2. **Ensure Node.js is installed** (for automated tests)
   - Download from: https://nodejs.org/

## 🎯 OPTION 1: Run Comprehensive Tests (Recommended)

```bash
run-comprehensive-tests.bat
```

This will:
- ✅ Test all 14 API endpoints
- ✅ Verify database connectivity (H2)
- ✅ Check data persistence and flow
- ✅ Test error handling
- ✅ Validate API documentation
- ✅ Generate detailed report

## 🔍 OPTION 2: Database-Specific Testing

```bash
node verify-database.js
```

This will:
- ✅ Test H2 database integration
- ✅ Verify data persistence across tables
- ✅ Show actual request/response data
- ✅ Test cross-controller data access

## 📋 OPTION 3: Manual Testing (VS Code REST Client)

1. Install **REST Client** extension in VS Code
2. Open `api-tests.http`
3. Click "Send Request" above each test
4. Update `userId` from step1 response for subsequent tests

## 🌐 OPTION 4: Browser Testing

1. **Swagger UI**: http://localhost:8080/swagger-ui/index.html
2. **H2 Console**: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: `password`

## 📊 What Gets Tested

### API Endpoints (14 total):
- **Onboarding**: 8 endpoints (step1-7 + summary)
- **Cash Flow**: 1 endpoint (analyze)
- **FIRE**: 1 endpoint (calculate)
- **Goals**: 1 endpoint (calculate)
- **Net Worth**: 2 endpoints (networth + portfolio)
- **Portfolio**: 1 endpoint (overview)

### Database Integration:
- ✅ H2 in-memory database connectivity
- ✅ JPA/Hibernate entity mapping
- ✅ Data persistence across requests
- ✅ Cross-table relationships
- ✅ Transaction management

### Data Flow Verification:
- ✅ Request/Response validation
- ✅ Data transformation
- ✅ Error handling
- ✅ Cross-controller data sharing

## 🎯 Expected Results

### ✅ Success Indicators:
- All onboarding steps complete (returns userId)
- Calculations return valid numerical results
- Database queries retrieve saved data
- Error handling rejects invalid inputs
- Success rate > 90%

### ❌ Failure Indicators:
- Connection refused errors
- Database constraint violations
- Null pointer exceptions
- Validation errors
- Success rate < 70%

## 🔧 Troubleshooting

### Application Not Starting:
```bash
# Check if port 8080 is in use
netstat -an | find "8080"

# Kill process if needed
taskkill /f /pid <PID>
```

### Database Issues:
- Check H2 console: http://localhost:8080/h2-console
- Verify tables exist: `SHOW TABLES;`
- Check data: `SELECT * FROM USER;`

### API Errors:
- Check application logs in console
- Verify request body format matches DTOs
- Ensure required fields are provided

## 📈 Sample Test Results

```
📊 SUMMARY STATISTICS:
   Total Tests: 18
   ✅ Passed: 16
   ❌ Failed: 2
   📈 Success Rate: 88.9%

🔍 DATABASE CONNECTIVITY:
   ✅ H2 Database Console Access
   ✅ Data Persistence
   ✅ Cross-table Relationships

🌐 API ENDPOINTS STATUS:
   Onboarding: 8/8 passed
   Calculations: 3/3 passed
   Data Retrieval: 2/3 passed
   Error Handling: 3/3 passed
```

## 🎉 Next Steps After Testing

1. **If tests pass**: APIs are working correctly
2. **If tests fail**: Check logs and fix issues
3. **Add monitoring**: Set up health checks
4. **Performance testing**: Load test with multiple users
5. **Security testing**: Validate input sanitization