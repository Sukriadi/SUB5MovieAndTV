package com.example.sub5movieandtv.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sub5movieandtv.api.APIClientMovieTV;
import com.example.sub5movieandtv.api.APIMovieTV;
import com.example.sub5movieandtv.Movie;
import com.example.sub5movieandtv.TV;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    private static final String API_KEY = "10c9574156a5fa8ac7508df502b9e27d";
    private static final String LANGUAGE = "en-US";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TV>> listTvShow = new MutableLiveData<>();


    public void setListMovies(final Context context) {
        APIMovieTV apiMovieTv = APIClientMovieTV.getClient().create(APIMovieTV.class);
        Call<MovieRespon> call = apiMovieTv.getMovieList(API_KEY, LANGUAGE);
        final ArrayList<Movie> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MovieRespon>() {
            @Override
            public void onResponse(Call<MovieRespon> call, Response<MovieRespon> response) {
                List<Movie> movieItemList = response.body().getResults();
                try {
                    movieItems.addAll(movieItemList);
                    listMovies.postValue(movieItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieRespon> call, Throwable t) {
                Log.d(TAG, "ERROR : " + t.getMessage());
            }
        });
    }
    public void setListTvShow(final Context context) {
        APIMovieTV apiMovieTv = APIClientMovieTV.getClient().create(APIMovieTV.class);
        Call<TVRespon> call = apiMovieTv.getTVList(API_KEY, LANGUAGE);
        final ArrayList<TV> tvShowItems = new ArrayList<>();
        call.enqueue(new Callback<TVRespon>() {
            @Override
            public void onResponse(Call<TVRespon> call, Response<TVRespon> response) {
                List<TV> tvItemList = response.body().getResults();
                try {
                    tvShowItems.addAll(tvItemList);
                    listTvShow.postValue(tvShowItems);
                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TVRespon> call, Throwable t) {
                Log.d(TAG, "ERROR : "+t.getMessage());
            }
        });
    }
    public MutableLiveData<ArrayList<Movie>> getListMovies() {
        return listMovies;
    }

    public MutableLiveData<ArrayList<TV>> getListTvShow() {    return listTvShow;}
}
