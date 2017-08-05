package org.kramp.testservice.resources;

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
import static org.junit.Assert.fail;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class SearchBooksAndMusicResourceTest {
    private static final ITunesSearchClient itunesSearchClient = mock(ITunesSearchClient.class);
    private static final GoogleAPIClient googleAPIClient = mock(GoogleAPIClient.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule
            .builder()
            .addResource(
                    new SearchBooksAndMusicResource(itunesSearchClient,
                            googleAPIClient, 5, 5))
            // i set timeout to 5 to test it easy
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();

    @Before
    public void setup() {
        ITunesSearchResults itunesSearchResults = new ITunesSearchResults();
        ArrayList<ITunesSearchResult> results = new ArrayList<ITunesSearchResult>();
        itunesSearchResults.setResults(results);
        for (int i = 0; i < 5; i++) {
            ITunesSearchResult result = new ITunesSearchResult();
            result.setCollectionName(Character.toString((char) (97+i))+"-success-" + i);
            result.setArtistName("musical-author-" + i);
            itunesSearchResults.getResults().add(result);
        }
        itunesSearchResults.setResultCount(itunesSearchResults.getResults()
                .size());
        GoogleBooksResults googleBooksResults = new GoogleBooksResults();
        ArrayList<GoogleBooksResult> items = new ArrayList<GoogleBooksResult>();
        googleBooksResults.setItems(items);
        for (int i = 0; i < 5; i++) {
            GoogleBooksResult result = new GoogleBooksResult();
            result.setVolumeInfo(new VolumeInfo());
            result.getVolumeInfo().setTitle(Character.toString((char) (97+i))+"-success-" + i);
            result.getVolumeInfo().setAuthors(
                    new ArrayList<String>(Arrays.asList("author-" + i)));
            googleBooksResults.getItems().add(result);
        }
        when(itunesSearchClient.searchAlbums("success", "US", 5)).thenReturn(
                itunesSearchResults);
        when(googleAPIClient.searchBooks("success", 5)).thenReturn(
                googleBooksResults);
        when(itunesSearchClient.searchAlbums("timeout", "US", 5)).thenAnswer(
                new Answer<ITunesSearchResults>() {
                    @Override
                    public ITunesSearchResults answer(
                            InvocationOnMock invocation) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                        }
                        return itunesSearchResults;
                    }
                });
    }

    @After
    public void tearDown() {
        reset(itunesSearchClient);
        reset(googleAPIClient);
    }

    @Test
    public void testSuccess() {
        Response response = resources.getJerseyTest().target("/search/success")
                .request().get();
        assertTrue("Response should be OK",
                response.getStatus() == Response.Status.OK.getStatusCode());
        List<SearchBooksAndMusicResult> actualResults = response
                .readEntity(new GenericType<List<SearchBooksAndMusicResult>>() {
                });
        assertTrue("I must have 10 results", actualResults.size() == 10);
        int albumsQuantity = 0;
        int booksQuantity = 0;
        String previousTitle = "";
        for (SearchBooksAndMusicResult searchBooksAndMusicResult : actualResults) {
            if (searchBooksAndMusicResult.getTitle().compareTo(previousTitle) < 0) {
                fail("The results are not ordered: "+
                        searchBooksAndMusicResult.getTitle()+" - "+previousTitle);
            }
            if (searchBooksAndMusicResult.getType().equals(
                    SearchBooksAndMusicResult.ALBUM)) {
                albumsQuantity++;
            }
            if (searchBooksAndMusicResult.getType().equals(
                    SearchBooksAndMusicResult.BOOK)) {
                booksQuantity++;
            }
        }
        assertTrue("Quantity of books and albums should be the same and 5",
                albumsQuantity == booksQuantity && booksQuantity == 5);
    }

    @Test
    public void timeoutFailTest() {
        Response response = resources.getJerseyTest().target("/search/timeout")
                .request().get();
        assertTrue("It should timeout",
                response.getStatus() == Response.Status.GATEWAY_TIMEOUT
                        .getStatusCode());
    }
    
}
