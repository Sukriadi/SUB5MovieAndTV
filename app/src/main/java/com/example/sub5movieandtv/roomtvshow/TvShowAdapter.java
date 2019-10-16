package com.example.sub5movieandtv.roomtvshow;

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
import com.example.sub5movieandtv.R;
import com.example.sub5movieandtv.TVDetail;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    public static final String TAG = TvShowAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<TvShow> tvshowList;

    public TvShowAdapter(Context context, ArrayList<TvShow> tvshowList) {
        this.context = context;
        this.tvshowList = tvshowList;
    }

    public void setTvshowList(ArrayList<TvShow> tvshowList) {
        this.tvshowList = tvshowList;
        notifyDataSetChanged();
    }

    private ArrayList<TvShow> getTvshowList() {
        return tvshowList;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        TvShow tv = getTvshowList().get(position);
        String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        Glide.with(context)
                .load(urlImg + tv.getPoster_path())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

        holder.tvTitle.setText(tv.getName());
        holder.tvDateReleased.setText(tv.getFirst_air_date());
        holder.tvUserScore.setText(tv.getVote_average().toString());
        holder.tvRuntime.setText(tv.getVote_count());
    }

    @Override
    public int getItemCount() {
        return tvshowList.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvTitle, tvRuntime, tvDateReleased, tvUserScore;
        public TvShowViewHolder(@NonNull View itemView) {
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
            TvShow tvShow = getTvshowList().get(position);

            tvShow.setName(tvShow.getName());
            tvShow.setOverview(tvShow.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), TVDetail.class);
            moveWithObjectIntent.putExtra(TVDetail.EXTRA_TV, tvShow);
            moveWithObjectIntent.putExtra(TVDetail.EXTRA_WHR_FROM, TAG);
            context.startActivity(moveWithObjectIntent);
        }
    }
}
