package com.velvet.investing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FireResponse {
    private double fireNumber;
    private double firePercentage;
}