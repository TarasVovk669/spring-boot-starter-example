package com.tour.operator.one.autoconfiguration;

import com.tour.operator.one.service.TourOperatorOneServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;


@AutoConfiguration
@ConditionalOnProperty(prefix = "tour-operator.one.service", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TourOperatorOneProperties.class)
public class TourOperatorOneAutoconfiguration {

    private static final Logger log = LoggerFactory.getLogger(TourOperatorOneAutoconfiguration.class);
    private final TourOperatorOneProperties properties;

    public TourOperatorOneAutoconfiguration(TourOperatorOneProperties properties) {
        log.info("Configuration with: {}", properties);
        this.properties = properties;
    }

    @Bean("operatorOneRestClient")
    public RestClient restClient(RestClient.Builder builder) {
        log.info("Configuration operatorRestClient: {}", properties);
        return builder
                .baseUrl(properties.getUrl())
                .defaultHeaders(httpHeaders -> {
                    if (null != properties.getCredentials()) {
                        httpHeaders.setBasicAuth(
                                properties.getCredentials().getUsername(),
                                properties.getCredentials().getPassword());
                    }
                })
                .build();
    }

    @Bean("tourOperatorOneService")
    public TourOperatorOneServiceImpl tourOperatorService(TourOperatorOneProperties properties,
                                                          @Qualifier("operatorOneRestClient") RestClient restClient) {
        log.info("Configuration tourOperatorService: {} and restClient: {}", properties, restClient);
        return new TourOperatorOneServiceImpl(restClient);
    }
}
