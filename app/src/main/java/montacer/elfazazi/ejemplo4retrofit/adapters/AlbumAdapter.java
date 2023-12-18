package montacer.elfazazi.ejemplo4retrofit.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.HttpURLConnection;
import java.util.List;

import montacer.elfazazi.ejemplo4retrofit.PhotosActivity;
import montacer.elfazazi.ejemplo4retrofit.R;
import montacer.elfazazi.ejemplo4retrofit.conexiones.ApiConexiones;
import montacer.elfazazi.ejemplo4retrofit.conexiones.RetrofitObject;
import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumVH> {
    private List<Album> objects;
    private int resource;
    private Context context;

    public AlbumAdapter(List<Album> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View albumView = LayoutInflater.from(context).inflate(resource, null);

        albumView.setLayoutParams(
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        return new AlbumVH(albumView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumVH holder, int position) {

        Album a = objects.get(position);

        holder.lbTitulo.setText(a.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("ALBUM_ID", a.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(a).show();
            }
        });
    }

    private AlertDialog confirmDelete(Album a){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("seguro que quieres borrar???");
        builder.setCancelable(false);

        builder.setNegativeButton("cancelar", null);
        builder.setPositiveButton("borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Retrofit retrofit = RetrofitObject.getConexion();
                ApiConexiones api = retrofit.create(ApiConexiones.class);

                Call<Album> deleteAlbum = api.deleteAlbum(a.getId());

                deleteAlbum.enqueue(new Callback<Album>() {
                    @Override
                    public void onResponse(Call<Album> call, Response<Album> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK){
                            int posicion = objects.indexOf(a);
                            objects.remove(a);
                            notifyItemRemoved(posicion);
                        }
                    }

                    @Override
                    public void onFailure(Call<Album> call, Throwable t) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class AlbumVH extends RecyclerView.ViewHolder {

        TextView lbTitulo;
        ImageButton btnEliminar;

        public AlbumVH(@NonNull View itemView) {
            super(itemView);

            lbTitulo = itemView.findViewById(R.id.lbTituloAlbumCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarAlbumCard);

        }
    }
}
