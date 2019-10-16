package com.example.sub5movieandtv.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sub5movieandtv.adapter.ListMovieAdapter;
import com.example.sub5movieandtv.model.MainViewModel;
import com.example.sub5movieandtv.Movie;
import com.example.sub5movieandtv.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private MainViewModel mainViewModel;
    private ListMovieAdapter movieItemsAdapter;
    private ProgressBar progressBar;
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie,container,false);
        progressBar = view.findViewById(R.id.progress_bar);
        rvMovies = view.findViewById(R.id.rv_movie);
        rvMovies.setHasFixedSize(true);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getListMovies().observe(getActivity(), getMovie);
        movieItemsAdapter = new ListMovieAdapter(getActivity());
        showRecyclerListMovie();
        return view;
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movieItems) {
            if (movieItems != null) {
                if (movieItemsAdapter != null){
                    movieItemsAdapter.setListMovie(movieItems);
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    };

    private void showRecyclerListMovie(){
        mainViewModel.setListMovies(getActivity());
        movieItemsAdapter.notifyDataSetChanged();
        rvMovies.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvMovies.setAdapter(movieItemsAdapter);

    }


}
