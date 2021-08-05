package com.example.decibel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.decibel.Adapters.LibraryRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

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
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        libraryRecyclerView = findViewById(R.id.forYouSongList);
        libraryRecyclerView.setHasFixedSize(true);

        libraryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        libraryRecyclerView.setLayoutManager(libraryLayoutManager);

        libraryAdapter = new LibraryRecycleViewAdapter(songList, this);
        libraryRecyclerView.setAdapter(libraryAdapter);

        searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
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

        libraryAdapter.filterList(filteredSongList);
    }

}}