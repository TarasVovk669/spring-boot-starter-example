package com.tour.operator.one.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tour-operator.one.service")
public class TourOperatorOneProperties {

    private final Boolean enabled;
    private final String url;
    private final Credentials credentials;

    public TourOperatorOneProperties(
            Boolean enabled,
            String url,
            Credentials credentials) {
        this.enabled = enabled;
        this.url = url;
        this.credentials = credentials;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public String getUrl() {
        return url;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public static class Credentials {
        private final String username;
        private final String password;

        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
