package montacer.elfazazi.ejemplo4retrofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import montacer.elfazazi.ejemplo4retrofit.adapters.AlbumAdapter;
import montacer.elfazazi.ejemplo4retrofit.conexiones.ApiConexiones;
import montacer.elfazazi.ejemplo4retrofit.conexiones.RetrofitObject;
import montacer.elfazazi.ejemplo4retrofit.databinding.ActivityMainBinding;
import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlbumAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Album> listaAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaAlbums = new ArrayList<>();
        adapter = new AlbumAdapter(listaAlbums, R.layout.album_view_holder, this);
        layoutManager = new LinearLayoutManager(this);

        binding.contentMain.contenedorAlbums.setAdapter(adapter);
        binding.contentMain.contenedorAlbums.setLayoutManager(layoutManager);

        doGetAlbums();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void doGetAlbums() {
        Retrofit retrofit = RetrofitObject.getConexion();

        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<ArrayList<Album>> getAlbums = api.getAlbums();
        getAlbums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK) {
                    ArrayList<Album> temp = response.body();
                    listaAlbums.addAll(temp);
                    adapter.notifyItemRangeInserted(0, listaAlbums.size());
                }else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "nom hay internet", Toast.LENGTH_SHORT).show();
            }

        }) ;
    }
}