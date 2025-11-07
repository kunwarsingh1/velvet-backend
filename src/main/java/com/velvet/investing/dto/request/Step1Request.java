package com.velvet.investing.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Step1Request {
    
    private String userId; // optional (returned by backend)
    
    private String name;
    
    private String city;
    
    private String mobileNumber;
    
    private String email;
    
    private String dateOfBirth;
    
    private Integer retirementYear;
}