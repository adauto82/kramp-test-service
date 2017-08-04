package org.kramp.testservice.api.google;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumeInfo {
    private String title;
    private String subtitle;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<ISBN> industryIdentifiers;
    private ReadingModes readingModes;
    private Integer pageCount;
    private String printType;
    private List<String> categories;
    private Double averageRating;
    private String maturityRating;
    private Boolean allowAnonLogging;
    private String contentVersion;
    private PenalizationSummary panelizationSummary;
    private String language;
    private ImageLinks imageLinks;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    
    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public String getDescription() {
        return description;
    }
    public List<ISBN> getIndustryIdentifiers() {
        return industryIdentifiers;
    }
    public ReadingModes getReadingModes() {
        return readingModes;
    }
    public Integer getPageCount() {
        return pageCount;
    }
    public String getPrintType() {
        return printType;
    }
    public List<String> getCategories() {
        return categories;
    }
    public Double getAverageRating() {
        return averageRating;
    }
    
    public String getMaturityRating() {
        return maturityRating;
    }
    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }
    public String getContentVersion() {
        return contentVersion;
    }
    public PenalizationSummary getPenalizationSummary() {
        return panelizationSummary;
    }
    public String getLanguage() {
        return language;
    }
    public ImageLinks getImageLinks() {
        return imageLinks;
    }
    public String getPreviewLink() {
        return previewLink;
    }
    public String getInfoLink() {
        return infoLink;
    }
    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIndustryIdentifiers(List<ISBN> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public void setPrintType(String printType) {
        this.printType = printType;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    
    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }
    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }
    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }
    public void setPenalizationSummary(PenalizationSummary penalizationSummary) {
        this.panelizationSummary = penalizationSummary;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }
    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

}
