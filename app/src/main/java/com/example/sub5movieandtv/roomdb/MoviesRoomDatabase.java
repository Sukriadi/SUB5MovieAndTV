package com.example.sub5movieandtv.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sub5movieandtv.roommovies.Movies;
import com.example.sub5movieandtv.roommovies.MoviesDao;

@Database(entities = {Movies.class}, version = 1, exportSchema = false)
public abstract class MoviesRoomDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();

    private static MoviesRoomDatabase INSTANCE;

    public static MoviesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesRoomDatabase.class) {
                if (INSTANCE == null) {  //create database name, my db name is : mymovies_database
                    INSTANCE = Room.databaseBuilder(context, MoviesRoomDatabase.class, "mymovies_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}