package com.tour.operator.one.service;

import com.common.model.TourOperatorRequest;
import com.common.model.TourOperatorResponse;
import com.common.model.TourOperatorService;
import com.tour.operator.one.model.TourProposition;
import com.tour.operator.one.util.ModelUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.tour.operator.one.util.ModelUtils.mapToOperatorRequest;


public class TourOperatorOneServiceImpl implements TourOperatorService {

    private final RestClient restClient;

    public TourOperatorOneServiceImpl(@Qualifier("operatorOneRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public TourOperatorResponse makeRequest(TourOperatorRequest request) {
        var tourRequest = mapToOperatorRequest(request); // transformation of our request into the one that the tour operator will understand

        var responseList = restClient
                .post().body(tourRequest)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<TourProposition>>() {
                });

        return TourOperatorResponse.builder()
                .deals(responseList
                        .getBody()
                        .stream()
                        .map(ModelUtils::mapToCommonModel)
                        .toList())
                .build();
    }
}
