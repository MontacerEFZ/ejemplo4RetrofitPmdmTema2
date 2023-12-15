package montacer.elfazazi.ejemplo4retrofit.conexiones;

import java.util.ArrayList;

import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();


}
