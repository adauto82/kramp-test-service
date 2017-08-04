package org.kramp.testservice.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchBooksAndMusicResult {
    /*
     * Requirement The response elements will only contain title,
     * authors(/artists) and information whether it's a book or an album.
     */

    public SearchBooksAndMusicResult(String title, String authors, String type) {
        this.title = title;
        this.authors = new ArrayList<String>(Arrays.asList(authors));
        this.type = type;
    }

    public SearchBooksAndMusicResult(String title, List<String> authors,
            String type) {
        this.title = title;
        this.authors = authors;
        this.type = type;
    }

    public static final String ALBUM = "album";
    public static final String BOOK = "book";

    @NotEmpty
    private String title;

    // An author can be "the maker of anything; creator; originator:"
    // http://www.dictionary.com/browse/author
    @NotEmpty
    private List<String> authors;

    @NotEmpty
    private String type;

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public List<String> getAuthors() {
        return authors;
    }

    @JsonProperty
    public String getType() {
        return type;
    }
}
