package com.example.sub5movieandtv.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "tb_movie";
    static final class MovieColumns implements BaseColumns {
        static String TITLE = "title";
        static String ORIGINAL_TITLE = "original_title";
        static String RELEASE_DATE = "release_date";
        static String VOTE_AVERAGE = "vote_average";
        static String VOTE_COUNT = "vote_count";
        static String OVERVIEW = "overview";
        static String POSTER_PATH = "poster_path";
        static String BACKDROP_PATH = "backdrop_path";
        static String ISFAVORITE = "isFavorite";
        static String POPULARITY = "popularity";
        static String ORIGINAL_LANGUAGE = "original_language";


    }
}