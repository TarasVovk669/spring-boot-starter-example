package com.tour.operator.two.autoconfiguration;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tour-operator.two.service")
public class TourOperatorTwoProperties {

    private final Boolean enabled;
    private final String url;
    private final String apiKey;

    public TourOperatorTwoProperties(
            Boolean enabled,
            String url,
            String apiKey) {
        this.enabled = enabled;
        this.url = url;
        this.apiKey = apiKey;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }
}
