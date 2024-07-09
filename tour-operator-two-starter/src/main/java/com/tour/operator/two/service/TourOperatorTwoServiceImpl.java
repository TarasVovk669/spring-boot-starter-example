package com.tour.operator.two.service;

import com.common.model.TourOperatorRequest;
import com.common.model.TourOperatorResponse;
import com.common.model.TourOperatorService;
import com.tour.operator.two.model.QueryResponse;
import com.tour.operator.two.util.ModelUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static com.tour.operator.two.util.ModelUtils.mapToOperatorRequest;


public class TourOperatorTwoServiceImpl implements TourOperatorService {

    private static final String QUERY =
            """
                    query makeTourRequest($request: TourOperatorRequest) {
                                      makeTourRequest(request: $request) {
                                        id
                                        startDate
                                        endDate
                                        price
                                        currency
                                        days
                                        hotel {
                                          hotelName
                                          hotelRating
                                          countryCode
                                        }
                                      }
                                    }
                    """;

    private final RestClient restClient;

    public TourOperatorTwoServiceImpl(@Qualifier("operatorTwoRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public TourOperatorResponse makeRequest(TourOperatorRequest request) {
        var tourRequest = mapToOperatorRequest(request);
        var variables = Map.ofEntries(Map.entry("request", tourRequest));
        var requestBody = Map.ofEntries(
                Map.entry("query", QUERY),
                Map.entry("variables", variables));

        var response = restClient
                .post().body(requestBody)
                .retrieve()
                .toEntity(QueryResponse.class);

        return TourOperatorResponse.builder()
                .deals(response.getBody()
                        .data()
                        .makeTourRequest()
                        .stream()
                        .map(ModelUtils::mapToCommonModel).toList())
                .build();
    }


}
