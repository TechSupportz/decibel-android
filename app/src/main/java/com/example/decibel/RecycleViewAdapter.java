package com.example.decibel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    SongCollection songCollection = new SongCollection();
    List<Song> songList;
    Context context;

    public RecycleViewAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_song,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.songName.setText(songList.get(position).getTitle());
        holder.artistName.setText(songList.get(position).getArtist());
        Picasso.get().load(songList.get(position).getCoverArt()).into(holder.coverArt);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaySongActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("index", position);
                extras.putIntegerArrayList("songIndexList", songCollection.getIndexList());
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView coverArt;
        TextView songName;
        TextView artistName;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            coverArt = itemView.findViewById(R.id.coverArt);
            songName = itemView.findViewById(R.id.songName);
            artistName = itemView.findViewById(R.id.artistName);
            parentLayout = itemView.findViewById(R.id.oneSongLayout);
        }
    }
}
