package com.velvet.investing.dto.response;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String userId;
    private String username;
    private String email;

    private Instant expiresAt;
}