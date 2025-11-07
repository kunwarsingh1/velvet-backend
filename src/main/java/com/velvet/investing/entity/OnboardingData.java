package com.velvet.investing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnboardingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @Min(value = 1900, message = "Invalid retirement year")
    private int retirementYear;
}