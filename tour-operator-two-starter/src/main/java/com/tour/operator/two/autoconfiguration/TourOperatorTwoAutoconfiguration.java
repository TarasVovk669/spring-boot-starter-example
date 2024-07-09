package com.tour.operator.two.autoconfiguration;

import com.tour.operator.two.service.TourOperatorTwoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;


@AutoConfiguration
@ConditionalOnProperty(prefix = "tour-operator.two.service", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TourOperatorTwoProperties.class)
public class TourOperatorTwoAutoconfiguration {

    private static final Logger log = LoggerFactory.getLogger(TourOperatorTwoAutoconfiguration.class);
    private final TourOperatorTwoProperties properties;

    public TourOperatorTwoAutoconfiguration(TourOperatorTwoProperties properties) {
        log.info("Configuration with: {}", properties);
        this.properties = properties;
    }

    @Bean("operatorTwoRestClient")
    public RestClient restClient(RestClient.Builder builder) {
        log.info("Configuration operatorRestClient: {}", properties);
        return builder
                .baseUrl(properties.getUrl())
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", properties.getApiKey());
                })
                .build();
    }

    @Bean("tourOperatorTwoService")
    public TourOperatorTwoServiceImpl tourOperatorService(TourOperatorTwoProperties properties,
                                                          @Qualifier("operatorTwoRestClient") RestClient restClient) {
        log.info("Configuration tourOperatorService: {} and restClient: {}", properties, restClient);
        return new TourOperatorTwoServiceImpl(restClient);
    }
}
