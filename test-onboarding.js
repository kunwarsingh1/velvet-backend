const axios = require('axios');

const BASE_URL = 'http://localhost:8080';
let userId = null;

async function testOnboardingFlow() {
    console.log('🚀 TESTING ONBOARDING API FLOW');
    console.log('='.repeat(50));

    try {
        // Step 1: Basic Information
        console.log('\n1. 👤 Testing Step 1 - Basic Info...');
        const step1Data = {
            name: "John Doe",
            city: "Mumbai",
            mobileNumber: "9876543210",
            email: "john.doe@example.com",
            dateOfBirth: "1990-05-15",
            retirementYear: 2055
        };
        
        const step1Response = await axios.post(`${BASE_URL}/api/onboarding/step1`, step1Data);
        userId = step1Response.data.userId;
        console.log('✅ Step 1 Success - User ID:', userId);

        // Step 2: Monthly Expenses
        console.log('\n2. 💰 Testing Step 2 - Monthly Expenses...');
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
        
        const step2Response = await axios.post(`${BASE_URL}/api/onboarding/step2`, step2Data);
        console.log('✅ Step 2 Success:', step2Response.data);

        // Step 3: Current Assets
        console.log('\n3. 🏦 Testing Step 3 - Current Assets...');
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
        
        const step3Response = await axios.post(`${BASE_URL}/api/onboarding/step3`, step3Data);
        console.log('✅ Step 3 Success:', step3Response.data);

        // Step 4: Current Liabilities
        console.log('\n4. 📊 Testing Step 4 - Current Liabilities...');
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
        
        const step4Response = await axios.post(`${BASE_URL}/api/onboarding/step4`, step4Data);
        console.log('✅ Step 4 Success:', step4Response.data);

        // Step 5: Financial Goals
        console.log('\n5. 🎯 Testing Step 5 - Financial Goals...');
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
        
        const step5Response = await axios.post(`${BASE_URL}/api/onboarding/step5`, step5Data);
        console.log('✅ Step 5 Success:', step5Response.data);

        // Step 6: Insurance Information
        console.log('\n6. 🛡️ Testing Step 6 - Insurance Info...');
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
        
        const step6Response = await axios.post(`${BASE_URL}/api/onboarding/step6`, step6Data);
        console.log('✅ Step 6 Success:', step6Response.data);

        // Step 7: Final Completion
        console.log('\n7. ✅ Testing Step 7 - Final Completion...');
        const step7Data = {
            userId: userId,
            riskTolerance: "Moderate",
            investmentExperience: "Beginner",
            preferredInvestmentStyle: "Balanced"
        };
        
        const step7Response = await axios.post(`${BASE_URL}/api/onboarding/step7`, step7Data);
        console.log('✅ Step 7 Success:', step7Response.data);

        // Get Summary
        console.log('\n8. 📋 Testing Summary Retrieval...');
        const summaryResponse = await axios.get(`${BASE_URL}/api/onboarding/summary/${userId}`);
        console.log('✅ Summary Success - User Data Retrieved');
        console.log('   Name:', summaryResponse.data.user?.name);
        console.log('   Email:', summaryResponse.data.user?.email);
        console.log('   City:', summaryResponse.data.user?.city);

        console.log('\n' + '='.repeat(50));
        console.log('🎉 ONBOARDING FLOW COMPLETED SUCCESSFULLY!');
        console.log('='.repeat(50));
        console.log(`✅ User ID: ${userId}`);
        console.log('✅ All 7 steps completed');
        console.log('✅ Data persisted in database');
        console.log('✅ Summary retrieval working');
        
        console.log('\n🔍 Check H2 Database:');
        console.log('   URL: http://localhost:8080/h2-console');
        console.log('   JDBC: jdbc:h2:mem:testdb');
        console.log('   Query: SELECT * FROM USERS WHERE ID = \'' + userId + '\';');

    } catch (error) {
        console.log('\n❌ ONBOARDING TEST FAILED');
        console.log('Error:', error.response?.data || error.message);
        
        if (error.code === 'ECONNREFUSED') {
            console.log('\n🚨 Spring Boot application not running!');
            console.log('Start with: mvnw.cmd spring-boot:run');
        }
    }
}

testOnboardingFlow();