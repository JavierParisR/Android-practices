package es.udc.juanporta.psi.clean.app.domain.song.service;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.song.service.Song;

public interface SongService {
    List<Song> searchSongs(String textToSearch);
}
