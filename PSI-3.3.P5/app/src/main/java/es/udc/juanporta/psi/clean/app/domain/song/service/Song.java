package es.udc.juanporta.psi.clean.app.domain.song.service;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    public Song(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getName() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
