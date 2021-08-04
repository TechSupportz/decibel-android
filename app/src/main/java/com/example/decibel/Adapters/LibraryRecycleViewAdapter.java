package com.example.decibel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decibel.PlaySongActivity;
import com.example.decibel.R;
import com.example.decibel.Song;
import com.example.decibel.SongCollection;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LibraryRecycleViewAdapter extends RecyclerView.Adapter<LibraryRecycleViewAdapter.MyViewHolder> implements Filterable {

    SongCollection songCollection = new SongCollection();
    List<Song> songList;
    List<Song> songListFull;
    Context context;

    public LibraryRecycleViewAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
        this.songListFull = new ArrayList<>(songList); //Creates copy of songList that can be edited independently
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LibraryRecycleViewAdapter.MyViewHolder holder, int position) {
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

    @Override
    public Filter getFilter(){
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Song> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(songListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Song song : songListFull) {
                    if (song.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(song);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            songList.clear();
            songList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


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
