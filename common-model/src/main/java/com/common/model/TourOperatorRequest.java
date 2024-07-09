package com.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourOperatorRequest {

    private Integer days;
    private String countryCode;
    private LocalDate fromDate;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
