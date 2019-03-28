
package com.gosemathraj.reddit.models.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecureMedia {

    @SerializedName("oembed")
    @Expose
    private Oembed oembed;
    @SerializedName("type")
    @Expose
    private String type;

    public Oembed getOembed() {
        return oembed;
    }

    public void setOembed(Oembed oembed) {
        this.oembed = oembed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
