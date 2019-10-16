package com.example.sub5movieandtv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub5movieandtv.adapter.ListTVAdapter;
import com.example.sub5movieandtv.roomdb.TvShowRoomDatabase;
import com.example.sub5movieandtv.roomtvshow.TvShow;
import com.example.sub5movieandtv.roomtvshow.TvShowAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TVDetail extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_WHR_FROM = "extra_whr_from";
    TextView tvTitle, tvStatus, tvGenre, tvCrew, tvUserScore,
            tvRuntime, tvLanguage, tvOverview, tvDateReleased;
    private String tvShowTitle, tvShowDesc, tvShowRelease, tvShowRating, tvShowVoteCount, tvShowUrlPhoto, tvPopularity, tvLang;
    ImageView imagePoster;
    private boolean isCheckFavorite;
    private String keyFavorite = "my_key"; //savepreference
    private String mySavePref = "my_savepref_favorite";
    private String strMsgSuccessInsert;
    private String strMsgSuccessDelete;
    private FloatingActionButton fabFavoriteFalse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_detail);

        tvTitle = findViewById(R.id.tv_item_title);
        tvStatus = findViewById(R.id.tv_status_popularity);
        tvUserScore = findViewById(R.id.tv_item_userScore);
        tvUserScore = findViewById(R.id.tv_item_userScore);
        tvRuntime = findViewById(R.id.tv_vote_count);
        tvLanguage = findViewById(R.id.tv_item_language);
        tvOverview = findViewById(R.id.tv_item_overview);
        tvDateReleased = findViewById(R.id.tv_item_dateReleased);
        imagePoster = findViewById(R.id.poster);
        fabFavoriteFalse = findViewById(R.id.fab_favorite_false);
        fabFavoriteFalse.setOnClickListener(this);
        strMsgSuccessInsert = getResources().getString(R.string.str_msg_add_fav);
        strMsgSuccessDelete = getResources().getString(R.string.str_msg_delete_fav);

        getDataParceable();
        checkingFavorite();

    }

    private void getDataParceable() {
        String whrFrom = getIntent().getStringExtra(EXTRA_WHR_FROM);
        if (whrFrom != null) {
            if (whrFrom.equals(ListTVAdapter.TAG)) {
                TV tv = getIntent().getParcelableExtra(EXTRA_TV);
                if (tv != null) {
                    tvShowTitle = tv.getTitle();
                    keyFavorite = tvShowTitle;
                    tvShowDesc = tv.getOverview();
                    tvShowRelease = tv.getDate_released();
                    tvShowRating = tv.getUser_score();
                    tvShowVoteCount = tv.getVoteCount();
                    tvShowUrlPhoto = tv.getPhoto();
                    tvPopularity = tv.getPopularity();
                    tvLang = tv.getOriginal_language();
                }
            } else if (whrFrom.equals(TvShowAdapter.TAG)) {
                TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);
                if (tvShow != null) {
                    tvShowTitle = tvShow.getName();
                    keyFavorite = tvShowTitle; //key
                    tvShowDesc = tvShow.getOverview();
                    tvShowRelease = tvShow.getFirst_air_date();
                    tvShowRating = tvShow.getVote_average().toString();
                    tvShowVoteCount = tvShow.getVote_count();
                    tvShowUrlPhoto = tvShow.getPoster_path();
                    tvPopularity = tvShow.getPopularity();
                    tvLang = tvShow.getOriginal_language();
                }
            }
            tvTitle.setText(tvShowTitle);
            tvStatus.setText(tvPopularity);
            tvUserScore.setText(tvShowRating);
            tvRuntime.setText(tvShowVoteCount);
            tvLanguage.setText(tvLang);
            tvOverview.setText(tvShowDesc);
            tvDateReleased.setText(tvShowRelease);


            String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
            Glide.with(this)
                    .load(urlImg + tvShowUrlPhoto)
                    .apply(new RequestOptions().override(350, 550))
                    .into(imagePoster);
        }
    }

    private void setTvShows() {
        @SuppressLint("StaticFieldLeak")
        class SetTvShow extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                TvShow tvShow = new TvShow();
                tvShow.setName(tvShowTitle);
                tvShow.setOverview(tvShowDesc);
                tvShow.setFirst_air_date(tvShowRelease);
                tvShow.setVote_average(Double.parseDouble(tvShowRating));
                tvShow.setVote_count(tvShowVoteCount);
                tvShow.setPoster_path(tvShowUrlPhoto);
                tvShow.setPopularity(tvPopularity);
                tvShow.setOriginal_language(tvLang);
                tvShow.setFavorite(true);
                TvShowRoomDatabase.getDatabase(getApplicationContext()).tvShowDao().insert(tvShow); //adding to db
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(TVDetail.this, strMsgSuccessInsert, Toast.LENGTH_SHORT).show();
            }
        }
        SetTvShow setTvShow = new SetTvShow();
        setTvShow.execute();
    }

    private void deleteTvShowByTitle() {
        @SuppressLint("StaticFieldLeak")
        class DeleteTvShowByTitle extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                TvShowRoomDatabase.getDatabase(getApplicationContext()).tvShowDao().deleteByTitle(tvShowTitle);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(TVDetail.this, strMsgSuccessDelete, Toast.LENGTH_SHORT).show();
            }
        }
        DeleteTvShowByTitle deleteTvShowByTitle = new DeleteTvShowByTitle();
        deleteTvShowByTitle.execute();
    }

    private void tesPref(boolean isFavor) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(mySavePref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyFavorite, isFavor);
        editor.apply();
    }

    private boolean radRef() {
        SharedPreferences mSharedPreferences = this.getSharedPreferences(mySavePref, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(keyFavorite, false);
    }

    private void setIsFavorite() {
        if (isCheckFavorite) {
            boolean isFavorite = false;
            tesPref(isFavorite);
            checkingFavorite();
            deleteTvShowByTitle();

        } else {
            boolean isFavorite = true;
            tesPref(isFavorite);
            checkingFavorite();
            setTvShows();

        }
    }

    private void checkingFavorite() {
        boolean isFavorite = radRef();
        if (isFavorite) {
            fabFavoriteFalse.setImageResource(R.drawable.ic_favorite_true_24dp);
            isCheckFavorite = true;
        } else {
            fabFavoriteFalse.setImageResource(R.drawable.ic_favorite_border_before);
            isCheckFavorite = false;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab_favorite_false) {
            setIsFavorite();
        }
    }
}
