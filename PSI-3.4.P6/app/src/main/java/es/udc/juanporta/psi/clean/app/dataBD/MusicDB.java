package es.udc.juanporta.psi.clean.app.dataBD;

import android.content.Context;
import android.database.ContentObservable;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.udc.juanporta.psi.clean.app.module.artist.ArtistsActivity;

@Database(version = 1, entities = {ArtistDB.class})

public abstract class MusicDB extends RoomDatabase {

    private static MusicDB db = null;
    abstract public ArtistDBDao getArtistDao();


}
