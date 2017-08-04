package org.kramp.testservice.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kramp.testservice.api.SearchBooksAndMusicResult;
import org.kramp.testservice.api.google.GoogleBooksResult;
import org.kramp.testservice.api.google.GoogleBooksResults;
import org.kramp.testservice.api.itunes.ITunesSearchResult;
import org.kramp.testservice.api.itunes.ITunesSearchResults;
import org.kramp.testservice.client.GoogleAPIClient;
import org.kramp.testservice.client.ITunesSearchClient;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;


//Path parameters tend to be cached, given the proper parameters.Check with 
//project manager if search results may vary for the external services.
@Path("/search")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SearchBooksAndMusicResource {
    
    final private static int DEFAULT_MAX_RESULTS = 5;
    
    private ITunesSearchClient itunesClient;
    private GoogleAPIClient googleAPIClient;

    private Integer maxResults;

    public SearchBooksAndMusicResource(ITunesSearchClient itunesSearchClient, 
            GoogleAPIClient googleAPIClient, Integer maxResults) {
        this.itunesClient = itunesSearchClient;
        this.googleAPIClient = googleAPIClient;
        this.maxResults = maxResults!=null?maxResults:DEFAULT_MAX_RESULTS;
    }

    @GET
    @Timed
    @Path("/{searchQuery}")
    public List<SearchBooksAndMusicResult> searchBooksAndMusic(@Context HttpServletRequest request,
            @PathParam("searchQuery") String searchQuery) {
        Locale locale = request.getLocale();
        ArrayList<SearchBooksAndMusicResult> serviceResponse = new ArrayList<SearchBooksAndMusicResult>();
        String country = "US";
        if (locale != null && locale.getCountry() != null && locale.getCountry().length()==2) {
            country = locale.getCountry();
        }
       
        ITunesSearchResults itunesResponse = itunesClient.searchAlbums(searchQuery, country, maxResults);
        for (ITunesSearchResult itunesSearchResult : itunesResponse.getResults()) {
            serviceResponse.add(new SearchBooksAndMusicResult(
                    itunesSearchResult.getCollectionName(), 
                    itunesSearchResult.getArtistName(), 
                    SearchBooksAndMusicResult.ALBUM));
        }

        GoogleBooksResults googleBooksResults = googleAPIClient.searchBooks(searchQuery, maxResults);
        for (GoogleBooksResult googleBooksResult : googleBooksResults.getItems()) {
            serviceResponse.add(new SearchBooksAndMusicResult(
                    googleBooksResult.getVolumeInfo().getTitle(), 
                    googleBooksResult.getVolumeInfo().getAuthors(), 
                    SearchBooksAndMusicResult.BOOK));
        }

        return serviceResponse;
    }
    
}
