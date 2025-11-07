const axios = require('axios');

// Configuration
const BASE_URL = 'http://localhost:8080';
const TEST_USER = {
    username: 'testuser_' + Date.now(),
    email: 'testuser_' + Date.now() + '@example.com',
    password: 'Test@123456'
};

let authToken = '';
let userId = '';
let goalId = '';

// Color codes for console output
const colors = {
    reset: '\x1b[0m',
    green: '\x1b[32m',
    red: '\x1b[31m',
    yellow: '\x1b[33m',
    blue: '\x1b[36m',
    magenta: '\x1b[35m'
};

// Helper functions
function log(message, color = colors.reset) {
    console.log(`${color}${message}${colors.reset}`);
}

function logSuccess(message) {
    log(`✓ ${message}`, colors.green);
}

function logError(message) {
    log(`✗ ${message}`, colors.red);
}

function logInfo(message) {
    log(`ℹ ${message}`, colors.blue);
}

function logSection(message) {
    log(`\n${'='.repeat(60)}`, colors.magenta);
    log(message, colors.magenta);
    log('='.repeat(60), colors.magenta);
}

async function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// Test functions
async function testRegister() {
    logSection('1. TESTING AUTHENTICATION - REGISTER');
    try {
        const response = await axios.post(`${BASE_URL}/api/auth/register`, TEST_USER);
        logSuccess('User registered successfully');
        logInfo(`User ID: ${response.data.userId}`);
        userId = response.data.userId;
        return true;
    } catch (error) {
        logError(`Registration failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testLogin() {
    logSection('2. TESTING AUTHENTICATION - LOGIN');
    try {
        const response = await axios.post(`${BASE_URL}/api/auth/login`, {
            email: TEST_USER.email,
            password: TEST_USER.password
        });
        authToken = response.data.token;
        userId = response.data.userId;
        logSuccess('Login successful');
        logInfo(`Token: ${authToken.substring(0, 20)}...`);
        logInfo(`User ID: ${userId}`);
        return true;
    } catch (error) {
        logError(`Login failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetProfile() {
    logSection('3. TESTING USER PROFILE');
    try {
        const response = await axios.get(`${BASE_URL}/api/auth/profile`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Profile retrieved successfully');
        logInfo(`Username: ${response.data.username}`);
        logInfo(`Email: ${response.data.email}`);
        return true;
    } catch (error) {
        logError(`Get profile failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testOnboardingStep1() {
    logSection('4. TESTING ONBOARDING - STEP 1 (Basic Info)');
    try {
        const response = await axios.post(`${BASE_URL}/api/onboarding/step1`, {
            name: 'John Doe',
            email: TEST_USER.email,
            mobile: '9876543210',
            city: 'Mumbai',
            dateOfBirth: '1990-01-15',
            retirementYear: 2050
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 1 completed');
        if (response.data.userId) {
            userId = response.data.userId;
            logInfo(`User ID: ${userId}`);
        }
        return true;
    } catch (error) {
        logError(`Step 1 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep2() {
    logSection('5. TESTING ONBOARDING - STEP 2 (Financial Info)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step2`, {
            userId: userId,
            monthlyIncome: 100000,
            monthlyExpenses: 50000,
            currentSavings: 500000
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 2 completed');
        return true;
    } catch (error) {
        logError(`Step 2 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep3() {
    logSection('6. TESTING ONBOARDING - STEP 3 (Additional Info)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step3`, {
            userId: userId,
            riskTolerance: 'moderate',
            investmentExperience: 'intermediate',
            investmentHorizon: 'long-term'
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 3 completed (Additional Info)');
        return true;
    } catch (error) {
        logError(`Step 3 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep4() {
    logSection('7. TESTING ONBOARDING - STEP 4 (Goals)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step4`, {
            userId: userId,
            goals: [
                {
                    name: 'Retirement',
                    targetAmount: 10000000,
                    targetYear: 2050,
                    category: 'retirement'
                },
                {
                    name: 'Child Education',
                    targetAmount: 2000000,
                    targetYear: 2035,
                    category: 'education'
                }
            ]
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 4 completed (Goals)');
        return true;
    } catch (error) {
        logError(`Step 4 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep5() {
    logSection('8. TESTING ONBOARDING - STEP 5 (Assets)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step5`, {
            userId: userId,
            cash: 100000,
            stocks: 200000,
            mutualFunds: 150000,
            realEstate: 5000000,
            gold: 100000,
            otherAssets: 50000
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 5 completed (Assets)');
        return true;
    } catch (error) {
        logError(`Step 5 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep6() {
    logSection('9. TESTING ONBOARDING - STEP 6 (Liabilities)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step6`, {
            userId: userId,
            homeLoan: 2000000,
            carLoan: 500000,
            personalLoan: 100000,
            creditCard: 50000,
            otherLiabilities: 25000
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 6 completed (Liabilities)');
        return true;
    } catch (error) {
        logError(`Step 6 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingStep7() {
    logSection('10. TESTING ONBOARDING - STEP 7 (Complete)');
    try {
        await axios.post(`${BASE_URL}/api/onboarding/step7`, {
            userId: userId,
            completed: true,
            termsAccepted: true
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Step 7 completed (Onboarding Complete)');
        return true;
    } catch (error) {
        logError(`Step 7 failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testOnboardingSummary() {
    logSection('11. TESTING ONBOARDING - GET SUMMARY');
    try {
        const response = await axios.get(`${BASE_URL}/api/onboarding/summary/${userId}`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Onboarding summary retrieved');
        logInfo(`Summary data retrieved for user: ${userId}`);
        return true;
    } catch (error) {
        logError(`Get summary failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testCreateGoal() {
    logSection('12. TESTING GOALS - CREATE');
    try {
        const response = await axios.post(`${BASE_URL}/api/goals`, {
            userId: userId,
            goalId: 'goal-test-1',
            name: 'House Purchase',
            targetAmount: 5000000,
            targetYear: 2030,
            category: 'property'
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        goalId = response.data.id;
        logSuccess('Goal created successfully');
        logInfo(`Goal ID: ${goalId}`);
        logInfo(`Goal Name: ${response.data.name}`);
        return true;
    } catch (error) {
        logError(`Create goal failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetGoals() {
    logSection('13. TESTING GOALS - GET ALL');
    try {
        const response = await axios.get(`${BASE_URL}/api/goals`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess(`Retrieved ${response.data.length} goal(s)`);
        response.data.forEach((goal, index) => {
            logInfo(`Goal ${index + 1}: ${goal.name} - ₹${goal.targetAmount}`);
        });
        return true;
    } catch (error) {
        logError(`Get goals failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testCalculateGoals() {
    logSection('14. TESTING GOALS - CALCULATE');
    try {
        const response = await axios.get(`${BASE_URL}/api/goals/calculate/${userId}?currentAge=33`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Goals calculated successfully');
        logInfo(`Calculated ${response.data.length} goal(s)`);
        return true;
    } catch (error) {
        logError(`Calculate goals failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testMonthlyExpenseCalculate() {
    logSection('15. TESTING MONTHLY EXPENSE - CALCULATE');
    try {
        const response = await axios.post(`${BASE_URL}/api/monthly-expense/calculate`, {
            userId: userId,
            basicExpenses: {
                rent: 20000,
                groceries: 10000,
                utilities: 5000,
                transportation: 5000,
                entertainment: 5000,
                others: 5000
            },
            emis: [
                { name: 'Home Loan', amount: 30000 },
                { name: 'Car Loan', amount: 15000 }
            ]
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Monthly expense calculated');
        logInfo(`Total Expense: ₹${response.data.totalMonthlyExpense}`);
        return true;
    } catch (error) {
        logError(`Calculate expense failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetMonthlyExpense() {
    logSection('16. TESTING MONTHLY EXPENSE - GET');
    try {
        const response = await axios.get(`${BASE_URL}/api/monthly-expense`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Monthly expense retrieved');
        if (response.data.totalMonthlyExpense) {
            logInfo(`Total: ₹${response.data.totalMonthlyExpense}`);
        }
        return true;
    } catch (error) {
        logError(`Get expense failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetNetWorth() {
    logSection('17. TESTING NET WORTH');
    try {
        const response = await axios.get(`${BASE_URL}/api/networth/${userId}`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Net worth retrieved');
        logInfo(`Total Assets: ₹${response.data.totalAssets}`);
        logInfo(`Total Liabilities: ₹${response.data.totalLiabilities}`);
        logInfo(`Net Worth: ₹${response.data.netWorth}`);
        return true;
    } catch (error) {
        logError(`Get net worth failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetPortfolioOverview() {
    logSection('18. TESTING PORTFOLIO OVERVIEW');
    try {
        const response = await axios.get(`${BASE_URL}/api/networth/portfolio/${userId}`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Portfolio overview retrieved');
        return true;
    } catch (error) {
        logError(`Get portfolio failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testCalculateFire() {
    logSection('19. TESTING FIRE - CALCULATE');
    try {
        const response = await axios.post(`${BASE_URL}/api/fire/calculate`, {
            userId: userId,
            currentAge: 33,
            retirementAge: 60,
            monthlyExpenses: 50000,
            currentSavings: 1000000,
            expectedReturn: 12.0
        }, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('FIRE calculated');
        if (response.data.fireNumber) {
            logInfo(`FIRE Number: ₹${response.data.fireNumber}`);
        }
        return true;
    } catch (error) {
        logError(`Calculate FIRE failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetFire() {
    logSection('20. TESTING FIRE - GET');
    try {
        const response = await axios.get(`${BASE_URL}/api/fire`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('FIRE data retrieved');
        return true;
    } catch (error) {
        logError(`Get FIRE failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetInsurance() {
    logSection('21. TESTING INSURANCE');
    try {
        const response = await axios.get(`${BASE_URL}/api/insurance`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Insurance data retrieved');
        return true;
    } catch (error) {
        logError(`Get insurance failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetLiabilities() {
    logSection('22. TESTING LIABILITIES');
    try {
        const response = await axios.get(`${BASE_URL}/api/liabilities`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Liabilities retrieved');
        if (response.data.totalLiabilities) {
            logInfo(`Total Liabilities: ₹${response.data.totalLiabilities}`);
        }
        return true;
    } catch (error) {
        logError(`Get liabilities failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testGetProjection() {
    logSection('23. TESTING PROJECTION');
    try {
        const response = await axios.get(`${BASE_URL}/api/projection`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Projection retrieved');
        if (response.data.currentNetWorth) {
            logInfo(`Current Net Worth: ₹${response.data.currentNetWorth}`);
            logInfo(`10 Year Projection: ₹${response.data.projectedNetWorth10Years}`);
        }
        return true;
    } catch (error) {
        logError(`Get projection failed: ${error.response?.data?.message || error.message}`);
        return false;
    }
}

async function testDeleteGoal() {
    logSection('24. TESTING GOALS - DELETE');
    if (!goalId) {
        logError('No goal ID available to delete');
        return false;
    }
    try {
        const response = await axios.delete(`${BASE_URL}/api/goals/${goalId}`, {
            headers: { Authorization: `Bearer ${authToken}` }
        });
        logSuccess('Goal deleted successfully');
        logInfo(`Message: ${response.data.message}`);
        return true;
    } catch (error) {
        logError(`Delete goal failed: ${error.response?.data?.error || error.message}`);
        return false;
    }
}

async function testHealthCheck() {
    logSection('25. TESTING HEALTH CHECK');
    try {
        const response = await axios.get(`${BASE_URL}/actuator/health`);
        logSuccess('Health check passed');
        logInfo(`Status: ${response.data.status}`);
        return true;
    } catch (error) {
        logError(`Health check failed: ${error.message}`);
        return false;
    }
}

// Main test runner
async function runAllTests() {
    log('\n' + '█'.repeat(60), colors.magenta);
    log('  COMPREHENSIVE API TEST SUITE', colors.magenta);
    log('█'.repeat(60) + '\n', colors.magenta);
    
    logInfo(`Base URL: ${BASE_URL}`);
    logInfo(`Test User: ${TEST_USER.email}`);
    logInfo(`Starting tests at: ${new Date().toLocaleString()}\n`);

    const results = {
        passed: 0,
        failed: 0,
        total: 0
    };

    const tests = [
        { name: 'Health Check', fn: testHealthCheck },
        { name: 'Register', fn: testRegister },
        { name: 'Login', fn: testLogin },
        { name: 'Get Profile', fn: testGetProfile },
        { name: 'Onboarding Step 1', fn: testOnboardingStep1 },
        { name: 'Onboarding Step 2', fn: testOnboardingStep2 },
        { name: 'Onboarding Step 3', fn: testOnboardingStep3 },
        { name: 'Onboarding Step 4', fn: testOnboardingStep4 },
        { name: 'Onboarding Step 5', fn: testOnboardingStep5 },
        { name: 'Onboarding Step 6', fn: testOnboardingStep6 },
        { name: 'Onboarding Step 7', fn: testOnboardingStep7 },
        { name: 'Onboarding Summary', fn: testOnboardingSummary },
        { name: 'Create Goal', fn: testCreateGoal },
        { name: 'Get Goals', fn: testGetGoals },
        { name: 'Calculate Goals', fn: testCalculateGoals },
        { name: 'Calculate Monthly Expense', fn: testMonthlyExpenseCalculate },
        { name: 'Get Monthly Expense', fn: testGetMonthlyExpense },
        { name: 'Get Net Worth', fn: testGetNetWorth },
        { name: 'Get Portfolio Overview', fn: testGetPortfolioOverview },
        { name: 'Calculate FIRE', fn: testCalculateFire },
        { name: 'Get FIRE', fn: testGetFire },
        { name: 'Get Insurance', fn: testGetInsurance },
        { name: 'Get Liabilities', fn: testGetLiabilities },
        { name: 'Get Projection', fn: testGetProjection },
        { name: 'Delete Goal', fn: testDeleteGoal }
    ];

    for (const test of tests) {
        results.total++;
        const success = await test.fn();
        if (success) {
            results.passed++;
        } else {
            results.failed++;
        }
        await sleep(500); // Small delay between tests
    }

    // Print summary
    logSection('TEST SUMMARY');
    log(`Total Tests: ${results.total}`, colors.blue);
    log(`Passed: ${results.passed}`, colors.green);
    log(`Failed: ${results.failed}`, results.failed > 0 ? colors.red : colors.green);
    log(`Success Rate: ${((results.passed / results.total) * 100).toFixed(2)}%`, 
        results.failed === 0 ? colors.green : colors.yellow);
    
    logInfo(`\nCompleted at: ${new Date().toLocaleString()}`);
    
    if (results.failed === 0) {
        log('\n🎉 ALL TESTS PASSED! 🎉\n', colors.green);
    } else {
        log('\n⚠️  SOME TESTS FAILED ⚠️\n', colors.red);
    }

    process.exit(results.failed > 0 ? 1 : 0);
}

// Run tests
runAllTests().catch(error => {
    logError(`Fatal error: ${error.message}`);
    process.exit(1);
});
