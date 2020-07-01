package com.example.listadeitens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> items;

    public MyAdapter(MainActivity mainActivity, List<MyItem> items) {
        this.mainActivity = mainActivity;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View itemLayout = inflater.inflate(R.layout.item, parent,false);

        return new MyViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = items.get(position);

        ImageView imvPhoto = (ImageView) holder.itemView.findViewById(R.id.imgPhoto);
        imvPhoto.setImageURI(myItem.photo);

        TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvDescription = (TextView) holder.itemView.findViewById(R.id.tvDescription);
        tvDescription.setText(myItem.description);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
