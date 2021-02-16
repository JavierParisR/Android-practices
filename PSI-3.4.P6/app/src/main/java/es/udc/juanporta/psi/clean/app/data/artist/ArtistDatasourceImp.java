package es.udc.juanporta.psi.clean.app.data.artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.BuildConfig;
import es.udc.juanporta.psi.clean.app.data.MusicBrainzAPI;
import es.udc.juanporta.psi.clean.app.domain.artist.Artist;
import es.udc.juanporta.psi.clean.app.domain.artist.Artists;
import es.udc.juanporta.psi.clean.app.domain.artist.datasource.ArtistDatasource;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistDatasourceImp implements ArtistDatasource {

    @Override
    public List<Artist> searchArtists(String textToSearch) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder().
                        header("User-Agent", "PSiCleanArch/" + BuildConfig.VERSION_NAME + "(juan.porta@udc.es)").
                        method(original.method(), original.body()).build();

                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = httpClient.interceptors();
        interceptors.add(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://musicbrainz.org/ws/2/").
                client(httpClient.build()).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:" + textToSearch;
        String format = "json";
        Call<Artists> call = api.searchArtistByName(query, format);

        try {

            return call.execute().body().getArtists();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
