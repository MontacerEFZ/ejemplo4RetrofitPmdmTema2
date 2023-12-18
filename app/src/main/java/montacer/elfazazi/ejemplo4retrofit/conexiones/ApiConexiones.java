package montacer.elfazazi.ejemplo4retrofit.conexiones;

import java.util.ArrayList;

import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import montacer.elfazazi.ejemplo4retrofit.modelos.Photo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {

    //Recogemos Todos
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    //recogemos todos con cierto parametro
    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") int albumId);

    //recogemos todos de cierto path
    @GET("/albums/{albumId}/photos")
    Call<ArrayList<Photo>> getPhotosAlbumPath(@Path("albumId") int albumId);

    //Insertamos album con post
    @POST("/albums")
    Call<Album> postAlbum(@Body Album a);

    //insertar album con formulario
    @FormUrlEncoded
    @POST("/albums")
    Call<Album> postAlbumForm(@Field("userId") int idUser, @Field("title") String titulo);

    @DELETE("albums/{id}")
    Call<Album> deleteAlbum(@Path("id") int idAlbum);
}
