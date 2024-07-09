package com.tour.operator.one.model;

public record OperatorRequest(
        Integer availableDays,
         String code,
         String fromDate,
         String minPrice,
         String maxPrice) {
}
