package com.example.sub5movieandtv.roomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sub5movieandtv.roomtvshow.TvShow;
import com.example.sub5movieandtv.roomtvshow.TvShowDao;

import java.util.List;

public class TvShowViewModel extends AndroidViewModel {
    private LiveData<List<TvShow>> tvShowLiveData;

    public TvShowViewModel(@NonNull Application application) {
        super(application);
        TvShowRoomDatabase db = TvShowRoomDatabase.getDatabase(application);
        TvShowDao tvShowDao = db.tvShowDao();
        tvShowLiveData = tvShowDao.getAllTvShowVm();
    }

    public LiveData<List<TvShow>> getTvShowList() {
        return tvShowLiveData;
    }
}