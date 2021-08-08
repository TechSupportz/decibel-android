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
    //variable declaration
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
        //setting colour of the background gradient/glow
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        recyclerView = findViewById(R.id.forYouSongList);
        //setting recyclerView to have fixed size as the size of contents inside it doesnt change
        recyclerView.setHasFixedSize(true);

        //sets recyclerView to be horizontal
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //provides the list that the recycler view will display
        mAdapter = new RecycleViewAdapter(forYou, this);
        recyclerView.setAdapter(mAdapter);


        recyclerView2 = findViewById(R.id.playlistList);
        //setting recyclerView to have fixed size as the size of contents inside it doesnt change
        recyclerView2.setHasFixedSize(true);

        //sets recyclerView to be horizontal
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        //provides the list that the recycler view will display
        mAdapter2 = new RecycleViewAdapter2(playlistCollection.presetPlaylist, this);
        recyclerView2.setAdapter(mAdapter2);

        //setting recyclerView to have fixed size as the size of contents inside it doesnt change
        recyclerView3 = findViewById(R.id.artistList);
        recyclerView3.setHasFixedSize(true);

        //sets recyclerView to be horizontal
        layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager3);

        //provides the list that the recycler view will display
        mAdapter3 = new RecycleViewAdapter3(playlistCollection.artistPlaylist, this);
        recyclerView3.setAdapter(mAdapter3);

        //fills in the preset playlists with their respective songs
        playlistCollection.collatePlaylists();
        //loads the liked song shared preferences
        loadData();
    }

    public void loadData(){
        //gets the shared pref file from device storage
        SharedPreferences sharedPreferences = getSharedPreferences("shared pref", MODE_PRIVATE);
        //retrieves the list from the shared pref
        Gson gson = new Gson();
        String json = sharedPreferences.getString("playlist", "");
        if (!json.equals("")) {
            Type type = new TypeToken<ArrayList<Song>>() {}.getType();
            PlaylistCollection.likedList = gson.fromJson(json, type);
        }
    }

    public void goLibrary(View view) {
        //creates intent to navigate to the library page
        Intent intent = new Intent(this, LibraryActivity.class);
        //calls the intent to navigate to the library page
        this.startActivity(intent);
        //transition animation
        Bungee.slideRight(this);
    }

    public void goLiked(View view) {
        //creates calls an intent to navigate to the liked page
        Intent intent = new Intent(this, PlaylistActivity.class);
        //adds the information PlaylistActivity would have to retrieve to display the liked playlist into a bundle
        Bundle extras = new Bundle();
        extras.putInt("index", 0);
        extras.putString("listType", "custom");
        //adds the bundle into the extras to be sent over to PlaylistActivity
        intent.putExtras(extras);
        //calls the intent to navigate to the PlaylistActivity page
        this.startActivity(intent);
        //transition animation
        Bungee.slideLeft(this);
    }
}