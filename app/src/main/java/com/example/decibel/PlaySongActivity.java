package com.example.decibel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import spencerstudios.com.bungeelib.Bungee;

public class PlaySongActivity extends AppCompatActivity {
    //variable declarations
    private String id;
    private String title;
    private String artist;
    private String genre;
    private String fileLink;
    private String coverArt;
    private int currentIndex = -1;
    private int currentIndexPos = -1;
    private List<Integer> songIndexList = new ArrayList<>();

    private MediaPlayer player = new MediaPlayer();
    SongCollection songCollection = new SongCollection();
    PlaylistCollection playlistCollection = new PlaylistCollection();
    private Palette.Swatch dominantSwatch;
    RotateAnimation rotateAnimation;
    SeekBar seekBar;
    ImageButton btnPlayPause;
    ImageView background;
    TextView songProgTxt;
    TextView songDurationTxt;
    Handler handler = new Handler();

    ImageButton btnShuffle;
    Boolean shuffleFlag = false;

    ImageButton btnLoop;
    Boolean loopFlag = false;

    ImageView btnLike;
    Boolean likeFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        //variable assigning
        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnLoop = findViewById(R.id.btnLoop);
        btnLike = findViewById(R.id.likedBtn);
        background = findViewById(R.id.backgroundImage);
        songProgTxt = findViewById(R.id.songProgTxt);
        songDurationTxt = findViewById(R.id.songDurationTxt);

        //retrives extras and gets data present in the bundle put in the extra
        Bundle songData = this.getIntent().getExtras();
        //sets currentIndex to the index of the song pressed
        currentIndex = songData.getInt("index");
        //gets the song index list with contains the index of songs to be lopped between for playlists
        songIndexList = songData.getIntegerArrayList("songIndexList");
        //gets position of current index in the songIndexList
        currentIndexPos = songIndexList.indexOf(currentIndex);

        //Logs to ensure positions are retrieved correctly
        Log.d("queue", "Retrieved position is:" + currentIndex);
        Log.d("queue", "SongIndexes: " + songIndexList);
        //calls method to display song information
        displaySongBasedOnIndex(currentIndex);
        //resets the media player
        player.reset();
        //calls method to play the song
        playSong(fileLink);
        //loads the shared preferences details to update liked button accordingly
        loadData();

        //detects seekbar changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //once user drags seekbar and releases it the player will seek to the position of the seekbar and play music at that time
                player.seekTo(seekBar.getProgress());
            }
        });

    }

    public void displaySongBasedOnIndex(int selectedIndex) {
        //gets song that has the index of currentIndex in songCollection
        Song song = songCollection.getCurrentSong(currentIndex);

        //assigns id of song to local variable
        this.id = song.getId();
        //assigns title of song to local variable
        this.title = song.getTitle();
        //assigns artist of song to local variable
        this.artist = song.getArtist();
        //assigns genre of song to local variable and capitalises first letters
        this.genre = WordUtils.capitalizeFully(song.getGenre());
        //assigns fileLink of song to local variable
        this.fileLink = song.getSongLink();
        //assigns coverArt of song to local variable
        this.coverArt = song.getCoverArt();

        //declares and assigns txtTitle
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        //sets txtTitle to the song Title
        txtTitle.setText(title);

        //declares and assigns txtArtist
        TextView txtArtist = findViewById(R.id.txtArtist);
        //sets txtArtist to the song artist
        txtArtist.setText(artist);

        //declares and assigns txtGenre
        TextView txtGenre = findViewById(R.id.txtGenre);
        //sets txtGenre to the song genre
        txtGenre.setText(genre);

        //declares and assigns iCoverArt
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        //loads song cover art from url and sets it to iCoverArt
        Picasso.get().load(coverArt).into(iCoverArt);

        //calls method to check if son is liked to update like button
        checkLike();
        //calls method to extract dominant color from cover art and set it as the background gradient/glow color
        backgroundTint();
    }


    public void playSong(String songUrl) {
        try {
            //resets player
            player.reset();
            //sets datasource as the song file link
            player.setDataSource(songUrl);
            //prepares the player
            player.prepare();
            //starts the player
            player.start();
            //removes existing callbacks from seekbar runnable
            handler.removeCallbacks(progressBar);
            //activates the seekbar runnable
            handler.postDelayed(progressBar, 0);
            //calls the method to perform actions when song ends
            StopWhenMusicEnds();
            //calls method to spin cover art
            spinCoverArt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void StopWhenMusicEnds(){
        //listens to when media player ends
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            //gets called when media player has completed playing a song
            public void onCompletion(MediaPlayer mp) {
                //sets duration timer text to duration of song
                songProgTxt.setText(createTimeLabel(player.getDuration()));
                //removes seekbar runnable callbacks
                handler.removeCallbacks(progressBar);

                //function to check if loop is activated
                if (!loopFlag){
                    //calls play nest song method
                    playNext(null);
                }
                else {
                    //calls playOrPauseMusic method to loop the song
                    playOrPauseMusic(null);
                }
            }
        });
    }

    public void playOrPauseMusic(View view) {
        //checks state of player (playing song or not)
        if (player.isPlaying()) {
            //pauses player
            player.pause();
            //updates play button icon to play icon
            btnPlayPause.setImageResource(R.drawable.play_icon);
            //removes callbacks of seekbar
            handler.removeCallbacks(progressBar);


        } else {
            //unpause the player
            player.start();
            //updates play button icon to pause icon
            btnPlayPause.setImageResource(R.drawable.pause_icon);
            //activates the seekbar runnable
            handler.postDelayed(progressBar, 0);
            //calls method to spin cover art
            spinCoverArt();
        }
    }

    //gets the index of next song
    public int getNextIndex(){
        //checks if song is last in IndexList to determine whether to loop the playlist to prevent app from crashing
        if (currentIndexPos == (songIndexList.size()-1)) {
            //sets current index to first song in songIndexList
            currentIndex = songIndexList.get(0);
            //updates currentIndexPos
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        else {
            //gets next song index
            currentIndex = songIndexList.get(currentIndexPos + 1);
            //updates currentIndexPos
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        return currentIndex;
    }

    //gets the index of prev song
    public int getPrevIndex(){
        //checks if song is first in IndexList to determine whether to loop the playlist to prevent app from crashing
        if (currentIndexPos == 0) {
            //sets current index to last song in songIndexList
            currentIndex = songIndexList.get(songIndexList.size()-1);
            //updates currentIndexPos
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        else {
            //gets previous song index
            currentIndex = songIndexList.get(currentIndexPos - 1);
            //updates currentIndexPos
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        return currentIndex;
    }

    //plays next song
    public void playNext(View view) {
        //checks if shuffle is on
        if (shuffleFlag){
            //generates a random number based on songIndexList Size
            int max = (songIndexList.size() - 1);
            int min = 0;
            int random = (new Random()).nextInt((max - min) + 1) + min;

            //ensures random index generated isnt sam as current index
            if (random == currentIndex){
                random = random+1;
                currentIndex = songIndexList.get(random);
            }
            else{
                currentIndex = songIndexList.get(random);
            }
        }
        else {
            //calls getNextIndex method
            currentIndex = getNextIndex();
        }
        //updates currentIndexPos
        currentIndexPos = songIndexList.indexOf(currentIndex);
        Log.d("queue", "After playNext, the index is now :" + currentIndex);
        //calls method to display song information
        displaySongBasedOnIndex(currentIndex);
        //calls method to play the song
        playSong(fileLink);
        //sets playOrPauseBtn icon to pause
        btnPlayPause.setImageResource(R.drawable.pause_icon);
        //calls method to extract dominant color from cover art and set it as the background gradient/glow color
        backgroundTint();
    }

    public void playPrevious(View view) {
        //calls getPrevIndex method
        currentIndex = getPrevIndex();
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        //calls method to display song information
        displaySongBasedOnIndex(currentIndex);
        //calls method to play the song
        playSong(fileLink);
        //sets playOrPauseBtn icon to pause
        btnPlayPause.setImageResource(R.drawable.pause_icon);
        //calls method to extract dominant color from cover art and set it as the background gradient/glow color
        backgroundTint();
    }

    public void toggleShuffle(View view) {
        //checks if shuffle is on or off
        if (shuffleFlag) {
            //animated transparency of button to give visual feedback to user so they now if shuffle is off
            btnShuffle.animate().alpha(0.3f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            //animated transparency of button to give visual feedback to user so they now if shuffle is on
            btnShuffle.animate().alpha(1f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();

        }
        //Set shuffleFlag to the opposite state of what it was before the loop button was pressed
        shuffleFlag = !shuffleFlag;
    }

    public void toggleLoop(View view) {
        //checks if shuffle is on or off
        if (loopFlag) {
            //animated transparency of button to give visual feedback to user so they now if loop is off
            btnLoop.animate().alpha(0.3f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            //animated transparency of button to give visual feedback to user so they now if shuffle is on
            btnLoop.animate().alpha(1f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        //Set loopFlag to the opposite state of what it was before the loop button was pressed
        loopFlag = !loopFlag;
    }

    //checks if songs are liked
    public void checkLike(){
        if (playlistCollection.isPresent(id)){
            //set transparency of button to give visual feedback to user to show song is liked
            btnLike.animate().alpha(1f).setDuration(0).start();
            likeFlag = true;
        }
        else{
            //set transparency of button to give visual feedback to user to show song is not liked
            btnLike.animate().alpha(0f).setDuration(0).start();
            likeFlag = false;
        }
    }

    //toggles and adds songs into liked list
    public void likeToggle(View view) {
        if (likeFlag==false) {
            //set transparency of button to give visual feedback to user to show song is liked
            btnLike.animate().alpha(1f).setDuration(150).start();
            //adds song to liked list
            playlistCollection.addToLikedList(id);
            //saves changes to shared preferences
            saveData(PlaylistCollection.likedList);
        }
        else{
            //set transparency of button to give visual feedback to user to show song is unliked
            btnLike.animate().alpha(0f).setDuration(150).start();
            //removes song frm liked list
            playlistCollection.removeFrmLikedList(id);
            //saves changes to shared preferences
            saveData(PlaylistCollection.likedList);
        }
        Log.d("Liked", " " + PlaylistCollection.likedList.size());
        //Set likeFlag to the opposite state of what it was before the loop button was pressed
        likeFlag = !likeFlag;
    }

    public void saveData(List<Song> saveList){
        //creates shared pref
        SharedPreferences sharedPreferences = getSharedPreferences("shared pref", MODE_PRIVATE);
        //edits and saves file in shared pref
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saveList);
        editor.putString("playlist", json);
        editor.apply();
        Log.d("liked", json);
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


    //converts ms to mins and seconds for rime label
    public String createTimeLabel (int duration){
        String timerLabel= "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        timerLabel = min + ":";

        if (sec <10){
            timerLabel += "0";
            timerLabel += sec;
        }
        else{
            timerLabel += sec;
        }

        return timerLabel;

    }


    //animation of rotation of cover art
    public void spinCoverArt() {
        //sets rotation animation
        rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        //interpolates animation so its linear
        rotateAnimation.setInterpolator(new LinearInterpolator());
        //sets duration of one rotation
        rotateAnimation.setDuration(3000);
        //infinitely repeats animation
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        //starts animation
        findViewById(R.id.imgCoverArt).startAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            //calls a method to check on player status every rotation
            public void onAnimationRepeat(Animation animation) {
                isSongPlaying();
            }
        });
    }

    //only repeats animation if player is playing and stops is at central position when music is paused
    public void isSongPlaying(){
        if(player.isPlaying()){
            Log.d("spin", "Player is playing");
        }
        else{
            findViewById(R.id.imgCoverArt).clearAnimation();
        }
    }


    public void backgroundTint() {
        dominantSwatch = null;

        //loads current album art as a Bitmap into the palette api
        Picasso.get().load(this.coverArt).into(new Target() {
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

    //runs seekbar runnable
    Runnable progressBar = new Runnable() {
        int count = 0;
        @Override
        public void run() {
            Log.d("temasek", "running" + count++);
            if (player != null && player.isPlaying()) {
                //gets current position of player
                int currentPos = player.getCurrentPosition();
                //gets duration song in player
                int duration = player.getDuration();
                //sets max as duration
                seekBar.setMax(duration);
                //updates current position
                seekBar.setProgress(currentPos);
                //sets song duration label with max song duration in mins and seconds
                String timerLabel = createTimeLabel(duration);
                songDurationTxt.setText(timerLabel);
                //sets progress lable of song in mins and seconds
                songProgTxt.setText(createTimeLabel(currentPos));
                //calls runnable repeatedly every 1000ms (1s)
                handler.postDelayed(this, 1000);
            }
        }
    };

    //brings user to homepage when home button is pressed
    public void goHome(View view) {
        //stops player if it is playing
        if (player.isPlaying()) {
            //releases media player
            player.release();
            //stops cover art animation
            rotateAnimation.cancel();
            //removes callbacks of progress bar
            handler.removeCallbacks(progressBar);
        }
        //creates intent to go to MainActivity(homepage)
        Intent goHome = new Intent(this, MainActivity.class);
        //calls intent to go to MainActivity(homepage)
        this.startActivity(goHome);
        //transition animation
        Bungee.slideDown(this);
    }

    //brings user back when back is pressed and stops player
    public void onBackPressed() {
        if (player.isPlaying()) {
            //releases media player
            player.release();
            //stops cover art animation
            rotateAnimation.cancel();
            //removes callbacks of progress bar
            handler.removeCallbacks(progressBar);
        }
        super.onBackPressed();
        //transition animation
        Bungee.slideDown(this);
    }
}

