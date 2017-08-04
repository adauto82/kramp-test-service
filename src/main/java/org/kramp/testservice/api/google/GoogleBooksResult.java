package org.kramp.testservice.api.google;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GoogleBooksResult {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    @JsonIgnore
    public Object saleInfo;
    @JsonIgnore
    public Object accessInfo;
    @JsonIgnore
    public Object searchInfo;
    
    public String getKind() {
        return kind;
    }
    public String getId() {
        return id;
    }
    public String getEtag() {
        return etag;
    }
    public String getSelfLink() {
        return selfLink;
    }
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
    public Object getSaleInfo() {
        return saleInfo;
    }
    public Object getAccessInfo() {
        return accessInfo;
    }
    public Object getSearchInfo() {
        return searchInfo;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEtag(String etag) {
        this.etag = etag;
    }
    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
    public void setSaleInfo(Object saleInfo) {
        this.saleInfo = saleInfo;
    }
    public void setAccessInfo(Object accessInfo) {
        this.accessInfo = accessInfo;
    }
    public void setSearchInfo(Object searchInfo) {
        this.searchInfo = searchInfo;
    }
}