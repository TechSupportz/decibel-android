package com.example.decibel.Adapters;

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

import com.example.decibel.PlaySongActivity;
import com.example.decibel.PlaylistActivity;
import com.example.decibel.R;
import com.example.decibel.Song;
import com.example.decibel.SongCollection;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlaylistRecycleViewAdapter extends RecyclerView.Adapter<PlaylistRecycleViewAdapter.MyViewHolder> {

    SongCollection songCollection = new SongCollection();
    PlaylistActivity playlistActivity = new PlaylistActivity();
    List<Integer> playlistSongIndex = PlaylistActivity.playlistSongIndex;
    List<Song> songList;
    Context context;

    public PlaylistRecycleViewAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlaylistRecycleViewAdapter.MyViewHolder holder, int position) {
        holder.songName.setText(songList.get(position).getTitle());
        holder.artistName.setText(songList.get(position).getArtist());
        Picasso.get().load(songList.get(position).getCoverArt()).into(holder.coverArt);
        String id = songList.get(position).getId();

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("temasek", "onClick: The song clicked was " + ""+id);
                int index = songCollection.searchSongById(id);
                Intent intent = new Intent(context, PlaySongActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("index", index);
                extras.putIntegerArrayList("songIndexList", (ArrayList<Integer>) playlistSongIndex);
                Log.d("queue", "playlistSongIndex size: " + playlistSongIndex.size());
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

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            coverArt = itemView.findViewById(R.id.songCover);
            songName = itemView.findViewById(R.id.songTitle);
            artistName = itemView.findViewById(R.id.artist);
            parentLayout = itemView.findViewById(R.id.songListLayout);
        }
    }
}
