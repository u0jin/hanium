package com.example.yesterday.yesterday.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yesterday.yesterday.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public TextView name;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        name = itemView.findViewById(R.id.recycler_name);
    }
}
