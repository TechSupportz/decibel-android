package com.example.decibel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.decibel.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager2;
    private ImageView background;

    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();
    List<Song> songList = songCollection.getSongList();
    List<Playlist> userPlaylist = playlistCollection.userPlaylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.backgroundImage);
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        recyclerView = findViewById(R.id.songList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(songList, this);
        recyclerView.setAdapter(mAdapter);

        recyclerView2 = findViewById(R.id.playlistList);
        recyclerView2.setHasFixedSize(true);

        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        mAdapter2 = new RecycleViewAdapter2(playlistCollection.presetPlaylist, this);
        recyclerView2.setAdapter(mAdapter2);
    }
}