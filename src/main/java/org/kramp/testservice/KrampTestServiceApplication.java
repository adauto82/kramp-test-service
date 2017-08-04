package org.kramp.testservice;

import javax.ws.rs.client.Client;

import org.kramp.testservice.client.GoogleAPIClient;
import org.kramp.testservice.client.ITunesSearchClient;
import org.kramp.testservice.health.GoogleAPIHealthCheck;
import org.kramp.testservice.health.ITunesSearchHealthCheck;
import org.kramp.testservice.health.SearchBooksAndMusicHealthCheck;
import org.kramp.testservice.resources.SearchBooksAndMusicResource;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class KrampTestServiceApplication extends
        Application<KrampTestServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KrampTestServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "KrampTestService";
    }

    @Override
    public void initialize(
            final Bootstrap<KrampTestServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final KrampTestServiceConfiguration configuration,
            final Environment environment) {
        //Jersey Client initialization
        final Client client = new JerseyClientBuilder(environment).using(
                configuration.getJerseyClientConfiguration()).build(getName());
        final ITunesSearchClient itunesSearchClient = new ITunesSearchClient(client);
        final GoogleAPIClient googleAPIClient = new GoogleAPIClient(client);
        //Search resource initialization and register
        final SearchBooksAndMusicResource searchResource = new SearchBooksAndMusicResource(itunesSearchClient, googleAPIClient, configuration.getResultsLimit());
        environment.jersey().register(searchResource);
        //Search healthCheck initialization and register
        final SearchBooksAndMusicHealthCheck healthCheck = new SearchBooksAndMusicHealthCheck(client); 
        environment.healthChecks().register("search", healthCheck);
        final GoogleAPIHealthCheck googleHealthCheck = new GoogleAPIHealthCheck(client); 
        environment.healthChecks().register("google books", googleHealthCheck);
        final ITunesSearchHealthCheck itunesSearchHealthCheck = new ITunesSearchHealthCheck(client); 
        environment.healthChecks().register("itunes albumes", itunesSearchHealthCheck);
    }

}
