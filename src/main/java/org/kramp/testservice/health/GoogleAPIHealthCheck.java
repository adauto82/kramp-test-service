package org.kramp.testservice.health;

import javax.ws.rs.client.Client;

import org.kramp.testservice.api.google.GoogleBooksResults;
import org.kramp.testservice.client.GoogleAPIClient;

import com.codahale.metrics.health.HealthCheck;

public class GoogleAPIHealthCheck extends HealthCheck {
    
    final private GoogleAPIClient googleAPIClient;
    
    public GoogleAPIHealthCheck(Client client) {
        googleAPIClient = new GoogleAPIClient(client);
    }

    @Override
    protected Result check() throws Exception {
        GoogleBooksResults results = googleAPIClient.searchBooks("Shakespeare", 5);
        if (results == null || results.getTotalItems() == 0) {
            return Result
                    .unhealthy("I got 0 results for 'Shakespeare' search. It should return at least 5 books. Check the logs");
        }
        return Result.healthy("I got "+results.getTotalItems()+" for 'Shakespeare' search");
    }

}
