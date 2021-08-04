package com.example.decibel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.decibel.Adapters.LibraryRecycleViewAdapter;
import com.example.decibel.Adapters.RecycleViewAdapter;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView background;

    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.getSongList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        background = findViewById(R.id.backgroundImage);
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        recyclerView = findViewById(R.id.librarySongList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new LibraryRecycleViewAdapter(songList, this);
        recyclerView.setAdapter(mAdapter);
    }
}