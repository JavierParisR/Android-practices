package es.udc.juanporta.psi.clean.app.module.song.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import es.udc.juanporta.psi.clean.BuildConfig;
import es.udc.juanporta.psi.clean.app.data.SetListFmAPI;
import es.udc.juanporta.psi.clean.app.data.interceptor.SetListFmApiInterceptor;
import es.udc.juanporta.psi.clean.app.domain.gig.Gigs;
import es.udc.juanporta.psi.clean.app.domain.song.service.Song;
import es.udc.juanporta.psi.clean.app.domain.song.service.SongService;
import es.udc.juanporta.psi.clean.app.domain.song.service.SongServiceImp;
import es.udc.juanporta.psi.clean.app.module.song.viewmodel.SongViewModel;
import es.udc.juanporta.psi.clean.app.module.song.viewmodel.SongViewModelMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongsPresenterImp implements SongsPresenter{

    private final static String TAG = SongsPresenterImp.class.getSimpleName();

    private SongsView mView;

    private List<SongViewModel> mSongsViewModels;

    private SongService mService = new SongServiceImp();

    public SongsPresenterImp(SongsView view) {

        mView = view;
    }

    @Override
    public void initFlow() {

        new SongsPresenterImp.GetSongsTask().execute();
    }

    @Override
    public void onClickSong() {

    }

    private List<SongViewModel> getSongsViewModel(List<Song> songs) {

        mSongsViewModels = new SongViewModelMapper(songs).map();
        return mSongsViewModels;
    }

    private void getLastGigs(List<SongViewModel> songsViewModels) {

        int index = 0;
        for (SongViewModel song : songsViewModels) {

            getLastGig(song, index++);
        }
    }

    private void getLastGig(SongViewModel song,
                            int position) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = okHttpClient.interceptors();
        interceptors.add(loggingInterceptor);
        interceptors.add(new SetListFmApiInterceptor());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.setlist.fm/rest/1.0/")
                .client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();

        SetListFmAPI api = retrofit.create(SetListFmAPI.class);

        Call<Gigs> call = api.searchSetLists(song.getId());

        call.enqueue(new Callback<Gigs>() {

            @Override
            public void onResponse(Call<Gigs> call,
                                   Response<Gigs> response) {

                if (response.isSuccessful()) {

                    Log.i(TAG, "Response OK: " + response.code());
                    SongViewModel songViewModel = mSongsViewModels.get(position);
                    mView.updateSong(songViewModel, position);

                } else {

                    Log.e(TAG, "Response fails: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Gigs> call,
                                  Throwable t) {

                Log.e(TAG, "Response fails: " + t.getMessage());
                mView.showEmptyView();
                mView.showError();
            }
        });
    }

    private class GetSongsTask extends AsyncTask<String, Void, List<Song>> {

        protected List<Song> doInBackground(String... textToSearch) {

            return mService.searchSongs("Green Day");
        }

        protected void onPostExecute(List<Song> result) {

            mSongsViewModels = getSongsViewModel(result);
            mView.showSongs(mSongsViewModels);
            getLastGigs(mSongsViewModels);
        }
    }
}
