package montacer.elfazazi.ejemplo4retrofit.conexiones;

import java.util.ArrayList;

import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import montacer.elfazazi.ejemplo4retrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") int albumId);

    @GET("/albums/{albumId}/photos")
    Call<ArrayList<Photo>> getPhotosAlbumPath(@Path("albumId") int albumId);g
}
