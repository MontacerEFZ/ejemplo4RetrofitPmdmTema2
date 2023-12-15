package montacer.elfazazi.ejemplo4retrofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import java.util.ArrayList;

import montacer.elfazazi.ejemplo4retrofit.adapters.AlbumAdapter;
import montacer.elfazazi.ejemplo4retrofit.databinding.ActivityMainBinding;
import montacer.elfazazi.ejemplo4retrofit.modelos.Album;

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


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}