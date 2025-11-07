const axios = require('axios');

// Get ngrok URL from command line argument or use default
const NGROK_URL = process.argv[2] || 'https://your-ngrok-url.ngrok.io';
let userId = null;

console.log('🌐 TESTING VELVET API VIA NGROK');
console.log('='.repeat(50));
console.log(`🔗 Testing URL: ${NGROK_URL}`);
console.log('='.repeat(50));

async function testNgrokAPI() {
    try {
        // Test 1: Health Check
        console.log('\n1. 🏥 Testing API Health...');
        try {
            await axios.get(`${NGROK_URL}/actuator/health`, { timeout: 10000 });
            console.log('✅ API is accessible via ngrok');
        } catch (error) {
            console.log('⚠️  Health endpoint not available, testing basic connectivity...');
            await axios.options(`${NGROK_URL}/api/onboarding/step1`);
            console.log('✅ API is accessible via ngrok');
        }

        // Test 2: Create User
        console.log('\n2. 👤 Testing User Creation...');
        const userData = {
            name: "Ngrok Test User",
            city: "Remote City",
            mobileNumber: "9999999999",
            email: "ngrok@test.com",
            dateOfBirth: "1990-01-01",
            retirementYear: 2050
        };
        
        const userResponse = await axios.post(`${NGROK_URL}/api/onboarding/step1`, userData);
        userId = userResponse.data.userId;
        console.log(`✅ User created via ngrok: ${userId}`);

        // Test 3: Cash Flow Analysis
        console.log('\n3. 💰 Testing Cash Flow Analysis...');
        const cashFlowData = {
            monthlySalary: 100000,
            monthlyExpense: 60000
        };
        
        const cashFlowResponse = await axios.post(`${NGROK_URL}/api/cashflow/analyze`, cashFlowData);
        console.log('✅ Cash flow analysis via ngrok:', cashFlowResponse.data);

        // Test 4: FIRE Calculation
        console.log('\n4. 🔥 Testing FIRE Calculation...');
        const fireData = {
            annualExpenses: 720000,
            currentPortfolioValue: 3000000
        };
        
        const fireResponse = await axios.post(`${NGROK_URL}/api/fire/calculate`, fireData);
        console.log('✅ FIRE calculation via ngrok:', fireResponse.data);

        console.log('\n' + '='.repeat(50));
        console.log('🎉 ALL NGROK TESTS PASSED!');
        console.log('='.repeat(50));
        console.log(`🌐 Your API is publicly accessible at: ${NGROK_URL}`);
        console.log('📱 You can now test from mobile devices or share with others');
        console.log('🔗 API Documentation: ' + NGROK_URL + '/swagger-ui/index.html');
        console.log('💾 H2 Console: ' + NGROK_URL + '/h2-console');

    } catch (error) {
        console.log('\n❌ NGROK TEST FAILED');
        console.log('Error:', error.message);
        
        if (error.code === 'ENOTFOUND' || error.code === 'ECONNREFUSED') {
            console.log('\n🚨 CONNECTION ISSUES:');
            console.log('1. Verify ngrok is running: ngrok http 8080');
            console.log('2. Check the ngrok URL is correct');
            console.log('3. Ensure Spring Boot app is running locally');
        }
    }
}

// Run the test
testNgrokAPI();