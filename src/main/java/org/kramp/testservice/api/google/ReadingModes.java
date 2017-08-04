package org.kramp.testservice.api.google;

public class ReadingModes {
    public Boolean text;
    public Boolean image;
    
    public Boolean getText() {
        return text;
    }
    public Boolean getImag() {
        return image;
    }
    public void setText(Boolean text) {
        this.text = text;
    }
    public void setImag(Boolean imag) {
        this.image = imag;
    }
}
