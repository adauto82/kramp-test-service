package org.kramp.testservice.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kramp.testservice.api.itunes.ITunesSearchResults;

public class ITunesSearchClient {
    
    final private static String ITUNES_SEARCH_URL = "https://itunes.apple.com/search";
    
    private Client client;
    
    public ITunesSearchClient(final Client client) {
        this.client = client;
    }
    
    public ITunesSearchResults searchAlbums(final String query, 
            final String country, final Integer maxResults) {
        //empty result on error, not null
        ITunesSearchResults results = new ITunesSearchResults(); 
        
        WebTarget itunesSearchResource = client.target(ITUNES_SEARCH_URL);
        itunesSearchResource = itunesSearchResource.queryParam("term", query);
        itunesSearchResource = itunesSearchResource.queryParam("country", country);
        itunesSearchResource = itunesSearchResource.queryParam("media", "music");
        itunesSearchResource = itunesSearchResource.queryParam("entity", "album");
        itunesSearchResource = itunesSearchResource.queryParam("limit", 
                maxResults!=null?maxResults:ServiceHelper.DEFAULT_LIMIT);

        Invocation.Builder itunesSearchInvocationBuilder = itunesSearchResource.request(
                MediaType.APPLICATION_JSON_TYPE);
        Response itunesSearchResponse = itunesSearchInvocationBuilder.get();
        if (itunesSearchResponse != null && ServiceHelper.isResponseOk(itunesSearchResponse.getStatus())) {
            results = itunesSearchResponse.readEntity(ITunesSearchResults.class);
        }
        return results;
    }

}
