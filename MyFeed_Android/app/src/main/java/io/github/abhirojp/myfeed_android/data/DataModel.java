package io.github.abhirojp.myfeed_android.data;

import java.io.Serializable;

import retrofit2.http.Path;

/**
 * Created by abhiroj on 6/9/17.
 */

public class DataModel implements Serializable {

    private String name;

    private String text;

    private String imageUrl;

    private String title;

    private String time;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
