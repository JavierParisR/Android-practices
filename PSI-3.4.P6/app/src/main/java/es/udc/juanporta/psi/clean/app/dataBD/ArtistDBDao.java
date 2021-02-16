package es.udc.juanporta.psi.clean.app.dataBD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import es.udc.juanporta.psi.clean.app.domain.artist.Artist;

@Dao
public interface ArtistDBDao {

    @Query("SELECT * FROM artist")
    public ArtistDB[] getArtists();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(ArtistDB... artists);

    @Delete
    public void delete(ArtistDB... artists);
}
