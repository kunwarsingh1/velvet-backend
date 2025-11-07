const axios = require('axios');

const BASE_URL = 'http://localhost:8080';
let userId = null;

// Test results tracking
const results = {
    passed: 0,
    failed: 0,
    tests: []
};

function logTest(name, status, response = null, error = null) {
    const test = { name, status, response: response?.status, error: error?.message };
    results.tests.push(test);
    if (status === 'PASS') results.passed++;
    else results.failed++;
    console.log(`${status === 'PASS' ? '✅' : '❌'} ${name}`);
    if (error) console.log(`   Error: ${error.message}`);
}

async function testAPI() {
    console.log('🚀 Starting Velvet Investment API Tests...\n');

    // Test 1: Onboarding Step 1
    try {
        const response = await axios.post(`${BASE_URL}/api/onboarding/step1`, {
            name: "John Doe",
            city: "Mumbai", 
            mobileNumber: "9876543210",
            email: "john.doe@example.com",
            dateOfBirth: "1990-05-15",
            retirementYear: 2055
        });
        userId = response.data.userId;
        logTest('Onboarding Step 1', 'PASS', response);
    } catch (error) {
        logTest('Onboarding Step 1', 'FAIL', null, error);
    }

    // Test 2: Onboarding Step 2 (only if step 1 passed)
    if (userId) {
        try {
            const response = await axios.post(`${BASE_URL}/api/onboarding/step2`, {
                userId: userId,
                monthlyExpenses: {
                    housing: 25000,
                    food: 8000,
                    transportation: 5000,
                    utilities: 3000,
                    entertainment: 4000,
                    healthcare: 2000,
                    other: 3000
                }
            });
            logTest('Onboarding Step 2', 'PASS', response);
        } catch (error) {
            logTest('Onboarding Step 2', 'FAIL', null, error);
        }
    }

    // Test 3: Cash Flow Analysis
    try {
        const response = await axios.post(`${BASE_URL}/api/cashflow/analyze`, {
            monthlySalary: 75000,
            monthlyExpense: 50000
        });
        logTest('Cash Flow Analysis', 'PASS', response);
    } catch (error) {
        logTest('Cash Flow Analysis', 'FAIL', null, error);
    }

    // Test 4: FIRE Calculation
    try {
        const response = await axios.post(`${BASE_URL}/api/fire/calculate`, {
            annualExpenses: 600000,
            currentPortfolioValue: 5000000
        });
        logTest('FIRE Calculation', 'PASS', response);
    } catch (error) {
        logTest('FIRE Calculation', 'FAIL', null, error);
    }

    // Test 5: Error handling - Invalid cash flow data
    try {
        await axios.post(`${BASE_URL}/api/cashflow/analyze`, {
            monthlySalary: -1000,
            monthlyExpense: 50000
        });
        logTest('Error Handling (Invalid Data)', 'FAIL', null, { message: 'Should have failed with negative salary' });
    } catch (error) {
        logTest('Error Handling (Invalid Data)', 'PASS', null, { message: 'Correctly rejected invalid data' });
    }

    // Test 6: Server Health Check
    try {
        const response = await axios.get(`${BASE_URL}/actuator/health`);
        logTest('Health Check', 'PASS', response);
    } catch (error) {
        // Try alternative health check
        try {
            const response = await axios.get(`${BASE_URL}/api/onboarding/step1`, { timeout: 5000 });
            logTest('Server Connectivity', 'PASS', response);
        } catch (error2) {
            logTest('Server Connectivity', 'FAIL', null, error2);
        }
    }

    // Summary
    console.log('\n📊 Test Summary:');
    console.log(`✅ Passed: ${results.passed}`);
    console.log(`❌ Failed: ${results.failed}`);
    console.log(`📈 Success Rate: ${((results.passed / (results.passed + results.failed)) * 100).toFixed(1)}%`);
    
    if (results.failed > 0) {
        console.log('\n❌ Failed Tests:');
        results.tests.filter(t => t.status === 'FAIL').forEach(t => {
            console.log(`   - ${t.name}: ${t.error}`);
        });
    }
}

// Run tests
testAPI().catch(console.error);