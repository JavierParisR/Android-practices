package es.udc.juanporta.psi.clean.app.domain.artist.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.data.artist.ArtistDatasourceImp;
import es.udc.juanporta.psi.clean.app.dataBD.ArtistDB;
import es.udc.juanporta.psi.clean.app.dataBD.ArtistDBDao;
import es.udc.juanporta.psi.clean.app.dataBD.MusicDB;
import es.udc.juanporta.psi.clean.app.domain.artist.Artist;
import es.udc.juanporta.psi.clean.app.domain.artist.datasource.ArtistDatasource;

public class ArtistServiceImp implements ArtistService {

    private ArtistDatasource mDatasource = new ArtistDatasourceImp();

    private MusicDB db;

    public ArtistServiceImp(MusicDB db){
        this.db=db;
    }
    @Override
    public List<Artist> searchArtists(String textToSearch,boolean online) {
        List<Artist> artist;
    if(online) {
        artist = mDatasource.searchArtists(textToSearch);
        int size = artist.size();
        ArtistDB[] artistDBArray = new ArtistDB[size];
        int i=0;
        for (Artist ar : artist){
            artistDBArray[i]=new ArtistDB(ar.getId(),ar.getName());
            i+=1;
        }
        if (db==null){
            i=0;
        }

        db.getArtistDao().insert(artistDBArray);
        return artist;
    }
    else{
        ArtistDB[] artistsArray = db.getArtistDao().getArtists();
        artist = new ArrayList<>();
        for (ArtistDB ar : artistsArray){
            artist.add(new Artist(ar.getArtistId(),ar.getName()));
        }
        return artist;
    }
    }


}
