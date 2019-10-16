package com.example.sub5movieandtv.fragment;


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


import com.example.sub5movieandtv.R;
import com.example.sub5movieandtv.roomdb.TvShowViewModel;
import com.example.sub5movieandtv.roomtvshow.TvShow;
import com.example.sub5movieandtv.roomtvshow.TvShowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavTvShowFragment extends Fragment {
    private RecyclerView recyclerViewTvShow;
    private TvShowAdapter tvShowAdapter;

    public FavTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewTvShow = view.findViewById(R.id.rv_tab_tvshow_room);
        recyclerViewTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        callDataViewModel();
    }

    private void callDataViewModel() {
        TvShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShowList().observe(this, new Observer<List<TvShow>>() {
            @Override
            public void onChanged(List<TvShow> tvShows) {
                tvShowAdapter = new TvShowAdapter(getContext(), (ArrayList<TvShow>) tvShows);
                tvShowAdapter.setTvshowList((ArrayList<TvShow>) tvShows);
                recyclerViewTvShow.setAdapter(tvShowAdapter);
            }
        });
    }
}
