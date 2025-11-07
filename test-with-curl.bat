@echo off
echo Testing Velvet Investment APIs with curl...
echo Make sure your Spring Boot application is running on http://localhost:8080
echo.

set BASE_URL=http://localhost:8080

echo 1. Testing Onboarding Step 1...
curl -X POST %BASE_URL%/api/onboarding/step1 ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"John Doe\",\"city\":\"Mumbai\",\"mobileNumber\":\"9876543210\",\"email\":\"john.doe@example.com\",\"dateOfBirth\":\"1990-05-15\",\"retirementYear\":2055}"
echo.
echo.

echo 2. Testing Cash Flow Analysis...
curl -X POST %BASE_URL%/api/cashflow/analyze ^
  -H "Content-Type: application/json" ^
  -d "{\"monthlySalary\":75000,\"monthlyExpense\":50000}"
echo.
echo.

echo 3. Testing FIRE Calculation...
curl -X POST %BASE_URL%/api/fire/calculate ^
  -H "Content-Type: application/json" ^
  -d "{\"annualExpenses\":600000,\"currentPortfolioValue\":5000000}"
echo.
echo.

echo 4. Testing Error Handling (Invalid Data)...
curl -X POST %BASE_URL%/api/cashflow/analyze ^
  -H "Content-Type: application/json" ^
  -d "{\"monthlySalary\":-1000,\"monthlyExpense\":50000}"
echo.
echo.

echo 5. Testing Server Connectivity...
curl -I %BASE_URL%/api/onboarding/step1
echo.

echo Tests completed!
pause