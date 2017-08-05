package org.kramp.testservice.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kramp.testservice.api.google.GoogleBooksResults;
import org.slf4j.LoggerFactory;

public class GoogleAPIClient {

    final private static String GOOGLE_API_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes";
    private Client client;

    public GoogleAPIClient(final Client client) {
        this.client = client;
    }

    public GoogleBooksResults searchBooks(String searchQuery, Integer maxResults) {
        // empty result on error, not null
        GoogleBooksResults results = null;
        WebTarget googleAPIResource = client.target(GOOGLE_API_BOOKS_URL);
        googleAPIResource = googleAPIResource.queryParam("q", searchQuery);
        // Otherwise magazines could come
        googleAPIResource = googleAPIResource.queryParam("printType", "books");
        googleAPIResource = googleAPIResource.queryParam("maxResults",
                maxResults != null ? maxResults : ServiceHelper.DEFAULT_LIMIT);

        Invocation.Builder googleAPIInvocationBuilder = googleAPIResource
                .request(MediaType.APPLICATION_JSON_TYPE);
        Future<Response> futureResponse = googleAPIInvocationBuilder.async()
                .get();

        try {
            Response googleResponse = futureResponse.get();
            if (googleResponse != null
                    && googleResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                results = googleResponse.readEntity(GoogleBooksResults.class);
            }
        } catch (InterruptedException e) {
            LoggerFactory.getLogger(this.getClass()).error(
                    "Error on GoogleBooksResults.searchBooks, Interrupted: "
                            + e.getMessage());
        } catch (ExecutionException e) {
            LoggerFactory.getLogger(this.getClass()).error(
                    "Error on GoogleBooksResults.searchBooks, Execution: "
                            + e.getMessage());
        }

        return results;
    }
}
