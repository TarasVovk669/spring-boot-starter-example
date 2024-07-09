package com.tour.aggregator.touraggregator.service;

import com.common.model.TourOffer;
import com.common.model.TourOperatorRequest;
import com.common.model.TourOperatorResponse;
import com.common.model.TourOperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);
    private final List<TourOperatorService> tourOperatorServices;
    private final Executor tourOperatorExecutor;
    private final Integer responseTimeout;

    public TourServiceImpl(List<TourOperatorService> tourOperatorServices,
                           @Qualifier("tourOperatorTaskExecutor") Executor tourOperatorExecutor,
                           @Value("${app.response-timeout:5}") Integer responseTimeout) {
        this.tourOperatorServices = tourOperatorServices;
        this.tourOperatorExecutor = tourOperatorExecutor;
        this.responseTimeout = responseTimeout;

    }


    public List<TourOffer> getTourOffers(@RequestBody TourOperatorRequest request) {
        log.info("Send request: {}", request);

        var futures = tourOperatorServices.stream()
                .map(tourOperator -> CompletableFuture.supplyAsync(() -> tourOperator.makeRequest(request), tourOperatorExecutor)
                        .orTimeout(responseTimeout, TimeUnit.SECONDS)
                        .exceptionally(ex -> TourOperatorResponse.builder().deals(List.of()).build())
                )
                .toList();

        var response = futures.stream()
                .map(CompletableFuture::join)
                .map(TourOperatorResponse::getDeals)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .toList();

        return response;
    }
}
