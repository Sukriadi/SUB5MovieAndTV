package com.example.sub5movieandtv.fragment;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sub5movieandtv.R;
import com.example.sub5movieandtv.db.LoadFavMoviesCallback;
import com.example.sub5movieandtv.db.MovieHelper;
import com.example.sub5movieandtv.dbadapter.MovieAdapter;
import com.example.sub5movieandtv.entity.MovieFav;
import com.example.sub5movieandtv.roomdb.MoviesViewModel;
import com.example.sub5movieandtv.roommovies.Movies;
import com.example.sub5movieandtv.roommovies.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMoviesFragment extends Fragment implements LoadFavMoviesCallback {
    public static final String TAG = FavMoviesFragment.class.getSimpleName();
    private MovieAdapter favMoviesAdapter;
    private RecyclerView recyclervFavMovies;
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private MovieHelper moviesHelper;
    private ProgressBar progressBarMovie;
    private TextView textViewEmpty;
    private ArrayList<MovieFav> list;
    public FavMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclervFavMovies = view.findViewById(R.id.rv_tab_movies_room);
        recyclervFavMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclervFavMovies.setHasFixedSize(true);
        moviesHelper = new MovieHelper(this.getActivity());

        moviesHelper.open();
        list = moviesHelper.query();
        favMoviesAdapter = new FavMoviesAdapter(this.getActivity());
        recyclervFavMovies.setAdapter(favMoviesAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, getContext());

        if (getContext() != null) {
            getContext().getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        }

        evetListener();
        checkingMovieList();

        if (savedInstanceState == null) {
            new LoadMoviesAsync(getContext(), this).execute();
        } else {
            list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                favMoviesAdapter.setListMoviesm(list);
            }
        }

    }

    private void callDataViewModel() {

    }

    @Override
    public void postExecute(Cursor cursor) {

    }

    @Override
    public void preExecute() {

    }
}
