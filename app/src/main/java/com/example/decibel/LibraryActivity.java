package com.example.decibel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.decibel.Adapters.LibraryRecycleViewAdapter;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private RecyclerView libraryRecyclerView;
    private LibraryRecycleViewAdapter libraryAdapter;
    private RecyclerView.LayoutManager libraryLayoutManager;
    private ImageView background;
    SearchView searchView;

    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.getSongList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        background = findViewById(R.id.backgroundImage);
        background.setBackgroundColor(Color.parseColor("#45A7FB"));

        libraryRecyclerView = findViewById(R.id.librarySongList);
        libraryRecyclerView.setHasFixedSize(true);

        libraryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        libraryRecyclerView.setLayoutManager(libraryLayoutManager);

        libraryAdapter = new LibraryRecycleViewAdapter(songList, this);
        libraryRecyclerView.setAdapter(libraryAdapter);

        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                libraryAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}