package com.example.sub5movieandtv.db;

import android.database.Cursor;

public interface LoadFavMoviesCallback {

    void postExecute (Cursor cursor);
    void preExecute();
}
