package org.kramp.testservice.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit.ResourceTestRule;

import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;

import org.kramp.testservice.api.SearchBooksAndMusicResult;
import org.kramp.testservice.api.google.GoogleBooksResult;
import org.kramp.testservice.api.google.GoogleBooksResults;
import org.kramp.testservice.api.google.VolumeInfo;
import org.kramp.testservice.api.itunes.ITunesSearchResult;
import org.kramp.testservice.api.itunes.ITunesSearchResults;
import org.kramp.testservice.client.GoogleAPIClient;
import org.kramp.testservice.client.ITunesSearchClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchBooksAndMusicResourceTest {
    private static final ITunesSearchClient itunesSearchClient = mock(ITunesSearchClient.class);
    private static final GoogleAPIClient googleAPIClient = mock(GoogleAPIClient.class);
        
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(new SearchBooksAndMusicResource(itunesSearchClient, googleAPIClient, 5, 60))
        .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
        .build();

    @Before
    public void setup() {
        ITunesSearchResults itunesSearchResults = new ITunesSearchResults();
        ArrayList<ITunesSearchResult> results = new ArrayList<ITunesSearchResult>();
        itunesSearchResults.setResults(results);
        for (int i=0;i<5;i++) {
            ITunesSearchResult result = new ITunesSearchResult();
            result.setCollectionName("success-"+i);
            result.setArtistName("musical-author-"+i);
            itunesSearchResults.getResults().add(result);
        }
        itunesSearchResults.setResultCount(itunesSearchResults.getResults().size());
        GoogleBooksResults googleBooksResults = new GoogleBooksResults();
        ArrayList<GoogleBooksResult> items = new ArrayList<GoogleBooksResult>();
        googleBooksResults.setItems(items);
        for (int i=0;i<5;i++) {
            GoogleBooksResult result = new GoogleBooksResult();
            result.setVolumeInfo(new VolumeInfo());
            result.getVolumeInfo().setTitle("success-"+i);
            result.getVolumeInfo().setAuthors(new ArrayList<String>(Arrays.asList("author-"+i)));
            googleBooksResults.getItems().add(result);
        }
        when(itunesSearchClient.searchAlbums("success", "US", 5)).thenReturn(itunesSearchResults);
        when(googleAPIClient.searchBooks("success", 5)).thenReturn(googleBooksResults);
    }
    
    @After
    public void tearDown(){
        reset(itunesSearchClient);
        reset(googleAPIClient);
    }
    
    @Test
    public void testSuccess() {
        Response response = resources.getJerseyTest().target("/search/success").request().get();
        assertTrue("Response should be OK", response.getStatus() == Response.Status.OK.getStatusCode());
        ObjectMapper objectMapper = resources.getObjectMapper();
        String actual = response.readEntity(String.class);
        try {
            List<SearchBooksAndMusicResult> actualResults = objectMapper.readValue(actual, new TypeReference<List<SearchBooksAndMusicResult>>(){});
            assertTrue("I must have 10 results", actualResults.size()==10);
        } catch (IOException e) {
            assertFail("Failed parsing JSON");
        }
        
    }

    private void assertFail(String string) {
        // TODO Auto-generated method stub
        
    }
}
