package com.example.sub5movieandtv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Database;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmovie";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT )",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.MovieColumns._ID,
            DatabaseContract.MovieColumns.TITLE,
            DatabaseContract.MovieColumns.ORIGINAL_TITLE,
            DatabaseContract.MovieColumns.RELEASE_DATE,
            DatabaseContract.MovieColumns.VOTE_AVERAGE,
            DatabaseContract.MovieColumns.VOTE_COUNT,
            DatabaseContract.MovieColumns.OVERVIEW,
            DatabaseContract.MovieColumns.POSTER_PATH,
            DatabaseContract.MovieColumns.BACKDROP_PATH,
            DatabaseContract.MovieColumns.ISFAVORITE,
            DatabaseContract.MovieColumns.POPULARITY,
            DatabaseContract.MovieColumns.ORIGINAL_LANGUAGE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(db);
    }
}
