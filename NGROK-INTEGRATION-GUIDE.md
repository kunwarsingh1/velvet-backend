# 🌐 NGROK INTEGRATION GUIDE

## Overview
Ngrok integration allows you to expose your local Velvet Investment API to the internet, enabling:
- Remote testing from mobile devices
- Sharing API with team members
- Webhook testing
- External service integration

## 🚀 Quick Start

### 1. Install ngrok
```bash
# Download from https://ngrok.com/download
# Or using chocolatey (Windows)
choco install ngrok

# Or using npm
npm install -g ngrok
```

### 2. Start Everything at Once
```bash
start-with-ngrok.bat
```
This will:
- Start Spring Boot application
- Start ngrok tunnel
- Provide public URL

### 3. Manual Setup
```bash
# Terminal 1: Start Spring Boot
mvnw.cmd spring-boot:run

# Terminal 2: Start ngrok
setup-ngrok.bat
```

## 📋 Available Scripts

### `setup-ngrok.bat`
- Checks ngrok installation
- Verifies Spring Boot is running
- Starts ngrok tunnel on port 8080

### `start-with-ngrok.bat`
- Launches both Spring Boot and ngrok
- Automated setup process

### `test-ngrok-api.js`
- Tests API functionality via ngrok
- Verifies public accessibility

## 🔧 Configuration

### Basic Configuration
```yaml
# ngrok-config.yml
version: "2"
authtoken: YOUR_AUTH_TOKEN

tunnels:
  velvet-api:
    addr: 8080
    proto: http
    bind_tls: true
```

### Custom Subdomain (Paid Plan)
```yaml
velvet-api-custom:
  addr: 8080
  proto: http
  subdomain: velvet-investment-api
  bind_tls: true
```

## 🌐 Usage Examples

### 1. Basic Tunnel
```bash
ngrok http 8080
```
Output: `https://abc123.ngrok.io -> http://localhost:8080`

### 2. With Configuration File
```bash
ngrok start velvet-api
```

### 3. Custom Subdomain
```bash
ngrok http -subdomain=velvet-api 8080
```

## 📱 Testing Your Public API

### Using the Test Script
```bash
node test-ngrok-api.js https://your-ngrok-url.ngrok.io
```

### Manual Testing
```bash
# Test user creation
curl -X POST https://your-ngrok-url.ngrok.io/api/onboarding/step1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","city":"Mumbai","email":"test@example.com"}'

# Test cash flow
curl -X POST https://your-ngrok-url.ngrok.io/api/cashflow/analyze \
  -H "Content-Type: application/json" \
  -d '{"monthlySalary":75000,"monthlyExpense":50000}'
```

## 🔗 Public URLs Available

Once ngrok is running, your API will be accessible at:

- **API Base**: `https://your-ngrok-url.ngrok.io`
- **Swagger UI**: `https://your-ngrok-url.ngrok.io/swagger-ui/index.html`
- **H2 Console**: `https://your-ngrok-url.ngrok.io/h2-console`
- **Health Check**: `https://your-ngrok-url.ngrok.io/actuator/health`

## 📊 API Endpoints via Ngrok

All your existing endpoints work through ngrok:

```
POST https://your-ngrok-url.ngrok.io/api/onboarding/step1
POST https://your-ngrok-url.ngrok.io/api/onboarding/step2
...
POST https://your-ngrok-url.ngrok.io/api/cashflow/analyze
POST https://your-ngrok-url.ngrok.io/api/fire/calculate
GET  https://your-ngrok-url.ngrok.io/api/networth/{userId}
```

## 🔒 Security Considerations

### Free Plan Limitations
- Random URLs (e.g., `abc123.ngrok.io`)
- 40 connections/minute limit
- Session timeout after 8 hours

### Paid Plan Benefits
- Custom subdomains
- Reserved domains
- Higher connection limits
- Password protection
- IP whitelisting

### Best Practices
```bash
# Add basic auth (paid plan)
ngrok http -auth="username:password" 8080

# Restrict by IP (paid plan)
ngrok http -allow-cidr="192.168.1.0/24" 8080
```

## 🐛 Troubleshooting

### Common Issues

1. **"ngrok not found"**
   ```bash
   # Add ngrok to PATH or use full path
   C:\path\to\ngrok.exe http 8080
   ```

2. **"Connection refused"**
   ```bash
   # Ensure Spring Boot is running first
   mvnw.cmd spring-boot:run
   ```

3. **"Tunnel not found"**
   ```bash
   # Check ngrok dashboard: http://localhost:4040
   ```

### Verification Steps
```bash
# 1. Check Spring Boot is running
netstat -an | find "8080"

# 2. Check ngrok tunnel status
curl http://localhost:4040/api/tunnels

# 3. Test public URL
curl https://your-ngrok-url.ngrok.io/api/onboarding/step1
```

## 📈 Monitoring

### Ngrok Dashboard
- Local: `http://localhost:4040`
- View requests/responses
- Monitor traffic
- Debug issues

### Request Inspection
```bash
# Enable request inspection
ngrok http 8080 --log=stdout --log-level=info
```

## 🎯 Use Cases

### 1. Mobile App Testing
- Test API from mobile devices
- Cross-platform compatibility
- Real network conditions

### 2. Team Collaboration
- Share API with frontend developers
- Demo to stakeholders
- Integration testing

### 3. Webhook Development
- Receive webhooks from external services
- Payment gateway integration
- Third-party API callbacks

### 4. External Integration
- Connect with external services
- API documentation sharing
- Client testing

## 🚀 Production Considerations

### For Production Use:
- Use proper domain with SSL
- Implement API authentication
- Set up rate limiting
- Use production database
- Add monitoring and logging

### Ngrok Alternatives for Production:
- AWS Application Load Balancer
- Cloudflare Tunnel
- Custom reverse proxy
- VPS with public IP

## 📝 Next Steps

1. **Get ngrok account**: Sign up at https://ngrok.com
2. **Set auth token**: `ngrok authtoken YOUR_TOKEN`
3. **Run the setup**: `start-with-ngrok.bat`
4. **Test the API**: `node test-ngrok-api.js [ngrok-url]`
5. **Share with team**: Provide the ngrok URL

Your Velvet Investment API is now accessible from anywhere in the world! 🌍