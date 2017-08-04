package org.kramp.testservice.api.google;

public class PenalizationSummary {

    public Boolean containsEpubBubbles;
    public Boolean containsImageBubbles;

    public Boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public Boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsEpubBubbles(Boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public void setContainsImageBubbles(Boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }
}
