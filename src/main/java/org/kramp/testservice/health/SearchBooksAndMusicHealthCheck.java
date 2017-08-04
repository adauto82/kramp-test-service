package org.kramp.testservice.health;

import javax.ws.rs.client.Client;

import com.codahale.metrics.health.HealthCheck;

public class SearchBooksAndMusicHealthCheck extends HealthCheck {

    private Client client;

    public SearchBooksAndMusicHealthCheck(Client client) {
        this.client = client;
    }
    
    @Override
    protected Result check() throws Exception {
        // TODO Auto-generated method stub
        return Result.healthy();
    }

}
