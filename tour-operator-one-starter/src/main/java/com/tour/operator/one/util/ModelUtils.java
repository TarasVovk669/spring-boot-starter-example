package com.tour.operator.one.util;

import com.common.model.Hotel;
import com.common.model.TourOffer;
import com.common.model.TourOperatorRequest;
import com.tour.operator.one.model.OperatorRequest;
import com.tour.operator.one.model.TourProposition;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ModelUtils {

    public static TourOffer mapToCommonModel(TourProposition tourProposition) {
        return TourOffer.builder()
                .id(tourProposition.getPropositionId())
                .startDate(tourProposition.getStartDate())
                .endDate(tourProposition.getEndDate())
                .price(new BigDecimal(tourProposition.getPrice()))
                .currency(tourProposition.getCurrency())
                .days(tourProposition.getDays())
                .hotel(Hotel.builder()
                        .name(tourProposition.getHotelName())
                        .rating(tourProposition.getHotelRating())
                        .countryCode(tourProposition.getHotelCountryCode())
                        .build())
                .build();
    }

    public static OperatorRequest mapToOperatorRequest(TourOperatorRequest request) {
        return new OperatorRequest(
                request.getDays(),
                request.getCountryCode(),
                request.getFromDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                Optional.ofNullable(request.getMinPrice()).map(BigDecimal::toString).orElse(null),
                Optional.ofNullable(request.getMinPrice()).map(BigDecimal::toString).orElse(null)
        );
    }
}
