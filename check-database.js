const axios = require('axios');

async function checkDatabase() {
    console.log('🔍 CHECKING DATABASE TABLES');
    console.log('='.repeat(40));
    
    try {
        // Create a test user first
        console.log('Creating test user...');
        const userData = {
            name: "DB Test User",
            city: "Test City", 
            email: "db@test.com",
            dateOfBirth: "1990-01-01",
            retirementYear: 2050
        };
        
        const response = await axios.post('http://localhost:8080/api/onboarding/step1', userData);
        const userId = response.data.userId;
        console.log('✅ User created:', userId);
        
        // Get summary to see what tables have data
        const summary = await axios.get(`http://localhost:8080/api/onboarding/summary/${userId}`);
        
        console.log('\n📊 DATA IN TABLES:');
        console.log('='.repeat(40));
        
        if (summary.data.user) {
            console.log('✅ USERS table: Has data');
            console.log('   ID:', summary.data.user.id);
            console.log('   Name:', summary.data.user.name);
        } else {
            console.log('❌ USERS table: No data');
        }
        
        if (summary.data.financialInfo) {
            console.log('✅ FINANCIAL_INFO table: Has data');
        } else {
            console.log('❌ FINANCIAL_INFO table: No data');
        }
        
        if (summary.data.assetInfo) {
            console.log('✅ ASSET_INFO table: Has data');
        } else {
            console.log('❌ ASSET_INFO table: No data');
        }
        
        console.log('\n💡 EXPLANATION:');
        console.log('The service saves to USERS table, not ONBOARDING_DATA table.');
        console.log('ONBOARDING_DATA table is unused in current implementation.');
        console.log('\n🔍 To verify in H2 Console:');
        console.log('   Query: SELECT * FROM USERS;');
        console.log('   Not:   SELECT * FROM ONBOARDING_DATA;');
        
    } catch (error) {
        console.log('❌ Error:', error.message);
    }
}

checkDatabase();