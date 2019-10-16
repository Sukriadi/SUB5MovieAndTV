package com.example.sub5movieandtv.roomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sub5movieandtv.roommovies.Movies;
import com.example.sub5movieandtv.roommovies.MoviesDao;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    private LiveData<List<Movies>> moviesLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        MoviesRoomDatabase db = MoviesRoomDatabase.getDatabase(application);
        MoviesDao moviesDao = db.moviesDao();
        moviesLiveData = moviesDao.getAllMoviesVm();
    }

    public LiveData<List<Movies>> getMoviesList() {
        return moviesLiveData;
    }
}
