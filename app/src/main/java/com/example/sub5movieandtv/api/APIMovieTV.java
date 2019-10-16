package com.example.sub5movieandtv.api;

import com.example.sub5movieandtv.model.MovieRespon;
import com.example.sub5movieandtv.model.TVRespon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIMovieTV {

    @GET("discover/movie")
    Call<MovieRespon> getMovieList(
            @Query("api_key") String apikey,
            @Query("language") String language);

    @GET("discover/tv")
    Call<TVRespon> getTVList(
            @Query("api_key") String apikey,
            @Query("language") String language);

}