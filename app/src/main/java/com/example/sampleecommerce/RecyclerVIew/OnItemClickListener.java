package com.example.sampleecommerce.RecyclerVIew;

import android.view.View;

public class OnItemClickListener implements View.OnClickListener {

    private int position;
    private OnClickCallback onClickCallback;

    public OnItemClickListener(int position, OnClickCallback onClickCallback) {
        this.position = position;
        this.onClickCallback = onClickCallback;
    }
    @Override
    public void onClick(View v) {
        onClickCallback.onItemClicked(v, position);
    }

    public interface OnClickCallback{
        void onItemClicked(View view, int position);
    }
}
