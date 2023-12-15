package montacer.elfazazi.ejemplo4retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import montacer.elfazazi.ejemplo4retrofit.R;
import montacer.elfazazi.ejemplo4retrofit.modelos.Album;
import montacer.elfazazi.ejemplo4retrofit.modelos.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoVH> {

    private List<Photo> objects;
    private int resource;
    private Context context;

    public PhotoAdapter(List<Photo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoVH(LayoutInflater.from(context).inflate(resource, null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoVH holder, int position) {
        Photo p = objects.get(position);

        holder.lbTitulo.setText(p.getTitle());
        Picasso.get().load(p.getUrl()).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class PhotoVH extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView lbTitulo;

        public PhotoVH(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.imgImagenPhotoCard);
            lbTitulo = itemView.findViewById(R.id.lbTituloPhotoCard);
        }
    }
}
