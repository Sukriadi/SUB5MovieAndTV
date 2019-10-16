package com.example.sub5movieandtv.dbadapter;

import android.app.Activity;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub5movieandtv.Movie;
//import com.example.sub5movieandtv.OnItemClick;
import com.example.sub5movieandtv.R;
import com.example.sub5movieandtv.entity.MovieFav;
import com.example.sub5movieandtv.other.CustomOnItemClickListener;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieFav> listMovie = new ArrayList<>();
    private Activity activity;

    public ArrayList<MovieFav> getListMovie() {
        return listMovie;
    }

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListMovie(ArrayList<MovieFav> listMovieFav) {
        if (listMovieFav.size() > 0) {
            this.listMovie.clear();
        }
        this.listMovie.addAll(listMovieFav);
        notifyDataSetChanged();
    }

    public void addItem(MovieFav moviefav) {
        this.listMovie.add(moviefav);
        notifyItemInserted(listMovie.size() - 1);
    }

    public void removeItem(int position) {
        this.listMovie.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listMovie.size());
    }

    private OnItemClickCallback onItemClickCallback;
    public void  setOnItemClickCallback (OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public  interface OnItemClickCallback {
        void onItemClickCallback (MovieFav movieFav);
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MovieViewHolder holder, int position) {
        String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        Glide.with(activity)
                .load(urlImg + listMovie.get(position).getPoster_path())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvDateReleased.setText(listMovie.get(position).getRelease_date());
        holder.tvUserScore.setText(listMovie.get(position).getVote_average().toString());
        holder.tvRuntime.setText(listMovie.get(position).getVote_count());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClickCallback(listMovie.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvRuntime, tvDateReleased, tvUserScore;
        RelativeLayout rl;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rlmain);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDateReleased = itemView.findViewById(R.id.tv_item_dateReleased);
            tvUserScore = itemView.findViewById(R.id.tv_item_score);
            tvRuntime = itemView.findViewById(R.id.tv_vote_count);
        }
    }
}
