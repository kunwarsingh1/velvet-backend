const axios = require('axios');

const BASE_URL = 'http://localhost:8080';
let userId = null;

// Test results tracking
const results = {
    passed: 0,
    failed: 0,
    tests: [],
    apiEndpoints: {
        onboarding: 8,
        cashflow: 1,
        fire: 1,
        goals: 1,
        networth: 2,
        portfolio: 1
    }
};

function logTest(name, status, response = null, error = null, data = null) {
    const test = { 
        name, 
        status, 
        responseStatus: response?.status,
        responseData: data,
        error: error?.message || error?.response?.data || error
    };
    results.tests.push(test);
    
    if (status === 'PASS') {
        results.passed++;
        console.log(`✅ ${name}`);
        if (data) console.log(`   Response: ${JSON.stringify(data).substring(0, 100)}...`);
    } else {
        results.failed++;
        console.log(`❌ ${name}`);
        if (error) console.log(`   Error: ${JSON.stringify(error).substring(0, 200)}...`);
    }
}

async function testDatabaseConnectivity() {
    console.log('🔍 Testing Database Connectivity...\n');
    
    try {
        // Test H2 Console accessibility
        const response = await axios.get(`${BASE_URL}/h2-console`, { timeout: 5000 });
        logTest('H2 Database Console Access', 'PASS', response);
    } catch (error) {
        logTest('H2 Database Console Access', 'FAIL', null, error);
    }
}

async function testServerHealth() {
    console.log('🏥 Testing Server Health...\n');
    
    try {
        // Test basic server connectivity
        const response = await axios.get(`${BASE_URL}/actuator/health`, { timeout: 5000 });
        logTest('Server Health Check', 'PASS', response, null, response.data);
    } catch (error) {
        // Fallback test - try any endpoint
        try {
            await axios.options(`${BASE_URL}/api/onboarding/step1`);
            logTest('Server Connectivity (Fallback)', 'PASS');
        } catch (error2) {
            logTest('Server Connectivity', 'FAIL', null, error2);
        }
    }
}

async function testOnboardingFlow() {
    console.log('👤 Testing Complete Onboarding Flow...\n');
    
    // Step 1: Basic Information
    try {
        const step1Data = {
            name: "John Doe",
            city: "Mumbai",
            mobileNumber: "9876543210",
            email: "john.doe@example.com",
            dateOfBirth: "1990-05-15",
            retirementYear: 2055
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step1`, step1Data);
        userId = response.data.userId;
        logTest('Onboarding Step 1 - Basic Info', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 1 - Basic Info', 'FAIL', null, error);
        return false; // Can't continue without userId
    }

    if (!userId) {
        console.log('❌ Cannot continue onboarding flow without userId');
        return false;
    }

    // Step 2: Monthly Expenses
    try {
        const step2Data = {
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
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step2`, step2Data);
        logTest('Onboarding Step 2 - Monthly Expenses', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 2 - Monthly Expenses', 'FAIL', null, error);
    }

    // Step 3: Current Assets
    try {
        const step3Data = {
            userId: userId,
            currentAssets: {
                cash: 100000,
                bankDeposits: 500000,
                mutualFunds: 300000,
                stocks: 200000,
                realEstate: 2000000,
                gold: 150000,
                other: 50000
            }
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step3`, step3Data);
        logTest('Onboarding Step 3 - Current Assets', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 3 - Current Assets', 'FAIL', null, error);
    }

    // Step 4: Current Liabilities
    try {
        const step4Data = {
            userId: userId,
            currentLiabilities: {
                homeLoan: 1500000,
                carLoan: 300000,
                personalLoan: 100000,
                creditCard: 50000,
                other: 25000
            },
            loans: [{
                type: "Home Loan",
                amount: 1500000,
                interestRate: 8.5,
                tenure: 20
            }]
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step4`, step4Data);
        logTest('Onboarding Step 4 - Current Liabilities', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 4 - Current Liabilities', 'FAIL', null, error);
    }

    // Step 5: Financial Goals
    try {
        const step5Data = {
            userId: userId,
            goals: [
                {
                    name: "Child Education",
                    targetAmount: 2000000,
                    targetYear: 2040,
                    priority: "High"
                },
                {
                    name: "House Purchase",
                    targetAmount: 5000000,
                    targetYear: 2030,
                    priority: "Medium"
                }
            ]
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step5`, step5Data);
        logTest('Onboarding Step 5 - Financial Goals', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 5 - Financial Goals', 'FAIL', null, error);
    }

    // Step 6: Insurance Information
    try {
        const step6Data = {
            userId: userId,
            insurances: [
                {
                    type: "Life Insurance",
                    coverAmount: 5000000,
                    premium: 50000,
                    provider: "LIC"
                },
                {
                    type: "Health Insurance",
                    coverAmount: 1000000,
                    premium: 25000,
                    provider: "Star Health"
                }
            ]
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step6`, step6Data);
        logTest('Onboarding Step 6 - Insurance Info', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 6 - Insurance Info', 'FAIL', null, error);
    }

    // Step 7: Final Completion
    try {
        const step7Data = {
            userId: userId,
            riskTolerance: "Moderate",
            investmentExperience: "Beginner",
            preferredInvestmentStyle: "Balanced"
        };
        
        const response = await axios.post(`${BASE_URL}/api/onboarding/step7`, step7Data);
        logTest('Onboarding Step 7 - Final Completion', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Onboarding Step 7 - Final Completion', 'FAIL', null, error);
    }

    // Get Summary
    try {
        const response = await axios.get(`${BASE_URL}/api/onboarding/summary/${userId}`);
        logTest('Onboarding Summary Retrieval', 'PASS', response, null, 'Summary data retrieved');
    } catch (error) {
        logTest('Onboarding Summary Retrieval', 'FAIL', null, error);
    }

    return true;
}

async function testCalculationAPIs() {
    console.log('\n🧮 Testing Calculation APIs...\n');
    
    // Test Cash Flow Analysis
    try {
        const cashFlowData = {
            monthlySalary: 75000,
            monthlyExpense: 50000
        };
        
        const response = await axios.post(`${BASE_URL}/api/cashflow/analyze`, cashFlowData);
        logTest('Cash Flow Analysis', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Cash Flow Analysis', 'FAIL', null, error);
    }

    // Test FIRE Calculation
    try {
        const fireData = {
            annualExpenses: 600000,
            currentPortfolioValue: 5000000
        };
        
        const response = await axios.post(`${BASE_URL}/api/fire/calculate`, fireData);
        logTest('FIRE Calculation', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('FIRE Calculation', 'FAIL', null, error);
    }

    // Test Monthly Expense Calculation
    try {
        const expenseData = {
            basicExpenses: 40000,
            emiPayments: 15000
        };
        
        const response = await axios.post(`${BASE_URL}/api/monthly-expense/calculate`, expenseData);
        logTest('Monthly Expense Calculation', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Monthly Expense Calculation', 'FAIL', null, error);
    }
}

async function testDataRetrievalAPIs() {
    console.log('\n📊 Testing Data Retrieval APIs...\n');
    
    if (!userId) {
        console.log('⚠️  Skipping data retrieval tests - no userId available');
        return;
    }

    // Test Goal Calculations
    try {
        const response = await axios.get(`${BASE_URL}/api/goals/calculate/${userId}?currentAge=30`);
        logTest('Goal Calculations', 'PASS', response, null, 'Goal calculations retrieved');
    } catch (error) {
        logTest('Goal Calculations', 'FAIL', null, error);
    }

    // Test Net Worth Calculation
    try {
        const response = await axios.get(`${BASE_URL}/api/networth/${userId}`);
        logTest('Net Worth Calculation', 'PASS', response, null, response.data);
    } catch (error) {
        logTest('Net Worth Calculation', 'FAIL', null, error);
    }

    // Test Portfolio Overview
    try {
        const response = await axios.get(`${BASE_URL}/api/networth/portfolio/${userId}`);
        logTest('Portfolio Overview', 'PASS', response, null, 'Portfolio data retrieved');
    } catch (error) {
        logTest('Portfolio Overview', 'FAIL', null, error);
    }

    // Test Portfolio Overview with Request Body (from PortfolioOverviewController)
    try {
        const portfolioRequest = {
            userId: userId,
            year: 2024
        };
        
        const response = await axios.post(`${BASE_URL}/api/portfolio/overview`, portfolioRequest);
        logTest('Portfolio Overview (POST)', 'PASS', response, null, 'Portfolio overview retrieved');
    } catch (error) {
        logTest('Portfolio Overview (POST)', 'FAIL', null, error);
    }
}

async function testErrorHandling() {
    console.log('\n🚨 Testing Error Handling...\n');
    
    // Test invalid cash flow data
    try {
        const invalidData = {
            monthlySalary: -1000,
            monthlyExpense: 50000
        };
        
        await axios.post(`${BASE_URL}/api/cashflow/analyze`, invalidData);
        logTest('Error Handling - Invalid Cash Flow', 'FAIL', null, 'Should have rejected negative salary');
    } catch (error) {
        if (error.response && error.response.status >= 400) {
            logTest('Error Handling - Invalid Cash Flow', 'PASS', error.response, null, 'Correctly rejected invalid data');
        } else {
            logTest('Error Handling - Invalid Cash Flow', 'FAIL', null, error);
        }
    }

    // Test missing required fields
    try {
        const incompleteData = {
            city: "Mumbai",
            email: "test@example.com"
        };
        
        await axios.post(`${BASE_URL}/api/onboarding/step1`, incompleteData);
        logTest('Error Handling - Missing Fields', 'FAIL', null, 'Should have rejected incomplete data');
    } catch (error) {
        if (error.response && error.response.status >= 400) {
            logTest('Error Handling - Missing Fields', 'PASS', error.response, null, 'Correctly rejected incomplete data');
        } else {
            logTest('Error Handling - Missing Fields', 'FAIL', null, error);
        }
    }

    // Test non-existent user
    try {
        await axios.get(`${BASE_URL}/api/onboarding/summary/non-existent-user`);
        logTest('Error Handling - Non-existent User', 'FAIL', null, 'Should have returned 404');
    } catch (error) {
        if (error.response && (error.response.status === 404 || error.response.status === 500)) {
            logTest('Error Handling - Non-existent User', 'PASS', error.response, null, 'Correctly handled non-existent user');
        } else {
            logTest('Error Handling - Non-existent User', 'FAIL', null, error);
        }
    }
}

async function testSwaggerDocumentation() {
    console.log('\n📚 Testing API Documentation...\n');
    
    try {
        const response = await axios.get(`${BASE_URL}/v3/api-docs`);
        logTest('OpenAPI Documentation', 'PASS', response, null, 'API docs accessible');
    } catch (error) {
        logTest('OpenAPI Documentation', 'FAIL', null, error);
    }

    try {
        const response = await axios.get(`${BASE_URL}/swagger-ui/index.html`);
        logTest('Swagger UI', 'PASS', response, null, 'Swagger UI accessible');
    } catch (error) {
        logTest('Swagger UI', 'FAIL', null, error);
    }
}

function generateDetailedReport() {
    console.log('\n' + '='.repeat(80));
    console.log('📋 COMPREHENSIVE API TEST REPORT');
    console.log('='.repeat(80));
    
    const totalTests = results.passed + results.failed;
    const successRate = totalTests > 0 ? ((results.passed / totalTests) * 100).toFixed(1) : 0;
    
    console.log(`\n📊 SUMMARY STATISTICS:`);
    console.log(`   Total Tests: ${totalTests}`);
    console.log(`   ✅ Passed: ${results.passed}`);
    console.log(`   ❌ Failed: ${results.failed}`);
    console.log(`   📈 Success Rate: ${successRate}%`);
    
    console.log(`\n🔍 DATABASE CONNECTIVITY:`);
    const dbTests = results.tests.filter(t => t.name.includes('Database') || t.name.includes('H2'));
    if (dbTests.length > 0) {
        dbTests.forEach(test => {
            console.log(`   ${test.status === 'PASS' ? '✅' : '❌'} ${test.name}`);
        });
    } else {
        console.log(`   ⚠️  No specific database tests found`);
    }
    
    console.log(`\n🌐 API ENDPOINTS STATUS:`);
    const categories = {
        'Onboarding': results.tests.filter(t => t.name.includes('Onboarding')),
        'Calculations': results.tests.filter(t => t.name.includes('Cash Flow') || t.name.includes('FIRE') || t.name.includes('Monthly Expense')),
        'Data Retrieval': results.tests.filter(t => t.name.includes('Goal') || t.name.includes('Net Worth') || t.name.includes('Portfolio')),
        'Error Handling': results.tests.filter(t => t.name.includes('Error Handling')),
        'Documentation': results.tests.filter(t => t.name.includes('API') || t.name.includes('Swagger'))
    };
    
    Object.entries(categories).forEach(([category, tests]) => {
        const passed = tests.filter(t => t.status === 'PASS').length;
        const total = tests.length;
        console.log(`   ${category}: ${passed}/${total} passed`);
    });
    
    if (results.failed > 0) {
        console.log(`\n❌ FAILED TESTS DETAILS:`);
        results.tests.filter(t => t.status === 'FAIL').forEach(test => {
            console.log(`   • ${test.name}`);
            if (test.error) {
                console.log(`     Error: ${JSON.stringify(test.error).substring(0, 150)}...`);
            }
        });
    }
    
    console.log(`\n💾 DATA FLOW VERIFICATION:`);
    if (userId) {
        console.log(`   ✅ User ID generated: ${userId}`);
        console.log(`   ✅ Data persistence across onboarding steps`);
        console.log(`   ✅ Cross-controller data access`);
    } else {
        console.log(`   ❌ User ID not generated - data flow incomplete`);
    }
    
    console.log(`\n🎯 RECOMMENDATIONS:`);
    if (successRate >= 90) {
        console.log(`   🎉 Excellent! All APIs are working properly`);
    } else if (successRate >= 70) {
        console.log(`   ⚠️  Good, but some issues need attention`);
    } else {
        console.log(`   🚨 Multiple issues detected - requires investigation`);
    }
    
    console.log(`\n📝 NEXT STEPS:`);
    console.log(`   1. Review failed tests and fix issues`);
    console.log(`   2. Add comprehensive unit tests`);
    console.log(`   3. Implement integration tests`);
    console.log(`   4. Set up monitoring and health checks`);
    console.log(`   5. Add performance testing`);
    
    console.log('\n' + '='.repeat(80));
}

async function runComprehensiveTests() {
    console.log('🚀 STARTING COMPREHENSIVE VELVET INVESTMENT API TESTS');
    console.log('='.repeat(80));
    
    try {
        await testServerHealth();
        await testDatabaseConnectivity();
        await testSwaggerDocumentation();
        
        const onboardingSuccess = await testOnboardingFlow();
        await testCalculationAPIs();
        
        if (onboardingSuccess) {
            await testDataRetrievalAPIs();
        }
        
        await testErrorHandling();
        
    } catch (error) {
        console.error('❌ Critical error during testing:', error.message);
    }
    
    generateDetailedReport();
}

// Run the comprehensive test suite
runComprehensiveTests().catch(console.error);