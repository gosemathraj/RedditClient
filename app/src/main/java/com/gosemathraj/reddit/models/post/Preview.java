
package com.gosemathraj.reddit.models.post;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preview {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("reddit_video_preview")
    @Expose
    private RedditVideoPreview redditVideoPreview;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public RedditVideoPreview getRedditVideoPreview() {
        return redditVideoPreview;
    }

    public void setRedditVideoPreview(RedditVideoPreview redditVideoPreview) {
        this.redditVideoPreview = redditVideoPreview;
    }

}
