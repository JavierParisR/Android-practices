package es.udc.juanporta.psi.clean.app.data.song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.BuildConfig;
import es.udc.juanporta.psi.clean.app.data.MusicBrainzAPI;
import es.udc.juanporta.psi.clean.app.data.interceptor.MusicBrainzApiInterceptor;
import es.udc.juanporta.psi.clean.app.domain.song.datasource.SongDataSource;
import es.udc.juanporta.psi.clean.app.domain.song.service.Song;
import es.udc.juanporta.psi.clean.app.domain.song.service.Songs;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongDataSourceImp implements SongDataSource {

    @Override
    public List<Song> searchSongs(String textToSearch) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = okHttpClient.interceptors();
        interceptors.add(loggingInterceptor);
        interceptors.add(new MusicBrainzApiInterceptor());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/").client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create()).build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:" + textToSearch;
        String format = "json";

        Call<Songs> call = api.searchSongsByName(query, format);

        try {

            return call.execute().body().getSongs();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}

