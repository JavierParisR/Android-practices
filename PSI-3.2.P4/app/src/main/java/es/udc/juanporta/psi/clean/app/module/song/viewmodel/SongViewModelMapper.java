package es.udc.juanporta.psi.clean.app.module.song.viewmodel;

import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.song.service.Song;

public class SongViewModelMapper {

    private List<Song> mSongs;

    public SongViewModelMapper(List<Song> songs){
        mSongs = songs;
    }

    public List<SongViewModel> map() {

        List<SongViewModel> songs = new ArrayList<>();
        for (Song song : mSongs) {
            songs.add(new SongViewModel(song.getId(), song.getName()));
        }
        return songs;
    }
}
