package org.kramp.testservice.api.itunes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jtrepat
 * 
 *  This class is just to contain in a POJO the result of the itunes search, 
 *  the names or the properties are this so that the marshalling would be 
 *  straightforward.
 * 
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ITunesSearchResult {
    
    private String wrapperType;
    private String collectionType;
    private String contentAdvisoryRating;
    private Integer amgArtistId;
    private String copyright;
    private String kind;
    private Date releaseDate;
    private Integer artistId;
    private Integer collectionId;
    private Integer trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String previewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private Double collectionPrice;
    private Double trackPrice;
    private String collectionExplicitness;
    private String trackExplicitness;
    private Integer discCount;
    private Integer discNumber;
    private Integer trackCount;
    private Integer trackNumber;
    private Integer trackTimeMillis;
    private String country;
    private String currency;
    private String primaryGenreName;
    
    @JsonProperty
    public String getWrapperType() {
        return wrapperType;
    }
    @JsonProperty
    public String getKind() {
        return kind;
    }
    @JsonProperty
    public Integer getArtistId() {
        return artistId;
    }
    @JsonProperty
    public Integer getCollectionId() {
        return collectionId;
    }
    @JsonProperty
    public Integer getTrackId() {
        return trackId;
    }
    @JsonProperty
    public String getArtistName() {
        return artistName;
    }
    @JsonProperty
    public String getCollectionName() {
        return collectionName;
    }
    @JsonProperty
    public String getTrackName() {
        return trackName;
    }
    @JsonProperty
    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }
    @JsonProperty
    public String getTrackCensoredName() {
        return trackCensoredName;
    }
    @JsonProperty
    public String getArtistViewUrl() {
        return artistViewUrl;
    }
    @JsonProperty
    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }
    @JsonProperty
    public String getTrackViewUrl() {
        return trackViewUrl;
    }
    @JsonProperty
    public String getPreviewUrl() {
        return previewUrl;
    }
    @JsonProperty
    public String getArtworkUrl60() {
        return artworkUrl60;
    }
    @JsonProperty
    public String getArtworkUrl100() {
        return artworkUrl100;
    }
    @JsonProperty
    public Double getCollectionPrice() {
        return collectionPrice;
    }
    @JsonProperty
    public Double getTrackPrice() {
        return trackPrice;
    }
    @JsonProperty
    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }
    @JsonProperty
    public String getTrackExplicitness() {
        return trackExplicitness;
    }
    @JsonProperty
    public Integer getDiscCount() {
        return discCount;
    }
    @JsonProperty
    public Integer getDiscNumber() {
        return discNumber;
    }
    @JsonProperty
    public Integer getTrackCount() {
        return trackCount;
    }
    @JsonProperty
    public Integer getTrackNumber() {
        return trackNumber;
    }
    @JsonProperty
    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }
    @JsonProperty
    public String getCountry() {
        return country;
    }
    @JsonProperty
    public String getCurrency() {
        return currency;
    }
    @JsonProperty
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }
    @JsonProperty
    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }
    @JsonProperty
    public void setKind(String kind) {
        this.kind = kind;
    }
    @JsonProperty
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
    @JsonProperty
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
    @JsonProperty
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }
    @JsonProperty
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    @JsonProperty
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    @JsonProperty
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    @JsonProperty
    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }
    @JsonProperty
    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }
    @JsonProperty
    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }
    @JsonProperty
    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }
    @JsonProperty
    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }
    @JsonProperty
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
    @JsonProperty
    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }
    @JsonProperty
    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
    @JsonProperty
    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }
    @JsonProperty
    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }
    @JsonProperty
    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }
    @JsonProperty
    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }
    @JsonProperty
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }
    @JsonProperty
    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }
    @JsonProperty
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }
    @JsonProperty
    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }
    @JsonProperty
    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }
    @JsonProperty
    public void setCountry(String country) {
        this.country = country;
    }
    @JsonProperty
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @JsonProperty
    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
    @JsonProperty
    public String getCollectionType() {
        return collectionType;
    }
    @JsonProperty
    public Integer getAmgArtistId() {
        return amgArtistId;
    }
    @JsonProperty
    public String getCopyright() {
        return copyright;
    }
    @JsonProperty
    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }
    @JsonProperty
    public void setAmgArtistId(Integer amgArtistId) {
        this.amgArtistId = amgArtistId;
    }
    @JsonProperty
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    @JsonProperty
    public Date getReleaseDate() {
        return releaseDate;
    }
    @JsonProperty
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    @JsonProperty
    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }
    @JsonProperty
    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

}
