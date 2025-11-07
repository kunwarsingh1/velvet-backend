# Delete Goal API Documentation

## Overview
New DELETE endpoint added to allow authenticated users to delete their financial goals.

## Endpoint Details

### Delete Goal
**DELETE** `/api/goals/{goalId}`

Deletes a specific goal for the authenticated user.

#### Authentication
- **Required**: Yes
- **Type**: Bearer Token (JWT)
- **Role**: USER

#### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| goalId | Long | Yes | The unique ID of the goal to delete |

#### Request Headers
```
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Success Response (200 OK)
```json
{
  "message": "Goal deleted successfully",
  "goalId": 123
}
```

#### Error Response (404 Not Found)
```json
{
  "error": "Goal not found or you don't have permission to delete it",
  "goalId": 123
}
```

#### Error Response (401 Unauthorized)
```json
{
  "timestamp": "2025-11-07T10:30:00.000+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/api/goals/123"
}
```

## Security Features

### Authorization
- Only the owner of the goal can delete it
- The service verifies that the goal's `userId` matches the authenticated user's ID
- Returns 404 if the goal doesn't exist or doesn't belong to the user

### Validation
1. User authentication is verified via JWT token
2. User is retrieved from the database using email from token
3. Goal existence is checked
4. Goal ownership is verified before deletion

## Usage Examples

### Using cURL
```bash
curl -X DELETE http://localhost:8080/api/goals/123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Using JavaScript (Fetch API)
```javascript
const deleteGoal = async (goalId, token) => {
  const response = await fetch(`http://localhost:8080/api/goals/${goalId}`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  
  const data = await response.json();
  
  if (response.ok) {
    console.log('Goal deleted:', data.message);
  } else {
    console.error('Error:', data.error);
  }
  
  return data;
};

// Usage
deleteGoal(123, 'your-jwt-token-here');
```

### Using Axios
```javascript
import axios from 'axios';

const deleteGoal = async (goalId, token) => {
  try {
    const response = await axios.delete(
      `http://localhost:8080/api/goals/${goalId}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      }
    );
    
    console.log('Success:', response.data.message);
    return response.data;
  } catch (error) {
    if (error.response) {
      console.error('Error:', error.response.data.error);
    }
    throw error;
  }
};

// Usage
deleteGoal(123, 'your-jwt-token-here');
```

### Using Postman
1. Set method to **DELETE**
2. Enter URL: `http://localhost:8080/api/goals/123`
3. Go to **Authorization** tab
4. Select **Bearer Token**
5. Paste your JWT token
6. Click **Send**

### Using Swagger UI
1. Navigate to `http://localhost:8080/swagger-ui.html`
2. Find the **Goals** section
3. Expand **DELETE /api/goals/{goalId}**
4. Click **Try it out**
5. Enter the `goalId`
6. Click **Execute**

## Complete Workflow Example

### 1. Login to get JWT token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "Password@123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "userId": "1234",
  "email": "user@example.com"
}
```

### 2. Get all goals to find the goalId
```bash
curl -X GET http://localhost:8080/api/goals \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

Response:
```json
[
  {
    "id": 123,
    "userId": "1234",
    "goalId": "goal-1",
    "name": "Retirement",
    "targetAmount": 10000000,
    "targetYear": 2050,
    "category": "retirement"
  },
  {
    "id": 124,
    "userId": "1234",
    "goalId": "goal-2",
    "name": "House Purchase",
    "targetAmount": 5000000,
    "targetYear": 2030,
    "category": "property"
  }
]
```

### 3. Delete a specific goal
```bash
curl -X DELETE http://localhost:8080/api/goals/123 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

Response:
```json
{
  "message": "Goal deleted successfully",
  "goalId": 123
}
```

### 4. Verify deletion
```bash
curl -X GET http://localhost:8080/api/goals \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

Response (goal 123 is now removed):
```json
[
  {
    "id": 124,
    "userId": "1234",
    "goalId": "goal-2",
    "name": "House Purchase",
    "targetAmount": 5000000,
    "targetYear": 2030,
    "category": "property"
  }
]
```

## Service Layer Implementation

### GoalService.deleteGoal()
```java
public boolean deleteGoal(Long goalId, String email) {
    // 1. Get user from email
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) return false;
    
    // 2. Get goal by ID
    Goal goal = goalRepository.findById(goalId).orElse(null);
    if (goal == null) return false;
    
    // 3. Verify ownership
    if (!goal.getUserId().equals(user.getId())) {
        return false;
    }
    
    // 4. Delete goal
    goalRepository.deleteById(goalId);
    return true;
}
```

## Related Endpoints

### Get All Goals
**GET** `/api/goals`
- Returns all goals for the authenticated user

### Create Goal
**POST** `/api/goals`
- Creates a new goal for the user

### Calculate Goals
**GET** `/api/goals/calculate/{userId}`
- Calculates goal requirements

## Testing

### Test Case 1: Successful Deletion
```bash
# Should return 200 OK with success message
curl -X DELETE http://localhost:8080/api/goals/123 \
  -H "Authorization: Bearer VALID_TOKEN"
```

### Test Case 2: Goal Not Found
```bash
# Should return 404 with error message
curl -X DELETE http://localhost:8080/api/goals/99999 \
  -H "Authorization: Bearer VALID_TOKEN"
```

### Test Case 3: Unauthorized Access
```bash
# Should return 401 Unauthorized
curl -X DELETE http://localhost:8080/api/goals/123
```

### Test Case 4: Delete Another User's Goal
```bash
# Should return 404 (goal ownership verification fails)
curl -X DELETE http://localhost:8080/api/goals/123 \
  -H "Authorization: Bearer OTHER_USER_TOKEN"
```

## Status Codes

| Code | Description |
|------|-------------|
| 200 | Goal deleted successfully |
| 401 | Unauthorized - Invalid or missing JWT token |
| 404 | Goal not found or user doesn't have permission |
| 500 | Internal server error |

## Notes

- The endpoint uses soft ownership verification - it returns 404 for both "not found" and "not authorized" cases to avoid information leakage
- The goal is permanently deleted from the database
- There is no undo functionality - consider implementing soft delete if needed
- The authenticated user's email is extracted from the JWT token automatically

## Compilation Status
✅ Service method added successfully
✅ Controller endpoint added successfully
✅ All imports are correct
✅ Code compiles without errors
