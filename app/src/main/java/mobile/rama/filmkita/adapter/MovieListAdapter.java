package mobile.rama.filmkita.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mobile.rama.filmkita.R;
import mobile.rama.filmkita.model.MovieData;
import mobile.rama.filmkita.network.Constant;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private List<MovieData> movieDatas;
    private Context context;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDatas = new ArrayList<>();
    }
    private void add(MovieData item) {
        movieDatas.add(item);
        notifyItemInserted(movieDatas.size() - 1);
    }

    public void addAll(List<MovieData> movieDatas) {
        for (MovieData movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(MovieData item) {
        int position = movieDatas.indexOf(item);
        if (position > -1) {
            movieDatas.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }
    private MovieData getItem(int i){
        return movieDatas.get(i);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movie, viewGroup,false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder movieViewHolder, int i) {
        final MovieData movieData = movieDatas.get(i);
        movieViewHolder.bind(movieData);

    }

    @Override
    public int getItemCount() {
        return movieDatas.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_data);
        }

        public void bind(MovieData movieData) {
            Picasso.get()
                    .load(Constant.IMG_URL + movieData.getPosterPath())
                    .into(img);
        }
    }
}
