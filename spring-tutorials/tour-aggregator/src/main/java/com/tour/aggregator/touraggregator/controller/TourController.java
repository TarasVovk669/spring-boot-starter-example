package com.tour.aggregator.touraggregator.controller;

import com.common.model.TourOffer;
import com.common.model.TourOperatorRequest;
import com.tour.aggregator.touraggregator.service.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<List<TourOffer>> getOffers(@RequestBody TourOperatorRequest request) {
     return ResponseEntity.ok(tourService.getTourOffers(request));
    }
}
