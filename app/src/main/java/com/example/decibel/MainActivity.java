package com.example.decibel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.decibel.Adapters.RecycleViewAdapter;
import com.example.decibel.Adapters.RecycleViewAdapter2;
import com.example.decibel.Adapters.RecycleViewAdapter3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager2;
    private  RecyclerView recyclerView3;
    private RecyclerView.Adapter mAdapter3;
    private RecyclerView.LayoutManager layoutManager3;
    private ImageView background;

    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();
    List<Song> songList = songCollection.getSongList();
    List<Song> forYou = songCollection.getForYouList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.backgroundImage);
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        recyclerView = findViewById(R.id.forYouSongList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(forYou, this);
        recyclerView.setAdapter(mAdapter);

        recyclerView2 = findViewById(R.id.playlistList);
        recyclerView2.setHasFixedSize(true);

        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        mAdapter2 = new RecycleViewAdapter2(playlistCollection.presetPlaylist, this);
        recyclerView2.setAdapter(mAdapter2);

        recyclerView3 = findViewById(R.id.artistList);
        recyclerView3.setHasFixedSize(true);

        layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager3);

        mAdapter3 = new RecycleViewAdapter3(playlistCollection.artistPlaylist, this);
        recyclerView3.setAdapter(mAdapter3);

        playlistCollection.collatePlaylists();
        loadData();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared pref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("playlist", "");
        if (!json.equals("")) {
            Type type = new TypeToken<ArrayList<Song>>() {}.getType();
            PlaylistCollection.likedList = gson.fromJson(json, type);
        }
    }

    public void goLibrary(View view) {
        Intent intent = new Intent(this, LibraryActivity.class);
        this.startActivity(intent);
        Bungee.slideRight(this);
    }

    public void goLiked(View view) {
        Intent intent = new Intent(this, PlaylistActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("index", 0);
        extras.putString("listType", "custom");
        intent.putExtras(extras);
        this.startActivity(intent);
        Bungee.slideLeft(this);
    }
}