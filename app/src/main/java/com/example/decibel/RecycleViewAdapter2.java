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
import androidx.annotation.StyleRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class RecycleViewAdapter2 extends RecyclerView.Adapter<RecycleViewAdapter2.MyViewHolder2> {

    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();
    List<Playlist> presetPlaylist;
    Context context;

    public RecycleViewAdapter2(List<Playlist> presetPlaylist, Context context) {
        this.presetPlaylist = presetPlaylist;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_song,parent,false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecycleViewAdapter2.MyViewHolder2 holder, int position) {
        holder.playlistName.setText(presetPlaylist.get(position).getName());
        holder.artistName.setText(presetPlaylist.get(position).getCreator());
        Picasso.get().load(presetPlaylist.get(position).getCoverArt()).into(holder.coverArt);

        String id = presetPlaylist.get(position).getId();

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("temasek", "onClick: The song clicked was " + position);
                Intent intent = new Intent(context, PlaylistActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("index", position);
                extras.putString("listType", "preset");
                intent.putExtras(extras);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return presetPlaylist.size();
    }

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
