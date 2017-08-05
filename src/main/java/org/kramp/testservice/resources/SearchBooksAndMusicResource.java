package org.kramp.testservice.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kramp.testservice.api.SearchBooksAndMusicResult;
import org.kramp.testservice.api.google.GoogleBooksResult;
import org.kramp.testservice.api.google.GoogleBooksResults;
import org.kramp.testservice.api.itunes.ITunesSearchResult;
import org.kramp.testservice.api.itunes.ITunesSearchResults;
import org.kramp.testservice.client.GoogleAPIClient;
import org.kramp.testservice.client.ITunesSearchClient;
import org.kramp.testservice.client.ServiceHelper;

import com.codahale.metrics.annotation.Timed;

//Path parameters tend to be cached, given the proper parameters.Check with 
//project manager if search results may vary for the external services.
@Path("/search")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SearchBooksAndMusicResource {

    private ITunesSearchClient itunesClient;
    private GoogleAPIClient googleAPIClient;

    private Integer maxResults;
    private Integer timeout;

    public SearchBooksAndMusicResource(ITunesSearchClient itunesSearchClient,
            GoogleAPIClient googleAPIClient, Integer maxResults, Integer timeout) {
        this.itunesClient = itunesSearchClient;
        this.googleAPIClient = googleAPIClient;
        this.maxResults = maxResults != null ? maxResults
                : ServiceHelper.DEFAULT_LIMIT;
        this.timeout = timeout;
    }
    
    @GET
    @Timed
    @Path("/{searchQuery}")
    public void searchBooksAndMusicAsync(
            @Context HttpServletRequest request,
            @PathParam("searchQuery") String searchQuery, 
            @Suspended final AsyncResponse asyncResponse) {
        /**
         * This is an ugly way to configure a timeout to the request
         * but since jetty does not allow to configure a timeout
         * and i am resilient to add a layer of nginx since the task
         * was a JAVA only task. Let'd do this workaround.
         */
        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //Not sure if GATEWAY_TIMEOUT or SERVICE_UNAVAILABLE
                asyncResponse.resume(Response.status(Response.Status.GATEWAY_TIMEOUT)
                        .entity("Operation time out.").build());
            }
        });
        asyncResponse.setTimeout(timeout, TimeUnit.SECONDS);
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                List<SearchBooksAndMusicResult> result = searchBooksAndMusic(request, searchQuery);
                asyncResponse.resume(result);
            }
     
            private List<SearchBooksAndMusicResult> searchBooksAndMusic(HttpServletRequest request, String searchQuery) {
                Locale locale = request.getLocale();
                ArrayList<SearchBooksAndMusicResult> serviceResponse = new ArrayList<SearchBooksAndMusicResult>();
                String country = "US";
                if (locale != null && locale.getCountry() != null
                        && locale.getCountry().length() == 2) {
                    country = locale.getCountry();
                }

                ITunesSearchResults itunesResponse = itunesClient.searchAlbums(
                        searchQuery, country, maxResults);
                
                if (itunesResponse != null) { // If there was no error
                    for (ITunesSearchResult itunesSearchResult : itunesResponse
                            .getResults()) {
                        serviceResponse.add(new SearchBooksAndMusicResult(
                                itunesSearchResult.getCollectionName(),
                                itunesSearchResult.getArtistName(),
                                SearchBooksAndMusicResult.ALBUM));
                    }
                }

                GoogleBooksResults googleBooksResults = googleAPIClient.searchBooks(
                        searchQuery, maxResults);
                if (googleBooksResults != null) { // If there was no error
                    for (GoogleBooksResult googleBooksResult : googleBooksResults
                            .getItems()) {
                        serviceResponse.add(new SearchBooksAndMusicResult(
                                googleBooksResult.getVolumeInfo().getTitle(),
                                googleBooksResult.getVolumeInfo().getAuthors(),
                                SearchBooksAndMusicResult.BOOK));
                    }
                }
                //Sort the result by title alphabetically.
                serviceResponse.sort((searchResult, anotherSearchResult) -> searchResult.getTitle()
                        .compareTo(anotherSearchResult.getTitle()));
                return serviceResponse;                
            }
        }).start();
    }
}
