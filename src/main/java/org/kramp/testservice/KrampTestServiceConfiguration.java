package org.kramp.testservice;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class KrampTestServiceConfiguration extends Configuration {
    
    //The default value is 5
    private Integer resultsLimit = 5;
    
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
    
    @JsonProperty("jerseyClient")
    public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClient) {
        this.jerseyClient = jerseyClient;
    }
    
    @JsonProperty
    public Integer getResultsLimit() {
        return resultsLimit;
    }
    
    @JsonProperty
    public void setResultsLimit(Integer resultsLimit) {
        this.resultsLimit = resultsLimit;
    }

}
