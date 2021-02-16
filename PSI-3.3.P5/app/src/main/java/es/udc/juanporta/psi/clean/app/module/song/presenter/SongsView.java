package es.udc.juanporta.psi.clean.app.module.song.presenter;

import java.util.List;

import es.udc.juanporta.psi.clean.app.module.song.viewmodel.SongViewModel;

public interface SongsView {

    void showSongs(List<SongViewModel> songs);

    void showEmptyView();

    void showError();

    void updateSong(SongViewModel artist, int position);
}
