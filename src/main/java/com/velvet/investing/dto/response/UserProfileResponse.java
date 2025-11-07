package com.velvet.investing.dto.response;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private String id;
    private String username;
    private String email;

    private String name;
    private String city;
    private String mobileNumber;
    private String dateOfBirth;
    private Integer retirementYear;
    private Instant createdAt;
}