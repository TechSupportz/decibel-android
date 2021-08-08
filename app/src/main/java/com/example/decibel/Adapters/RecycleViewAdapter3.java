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

import com.example.decibel.Playlist;
import com.example.decibel.PlaylistActivity;
import com.example.decibel.PlaylistCollection;
import com.example.decibel.R;
import com.example.decibel.SongCollection;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class RecycleViewAdapter3 extends RecyclerView.Adapter<RecycleViewAdapter3.MyViewHolder2> {
    //declare variables
    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();
    List<Playlist> artistPlaylist;
    Context context;

    public RecycleViewAdapter3(List<Playlist> artistPlaylist, Context context) {
        this.artistPlaylist = artistPlaylist;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //inflates layout of specified layout file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_song,parent,false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecycleViewAdapter3.MyViewHolder2 holder, int position) {
        //sets song title, artist name and cover art
        holder.playlistName.setText(artistPlaylist.get(position).getName());
        holder.artistName.setText(artistPlaylist.get(position).getCreator());
        Picasso.get().load(artistPlaylist.get(position).getCoverArt()).into(holder.coverArt);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("temasek", "onClick: The song clicked was " + position);
                //creates calls an intent to navigate to the liked page
                Intent intent = new Intent(context, PlaylistActivity.class);
                //adds the information PlaylistActivity would have to retrieve to display the liked playlist into a bundle
                Bundle extras = new Bundle();
                extras.putInt("index", position);
                extras.putString("listType", "artist");
                //adds the bundle into the extras to be sent over to PlaylistActivity
                intent.putExtras(extras);
                //calls the intent to navigate to the PlaylistActivity page
                context.startActivity(intent);
                //transition animation
                Bungee.slideLeft(context);
            }
        });
    }

    @Override
    //gets number of items in list
    public int getItemCount() {
        return artistPlaylist.size();
    }

    //declares and assigns variables of
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView coverArt;
        TextView playlistName;
        TextView artistName;
        ConstraintLayout parentLayout;

        public MyViewHolder2(@NonNull @NotNull View itemView) {
            super(itemView);
            coverArt = itemView.findViewById(R.id.coverArt);
            playlistName = itemView.findViewById(R.id.songName);
            artistName = itemView.findViewById(R.id.artistName);
            parentLayout = itemView.findViewById(R.id.oneSongLayout);
        }
    }

}
