//package com.example.sub5movieandtv.roommovies;
////
////import androidx.lifecycle.LiveData;
////import androidx.room.Dao;
////import androidx.room.Insert;
////import androidx.room.Query;
////
////import java.util.List;
////
////@Dao
////public interface MoviesDao {
////    @Insert
////    void insert(Movies movies);
////
////    @Query("SELECT * FROM movies ORDER BY title")
////    LiveData<List<Movies>> getAllMoviesVm();
////
////    @Query("DELETE FROM movies WHERE title= :title_movie")
////    void deleteByTitle(String title_movie);
////}