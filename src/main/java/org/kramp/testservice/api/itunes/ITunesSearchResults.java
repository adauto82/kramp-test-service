package org.kramp.testservice.api.itunes;

import java.util.List;

public class ITunesSearchResults {

    private Integer resultCount;
    private List<ITunesSearchResult> results;
    
    public Integer getResultCount() {
        return resultCount;
    }
    
    public List<ITunesSearchResult> getResults() {
        return results;
    }
    
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
    
    public void setResults(List<ITunesSearchResult> results) {
        this.results = results;
    } 
    
}
