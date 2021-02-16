package es.udc.juanporta.psi.clean.app.domain.song.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.artist.Artist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Songs {
    @SerializedName("count")
    private int count;

    @SerializedName("works")
    private List<Song> songs;

    public Songs(int count,
                 List<Song> songs) {

        this.count = count;
        this.songs = songs;
    }

    public int getCount() {

        return count;
    }

    public List<Song> getSongs() {

        return songs;
    }
}
