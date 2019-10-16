package com.example.sub5movieandtv.other;

import android.view.View;

import com.example.sub5movieandtv.OnItemClick;

public class CustomOnItemClickListener implements View.OnClickListener  {
    private int position;
    private OnItemClick.OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int position, OnItemClick.OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }

    public interface onItemClickCallback {
        void onItemClicked(View view, int position);


    }

}
