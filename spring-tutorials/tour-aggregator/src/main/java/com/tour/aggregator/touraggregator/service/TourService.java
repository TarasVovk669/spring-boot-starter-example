package com.tour.aggregator.touraggregator.service;

import com.common.model.TourOffer;
import com.common.model.TourOperatorRequest;

import java.util.List;

public interface TourService {
    List<TourOffer> getTourOffers(TourOperatorRequest request);
}
