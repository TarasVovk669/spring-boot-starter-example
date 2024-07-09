package com.tour.operator.one;

import com.tour.operator.one.autoconfiguration.TourOperatorOneAutoconfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TourOperatorClientTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(RestClientAutoConfiguration.class, TourOperatorOneAutoconfiguration.class));

    @Test
    void shouldContainTodoRestClientBean() {
        contextRunner.run(context -> {
            assertTrue(context.containsBean("operatorOneRestClient"));
            assertTrue(context.containsBean("tourOperatorOneService"));
        });
    }
}
