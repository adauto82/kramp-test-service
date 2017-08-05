package org.kramp.testservice.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kramp.testservice.api.itunes.ITunesSearchResults;
import org.slf4j.LoggerFactory;

public class ITunesSearchClient {

    final private static String ITUNES_SEARCH_URL = "https://itunes.apple.com/search";

    private Client client;

    public ITunesSearchClient(final Client client) {
        this.client = client;
    }

    public ITunesSearchResults searchAlbums(final String query,
            final String country, final Integer maxResults) {
        // empty result on error, not null
        ITunesSearchResults results = null;

        WebTarget itunesSearchResource = client.target(ITUNES_SEARCH_URL);
        itunesSearchResource = itunesSearchResource.queryParam("term", query);
        itunesSearchResource = itunesSearchResource.queryParam("country",
                country);
        itunesSearchResource = itunesSearchResource
                .queryParam("media", "music");
        itunesSearchResource = itunesSearchResource.queryParam("entity",
                "album");
        itunesSearchResource = itunesSearchResource.queryParam("limit",
                maxResults != null ? maxResults : ServiceHelper.DEFAULT_LIMIT);

        Invocation.Builder itunesSearchInvocationBuilder = itunesSearchResource
                .request(MediaType.APPLICATION_JSON_TYPE);
        Future<Response> futureResponse = itunesSearchInvocationBuilder.async()
                .get();
        Response itunesSearchResponse;
        try {
            itunesSearchResponse = futureResponse.get();
            if (itunesSearchResponse != null
                    && itunesSearchResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                results = itunesSearchResponse
                        .readEntity(ITunesSearchResults.class);
            }
        } catch (InterruptedException e) {
            LoggerFactory.getLogger(this.getClass()).error(
                    "Error on ITunesSearchResults.searchAlbums, Interrupted: "
                            + e.getMessage());
        } catch (ExecutionException e) {
            LoggerFactory.getLogger(this.getClass()).error(
                    "Error on ITunesSearchResults.searchAlbums, Execution: "
                            + e.getMessage());
        }
        return results;
    }

}
