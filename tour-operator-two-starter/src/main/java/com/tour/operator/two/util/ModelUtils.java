package com.tour.operator.two.util;

import com.common.model.Hotel;
import com.common.model.TourOffer;
import com.common.model.TourOperatorRequest;
import com.tour.operator.two.model.GraphqlOperatorRequest;
import com.tour.operator.two.model.TourElement;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.util.Optional;

public class ModelUtils {

    public static TourOffer mapToCommonModel(TourElement tourProposition) {
        return TourOffer.builder()
                .id(tourProposition.getId())
                .startDate(tourProposition.getStartDate().toLocalDateTime())
                .endDate(tourProposition.getEndDate().toLocalDateTime())
                .price(new BigDecimal(tourProposition.getPrice()))
                .currency(tourProposition.getCurrency())
                .days(tourProposition.getDays())
                .hotel(Hotel.builder()
                        .name(tourProposition.getHotel().getHotelName())
                        .rating(tourProposition.getHotel().getHotelRating())
                        .countryCode(tourProposition.getHotel().getCountryCode())
                        .build())
                .build();
    }

    public static GraphqlOperatorRequest mapToOperatorRequest(TourOperatorRequest request) {
        return new GraphqlOperatorRequest(
                request.getDays(),
                request.getCountryCode(),
                request.getFromDate().atTime(OffsetTime.MIN),
                Optional.ofNullable(request.getMinPrice()).map(BigDecimal::toString).orElse(null),
                Optional.ofNullable(request.getMinPrice()).map(BigDecimal::toString).orElse(null)
        );
    }
}
