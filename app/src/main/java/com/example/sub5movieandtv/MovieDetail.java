package com.example.sub5movieandtv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub5movieandtv.adapter.ListMovieAdapter;
import com.example.sub5movieandtv.roomdb.MoviesRoomDatabase;
import com.example.sub5movieandtv.roommovies.Movies;
import com.example.sub5movieandtv.roommovies.MoviesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetail extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_WHR_FROM = "extra_whr_from";
    TextView tvTitle, tvDateReleased, tvOverview, tvBudget,
            tvRevenue, tvUserScore, tvRuntime, tvLanguage, tvStatus, tvGenre;
    private String keyFavorite = "my_key";
    private String mySavePref = "my_savepref_favorite";
    private String strMsgSuccessInsert;
    private String strMsgSuccessDelete;
    private FloatingActionButton fabFavoriteFalse;
    private boolean isCheckFavorite;
    private String movieTitle, movieDesc, movieRelease, movieRating, movieVoteCount, movieUrlPhoto, movieUrlBg, moviepopularity, movielanguage;
    ImageView imagePoster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        fabFavoriteFalse = findViewById(R.id.fab_favorite_false);
        tvTitle = findViewById(R.id.tv_item_title);
        tvStatus = findViewById(R.id.tv_status_popularity);
        tvUserScore = findViewById(R.id.tv_item_userScore);
        tvBudget = findViewById(R.id.tv_item_budget);
        tvOverview = findViewById(R.id.tv_item_overview);
        tvDateReleased = findViewById(R.id.tv_item_dateReleased);
        tvLanguage = findViewById(R.id.tv_item_language);
        tvRuntime = findViewById(R.id.tv_vote_count);
        imagePoster = findViewById(R.id.poster);
        fabFavoriteFalse.setOnClickListener(this);
        strMsgSuccessInsert = getResources().getString(R.string.str_msg_add_fav);
        strMsgSuccessDelete = getResources().getString(R.string.str_msg_delete_fav);

        getDataParceable();
        checkingFavorite();
    }

    private void getDataParceable() {
        String whrFrom = getIntent().getStringExtra(EXTRA_WHR_FROM);
        if (whrFrom != null) {
            if (whrFrom.equals(ListMovieAdapter.TAG)) {
                Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
                if (movie != null) {
                    movieTitle = movie.getName();
                    keyFavorite = movieTitle;
                    movieRating = movie.getUser_score().toString();
                    movieVoteCount = movie.getVoteCount();
                    moviepopularity = movie.getPopularity();
                    movieDesc = movie.getOverview();
                    movieRelease = movie.getReleaseDate();
                    movielanguage = movie.getLanguage();
                    movieUrlPhoto = movie.getPhoto();
                }
            } else if (whrFrom.equals(MoviesAdapter.TAG)) {
                Movies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
                if (movie != null) {
                    movieTitle = movie.getTitle();
                    keyFavorite = movieTitle;
                    movieRelease = movie.getRelease_date();
                    movieRating = movie.getVote_average().toString();
                    movieVoteCount = movie.getVote_count();
                    moviepopularity = movie.getPopularity();
                    movieDesc = movie.getOverview();
                    movielanguage = movie.getOriginal_language();
                    movieUrlPhoto = movie.getPoster_path();
                }
            }

            tvTitle.setText(movieTitle);
            tvStatus.setText(movieRelease);
            tvUserScore.setText(movieRating);
            tvBudget.setText(moviepopularity);
            tvOverview.setText(movieDesc);
            tvDateReleased.setText(movieRelease);
            tvLanguage.setText(movielanguage);
            tvRuntime.setText(movieVoteCount);

            String urlImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
            Glide.with(this)
                    .load(urlImg + movieUrlPhoto)
                    .apply(new RequestOptions().override(350, 550))
                    .into(imagePoster);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab_favorite_false) {
            setIsFavorite();
        }
    }

    private void deleteMoviesByTitle() {
        @SuppressLint("StaticFieldLeak")
        class DeleteMovies extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                MoviesRoomDatabase.getDatabase(getApplicationContext()).moviesDao().deleteByTitle(movieTitle);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(MovieDetail.this, strMsgSuccessDelete, Toast.LENGTH_SHORT).show();
            }
        }
        DeleteMovies deleteMoviesByTitle = new DeleteMovies();
        deleteMoviesByTitle.execute();
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
            deleteMoviesByTitle(); //deleteMovies

        } else {
            boolean isFavorite = true;
            tesPref(isFavorite);
            checkingFavorite();
            setMovies();
        }
    }
    //Room
    private void setMovies() {
        @SuppressLint("StaticFieldLeak")
        class SetMovies extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Movies movies = new Movies();
                movies.setTitle(movieTitle);
                movies.setOverview(movieDesc);
                movies.setRelease_date(movieRelease);
                movies.setVote_average(Double.parseDouble(movieRating));
                movies.setVote_count(movieVoteCount);
                movies.setPoster_path(movieUrlPhoto);
                movies.setPopularity(moviepopularity);
                movies.setOriginal_language(movielanguage);
                Log.d("INIII", "doInBackground: "+movieTitle);
                movies.setFavorite(true);

                //adding to db
                MoviesRoomDatabase.getDatabase(getApplicationContext()).moviesDao().insert(movies);
                Log.d("tes", "doInBackground: " + movies.getTitle());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(MovieDetail.this, strMsgSuccessInsert, Toast.LENGTH_SHORT).show();
            }
        }
        SetMovies setMovies = new SetMovies();
        setMovies.execute();
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
}
