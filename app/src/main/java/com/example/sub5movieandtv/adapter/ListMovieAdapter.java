package com.example.sub5movieandtv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub5movieandtv.Movie;
import com.example.sub5movieandtv.MovieDetail;
import com.example.sub5movieandtv.R;


import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListMovieViewHolder> {
    public static final String TAG = ListMovieAdapter.class.getCanonicalName();
    private Context context;
    private ArrayList<Movie> listMovies = new ArrayList<>();


    public ArrayList<Movie> getListMovie (){
        return listMovies;
    }

    public void setListMovie (ArrayList<Movie> list){
        listMovies.clear();
        listMovies.addAll(list);
        notifyDataSetChanged();

    }

    public ListMovieAdapter (Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ListMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie,viewGroup,false);
        return new ListMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieViewHolder listMovieViewHolder, int position) {
        Movie movie = getListMovie().get(position);
        String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        Glide.with(context)
               .load(urlImg + movie.getPhoto())
               .apply(new RequestOptions().override(350,550))
               .into(listMovieViewHolder.imgPhoto);

        listMovieViewHolder.tvTitle.setText(movie.getName());
        listMovieViewHolder.tvDateReleased.setText(movie.getReleaseDate());
        listMovieViewHolder.tvUserScore.setText(movie.getUser_score().toString());
        listMovieViewHolder.tvRuntime.setText(movie.getVoteCount());

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class ListMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvTitle, tvRuntime, tvDateReleased, tvUserScore;

        ListMovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDateReleased = itemView.findViewById(R.id.tv_item_dateReleased);
            tvUserScore = itemView.findViewById(R.id.tv_item_score);
            tvRuntime = itemView.findViewById(R.id.tv_vote_count);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = getListMovie().get(position);
            movie.setName(movie.getName());
            movie.setOverview(movie.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetail.class);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_WHR_FROM, TAG);
            context.startActivity(moveWithObjectIntent);
        }
        }

}
