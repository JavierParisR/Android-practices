package es.udc.juanporta.psi.clean.app.module.song;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.module.BaseActivity;
import es.udc.juanporta.psi.clean.app.module.song.adapter.SongAdapter;
import es.udc.juanporta.psi.clean.app.module.song.presenter.SongsPresenter;
import es.udc.juanporta.psi.clean.app.module.song.presenter.SongsPresenterImp;
import es.udc.juanporta.psi.clean.app.module.song.presenter.SongsView;
import es.udc.juanporta.psi.clean.app.module.song.viewmodel.SongViewModel;

public class SongsActivity extends BaseActivity implements SongsView {

    private static final String TAG = SongsActivity.class.getSimpleName();

    @BindView(R.id.artists_list) //TODO
    RecyclerView mRecycler;

    @BindView(R.id.artists_empty_list)//TODO
    TextView mEmptyView;

    private SongAdapter mAdapter;

    private SongsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        mPresenter = new SongsPresenterImp(this);
        mPresenter.initFlow();
    }

    @Override
    public void showSongs(List<SongViewModel> songs) {

        mAdapter.setItems(songs);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {

        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSong(SongViewModel song, int position) {

        mAdapter.updateItem(song, position);
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new SongAdapter();
        mRecycler.setAdapter(mAdapter);
    }
}
