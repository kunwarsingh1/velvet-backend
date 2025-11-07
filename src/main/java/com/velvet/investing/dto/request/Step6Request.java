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
public class Step6Request {
    
    private String userId;
    
    private InsuranceDto insurance;
    
    @Getter
    @Setter
    public static class InsuranceDto {
        private Double termLife;
        private Double health;
        private Double criticalIllness;
    }
}