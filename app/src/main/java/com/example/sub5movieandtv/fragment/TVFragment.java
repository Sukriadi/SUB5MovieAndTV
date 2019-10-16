package com.example.sub5movieandtv.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sub5movieandtv.adapter.ListTVAdapter;
import com.example.sub5movieandtv.model.MainViewModel;
import com.example.sub5movieandtv.R;
import com.example.sub5movieandtv.TV;
//import com.example.sub3movieandtv.TVData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {
    private RecyclerView rvTV;
    private MainViewModel mainViewModel;
    private  ListTVAdapter listTVAdapter;
    private ProgressBar progressBar;
    public TVFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv,container,false);
        rvTV = view.findViewById(R.id.rv_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        rvTV.setHasFixedSize(true);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getListTvShow().observe(getActivity(), getTV);
        listTVAdapter = new ListTVAdapter(getActivity());

        showRecyclerListTV();

        return view;

    }

    private Observer<ArrayList<TV>> getTV = new Observer<ArrayList<TV>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TV> tvItem) {
            if (tvItem != null) {
                if (listTVAdapter != null){
                    listTVAdapter.setListTv(tvItem);
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    };

    private void showRecyclerListTV(){
        mainViewModel.setListTvShow(getActivity());
        listTVAdapter.notifyDataSetChanged();
        rvTV.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvTV.setAdapter(listTVAdapter);

    }


}
