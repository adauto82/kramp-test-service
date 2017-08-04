package org.kramp.testservice.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kramp.testservice.api.google.GoogleBooksResults;

public class GoogleAPIClient {
    
    final private static String GOOGLE_API_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes";
    private Client client;
    
    public GoogleAPIClient(Client client) {
        this.client = client;
    }
    
    public GoogleBooksResults searchBooks(String searchQuery, Integer maxResults) {
        //empty result on error, not null
        GoogleBooksResults results = new GoogleBooksResults();
        WebTarget googleAPIResource = client.target(GOOGLE_API_BOOKS_URL);
        googleAPIResource = googleAPIResource.queryParam("q", searchQuery);
        //Otherwise magazines could come
        googleAPIResource = googleAPIResource.queryParam("printType", "books");
        googleAPIResource = googleAPIResource.queryParam("maxResults", maxResults!=null?maxResults:ServiceHelper.DEFAULT_LIMIT);

        Invocation.Builder googleAPIInvocationBuilder = googleAPIResource.request(MediaType.APPLICATION_JSON_TYPE);
        Response googleResponse = googleAPIInvocationBuilder.get();

        if (googleResponse != null && ServiceHelper.isResponseOk(googleResponse.getStatus())) {
            results = googleResponse.readEntity(GoogleBooksResults.class);
        }
        
        return results;
    }
}
