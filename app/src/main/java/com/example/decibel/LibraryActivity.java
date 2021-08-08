package com.example.decibel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.decibel.Adapters.LibraryRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class LibraryActivity extends AppCompatActivity {
    //variable declarations
    private RecyclerView libraryRecyclerView;
    private LibraryRecycleViewAdapter libraryAdapter;
    private RecyclerView.LayoutManager libraryLayoutManager;
    private ImageView background;
    private EditText searchBar;

    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.getSongList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        background = findViewById(R.id.backgroundImage);
        //setting colour of the background gradient/glow
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        libraryRecyclerView = findViewById(R.id.forYouSongList);
        //setting recyclerView to have fixed size as the size of contents inside it doesnt change
        libraryRecyclerView.setHasFixedSize(true);

        //sets recyclerView to be horizontal
        libraryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        libraryRecyclerView.setLayoutManager(libraryLayoutManager);

        //provides the list that the recycler view will display
        libraryAdapter = new LibraryRecycleViewAdapter(songList, this);
        libraryRecyclerView.setAdapter(libraryAdapter);

        //variable assigning
        searchBar = findViewById(R.id.searchBar);
        //watches for changes in search bar
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            //calls a method after every chnage
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    //Method will be called everytime user enters or delete a letting in search bar
    private void filter(String query) {
        ArrayList<Song> filteredSongList = new ArrayList<>();

        for (Song searchedSong : songList) {
            //gets title of each song in songList, turns them into lowercase so search isn't case-sensitive and check if title contains text found in query
            if (searchedSong.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredSongList.add(searchedSong);
            }
        }
        libraryAdapter.filterList(filteredSongList);
    }

    //brings user back when back in bottom bar is pressed
    public void goBack(View view) {
        onBackPressed();
    }

    //brings user back when back is pressed
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideLeft(this);
    }
}
