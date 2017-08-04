package org.kramp.testservice.health;

import javax.ws.rs.client.Client;

import org.kramp.testservice.api.itunes.ITunesSearchResults;
import org.kramp.testservice.client.ITunesSearchClient;

import com.codahale.metrics.health.HealthCheck;

public class ITunesSearchHealthCheck extends HealthCheck {

    final private ITunesSearchClient itunesClient;

    public ITunesSearchHealthCheck(Client client) {
        this.itunesClient = new ITunesSearchClient(client);
    }

    @Override
    protected Result check() throws Exception {
        ITunesSearchResults results = itunesClient.searchAlbums("Michael",
                "US", 5);
        if (results == null || results.getResultCount() == 0) {
            return Result
                    .unhealthy("I got 0 results for 'Michael' search. It should return at least 5 albums. Check the logs");
        }
        return Result.healthy("I got " + results.getResultCount()
                + " for 'Michael' search");
    }

}
