package com.example.decibel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.decibel.Adapters.PlaylistRecycleViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class PlaylistActivity extends AppCompatActivity {
    //variable declarations
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
    public static List<Integer> playlistSongIndex = new ArrayList<>();


    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();

    private Palette.Swatch dominantSwatch;
    ImageView background;

    ImageButton btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        //variable assigning
        btnPlay = findViewById(R.id.btnPlay);
        background = findViewById(R.id.backgroundImage);

        //retrives extras and gets data present in the bundle put in the extra
        Bundle playlistData = this.getIntent().getExtras();
        //sets currentIndex to the index of the playlist pressed
        currentIndex = playlistData.getInt("index");
        //gets playlist type
        playlistType = playlistData.getString("listType");
        //gets playlist object from playlistCollection
        playlist = playlistCollection.getCurrentPlaylist(playlistType, currentIndex);

        playlistRecyclerView = findViewById(R.id.playlistRecyclerView);
        //setting recyclerView to have fixed size as the size of contents inside it doesnt change
        playlistRecyclerView.setHasFixedSize(true);

        //sets recyclerView to be vertical
        playlistlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playlistRecyclerView.setLayoutManager(playlistlayoutManager);

        //provides the list that the recycler view will display
        playlistAdapter = new PlaylistRecycleViewAdapter(playlist.getPlaylistSongs(), this);
        playlistRecyclerView.setAdapter(playlistAdapter);

        Log.d("playlistQueue", "Retrieved position is:" + currentIndex);

        //loads the liked song shared preferences
        loadData();
        //calls method to display playlist information
        displayPlaylistBasedOnIndex();
        //creates song index list
        createSongIndexList();
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

        //calls method to extract dominant color from cover art and set it as the background gradient/glow color
        backgroundTint();

    }

    public void backgroundTint() {

        dominantSwatch = null;

        //loads current album art as a Bitmap into the palette api
        Picasso.get().load(coverArt).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("Colour", "bitmap loaded");
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable @org.jetbrains.annotations.Nullable Palette palette) {
                        assert palette != null;
                        //gets dominant colour of coverart
                        dominantSwatch = palette.getDominantSwatch();
                        Log.d("Colour", "the swatch is " + dominantSwatch);

                        if (dominantSwatch != null){
                            //sets background gradient/glow colour as dominant colour
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

    public void createSongIndexList(){
        //clears playlistSongIndex
        playlistSongIndex.clear();
        //adds song songCollection index of the songs in the playlists into songIndexList
        for (int i = 0; i < playlistSongs.size(); i++) {
            Song tempSong = playlistSongs.get(i);
            String tempSongId = tempSong.getId();
            int songIndex = songCollection.searchSongById(tempSongId);
            playlistSongIndex.add(songIndex);
        }
    }

    //plays the playlist from the first song
    public void playSongs(View view) {
        //gets id of first song in playlist
        String id = playlist.getPlaylistSongs().get(0).getId();
        //gets index of first song in playlist
        int index = songCollection.searchSongById(id);
        //creates intent to go to playSongActivity
        Intent intent = new Intent(this, PlaySongActivity.class);
        //adds the information PlaylistActivity would have to retrieve to display the liked playlist into a bundle
        Bundle extras = new Bundle();
        extras.putInt("index", index);
        extras.putIntegerArrayList("songIndexList", (ArrayList<Integer>) playlistSongIndex);
        Log.d("queue", "playlistSongIndex size: " + playlistSongIndex.size());
        //adds the bundle into the extras to be sent over to PlaylistActivity
        intent.putExtras(extras);
        //calls the intent to navigate to the PlaylistActivity page
        this.startActivity(intent);
        //transition animation
        Bungee.slideUp(this);
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

    //brings user back when back button in bottom bar is pressed
    public void goBack(View view) {
        onBackPressed();
    }

    //brings user back when back is pressed
    public void onBackPressed() {
        super.onBackPressed();
        //transition animation
        Bungee.slideRight(this);
    }

}