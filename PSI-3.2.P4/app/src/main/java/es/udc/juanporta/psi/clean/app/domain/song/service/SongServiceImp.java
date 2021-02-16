package es.udc.juanporta.psi.clean.app.domain.song.service;

import java.util.List;

import es.udc.juanporta.psi.clean.app.data.song.SongDataSourceImp;;
import es.udc.juanporta.psi.clean.app.domain.song.service.Song;
import es.udc.juanporta.psi.clean.app.domain.song.datasource.SongDataSource;

public class SongServiceImp implements SongService{
    private SongDataSource mDatasource = new SongDataSourceImp();

    @Override
    public List<Song> searchSongs(String textToSearch) {

        return mDatasource.searchSongs(textToSearch);
    }
}