//package com.example.sub5movieandtv.roommovies;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.example.sub5movieandtv.MovieDetail;
//import com.example.sub5movieandtv.R;
//
//import java.util.ArrayList;
//
//public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
//    public static final String TAG = MoviesAdapter.class.getSimpleName();
//    private Context context;
//    private ArrayList<Movies> moviesList;
//
//    public MoviesAdapter(Context context, ArrayList<Movies> moviesList) {
//        this.context = context;
//        this.moviesList = moviesList;
//    }
//
//    public void setMoviesList(ArrayList<Movies> moviesList) {
//        this.moviesList = moviesList;
//        notifyDataSetChanged();
//    }
//
//    private ArrayList<Movies> getMoviesList() {
//        return moviesList;
//    }
//    @NonNull
//    @Override
//    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie,viewGroup,false);
//        return new MoviesViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
//        Movies movie = getMoviesList().get(position);
//        String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
//        Glide.with(context)
//                .load(urlImg + movie.getPoster_path())
//                .apply(new RequestOptions().override(350,550))
//                .into(holder.imgPhoto);
//
//        holder.tvTitle.setText(movie.getTitle());
//        holder.tvDateReleased.setText(movie.getRelease_date());
//        holder.tvUserScore.setText(movie.getVote_average().toString());
//        holder.tvRuntime.setText(movie.getVote_count());
//    }
//
//    @Override
//    public int getItemCount() {
//        return moviesList.size();
//    }
//
//    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageView imgPhoto;
//        TextView tvTitle, tvRuntime, tvDateReleased, tvUserScore;
//
//        public MoviesViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imgPhoto = itemView.findViewById(R.id.img_item_photo);
//            tvTitle = itemView.findViewById(R.id.tv_item_title);
//            tvDateReleased = itemView.findViewById(R.id.tv_item_dateReleased);
//            tvUserScore = itemView.findViewById(R.id.tv_item_score);
//            tvRuntime = itemView.findViewById(R.id.tv_vote_count);
//
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            Movies movie = getMoviesList().get(position);
//
//            movie.setTitle(movie.getTitle());
//            movie.setOverview(movie.getOverview());
//
//            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetail.class);
//            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
//            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_WHR_FROM, TAG);
//            context.startActivity(moveWithObjectIntent);
//
//        }
//    }
//}
