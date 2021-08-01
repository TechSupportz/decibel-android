package com.example.decibel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private String id;
    private String name;;
    private String creator;
    private int coverArt;
    private List<Song> playlistSongs;
    private int currentIndex = -1;
    private String playlistType;
    private Playlist playlist;
    private RecyclerView playlistRecyclerView;
    private RecyclerView.Adapter playlistAdapter;
    private RecyclerView.LayoutManager playlistlayoutManager;


    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();

    private Palette.Swatch dominantSwatch;
    ImageView background;

    ImageButton btnShuffle;
    Boolean shuffleFlag = false;

    ImageButton btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        btnShuffle = findViewById(R.id.btnShuffle);
        btnPlay = findViewById(R.id.btnPlay);
        background = findViewById(R.id.backgroundImage);

        Bundle playlistData = this.getIntent().getExtras();
        currentIndex = playlistData.getInt("index");
        playlistType = playlistData.getString("listType");

        playlist = playlistCollection.getCurrentPlaylist(playlistType, currentIndex);

        playlistRecyclerView = findViewById(R.id.playlistRecyclerView);
        playlistRecyclerView.setHasFixedSize(true);

        playlistlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playlistRecyclerView.setLayoutManager(playlistlayoutManager);

        playlistAdapter = new PlaylistRecycleViewAdapter(playlist.getPlaylistSongs(), this);
        playlistRecyclerView.setAdapter(playlistAdapter);

        Log.d("temasek", "Retrieved position is:" + currentIndex);

        displayPlaylistBasedOnIndex();
        backgroundTint();

    }

    public void displayPlaylistBasedOnIndex(){

        this.id = playlist.getId();
        this.name = playlist.getName();
        this.creator = playlist.getCreator();
        this.playlistSongs = playlist.getPlaylistSongs();
        this.coverArt = playlist.getCoverArt();


        ImageView playlistCover;
        playlistCover = findViewById(R.id.playlistCover);
        Picasso.get().load(coverArt).into(playlistCover);

        TextView playlistName;
        playlistName = findViewById(R.id.playlistName);
        playlistName.setText(name);

        TextView playlistCreator;
        playlistCreator = findViewById(R.id.playlistCreator);
        playlistCreator.setText(creator);

    }

    public void backgroundTint() {

        dominantSwatch = null;

        Picasso.get().load(coverArt).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("Colour", "bitmap loaded");
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable @org.jetbrains.annotations.Nullable Palette palette) {
                        assert palette != null;
                        dominantSwatch = palette.getDominantSwatch();
                        Log.d("Colour", "the swatch is " + dominantSwatch);

                        if (dominantSwatch != null){
                            Log.d("Colour", "the dominant colour is " + dominantSwatch.getRgb());
                            background.setBackgroundColor(dominantSwatch.getRgb());
                        }
                    }
                });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("Colour", "bitmap fail");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("Colour", "bitmap prepare");
            }
        });
    }
    public void playSongs(View view) {
        String id = playlist.getPlaylistSongs().get(0).getId();
        int index = songCollection.searchSongById(id);
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        this.startActivity(intent);
    }
}