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

import spencerstudios.com.bungeelib.Bungee;

public class PlaylistRecycleViewAdapter extends RecyclerView.Adapter<PlaylistRecycleViewAdapter.MyViewHolder> {
    //declare variables
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
        //inflates layout of specified layout file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlaylistRecycleViewAdapter.MyViewHolder holder, int position) {
        //sets song title, artist name and cover art
        holder.songName.setText(songList.get(position).getTitle());
        holder.artistName.setText(songList.get(position).getArtist());
        Picasso.get().load(songList.get(position).getCoverArt()).into(holder.coverArt);
        String id = songList.get(position).getId();

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("temasek", "onClick: The song clicked was " + ""+id);
                //gets index of song
                int index = songCollection.searchSongById(id);
                //creates calls an intent to navigate to the PlaySongActivity page
                Intent intent = new Intent(context, PlaySongActivity.class);
                //adds the information PlaySongActivity would have to retrieve to display the liked playlist into a bundle
                Bundle extras = new Bundle();
                extras.putInt("index", index);
                extras.putIntegerArrayList("songIndexList", (ArrayList<Integer>) playlistSongIndex);
                //adds the bundle into the extras to be sent over to PlaySongActivity
                Log.d("queue", "playlistSongIndex size: " + playlistSongIndex.size());
                intent.putExtras(extras);
                //calls the intent to navigate to the PlaySongActivity page
                context.startActivity(intent);
                //transition animation
                Bungee.slideUp(context);
            }
        });
    }

    @Override
    //gets number of items in list
    public int getItemCount() {
        return songList.size();
    }

    //declares and assigns variables of
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
