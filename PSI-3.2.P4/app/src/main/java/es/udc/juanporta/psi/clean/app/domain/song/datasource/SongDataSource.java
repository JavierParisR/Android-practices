package es.udc.juanporta.psi.clean.app.domain.song.datasource;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.song.service.Song;


public interface SongDataSource {

    List<Song> searchSongs(String textToSearch);

}
