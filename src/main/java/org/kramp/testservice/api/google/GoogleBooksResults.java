package org.kramp.testservice.api.google;

import java.util.List;

public class GoogleBooksResults {

    private String kind;
    private Integer totalItems;
    private List<GoogleBooksResult> items;

    public String getKind() {
        return kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public List<GoogleBooksResult> getItems() {
        return items;
    }

    public void setKing(String kind) {
        this.kind = kind;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public void setItems(List<GoogleBooksResult> items) {
        this.items = items;
    }
}
