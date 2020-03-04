package com.nasa.exercise.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "nasa")
public class NasaApiProxyConfig {

    private String apiKey;
    private String apiUrl;
    private String paramApiKey;
    private String paramEarthDate;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getParamApiKey() {
        return paramApiKey;
    }

    public void setParamApiKey(String paramApiKey) {
        this.paramApiKey = paramApiKey;
    }

    public String getParamEarthDate() {
        return paramEarthDate;
    }

    public void setParamEarthDate(String paramEarthDate) {
        this.paramEarthDate = paramEarthDate;
    }
}
