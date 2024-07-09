package com.tour.operator.two.model;

import java.time.OffsetDateTime;

public record GraphqlOperatorRequest(
        Integer days,
         String countryCode,
         OffsetDateTime fromDate,
         String minPrice,
         String maxPrice) {
}
