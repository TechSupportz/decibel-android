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

        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnLoop = findViewById(R.id.btnLoop);
        btnLike = findViewById(R.id.likedBtn);
        background = findViewById(R.id.backgroundImage);
        songProgTxt = findViewById(R.id.songProgTxt);
        songDurationTxt = findViewById(R.id.songDurationTxt);

        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        songIndexList = songData.getIntegerArrayList("songIndexList");
        currentIndexPos = songIndexList.indexOf(currentIndex);

        Log.d("temasek", "Retrieved position is:" + currentIndex);
        Log.d("queue", "SongIndexes: " + songIndexList);
        displaySongBasedOnIndex(currentIndex);
        player.reset();
        playSong(fileLink);
        loadData();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });

    }

    public void displaySongBasedOnIndex(int selectedIndex) {

        Song song = songCollection.getCurrentSong(currentIndex);

        this.id = song.getId();
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.genre = WordUtils.capitalizeFully(song.getGenre());
        this.fileLink = song.getSongLink();
        this.coverArt = song.getCoverArt();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);

        TextView txtGenre = findViewById(R.id.txtGenre);
        txtGenre.setText(genre);

        Log.d("Genre", "genre is " + genre);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.get().load(coverArt).into(iCoverArt);

        checkLike();
        backgroundTint();
    }


    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            handler.removeCallbacks(progressBar);
            handler.postDelayed(progressBar, 0); //activates runnable
            StopWhenMusicEnds();
            setTitle(title);
            spinCoverArt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void StopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                songProgTxt.setText(createTimeLabel(player.getDuration()));
                handler.removeCallbacks(progressBar);

                if (!loopFlag){
                    playNext(null);
                }
                else {
                    playOrPauseMusic(null);
                }
            }
        });
    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setImageResource(R.drawable.play_icon);
            handler.removeCallbacks(progressBar);


        } else {
            player.start();
            btnPlayPause.setImageResource(R.drawable.pause_icon);
            handler.postDelayed(progressBar, 0);
            spinCoverArt();
        }
    }

    public int getNextIndex(){
        if (currentIndexPos == (songIndexList.size()-1)) {
            currentIndex = songIndexList.get(0);
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        else {
            currentIndex = songIndexList.get(currentIndexPos + 1);
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        return currentIndex;
    }

    public int getPrevIndex(){
        if (currentIndexPos == 0) {
            currentIndex = songIndexList.get(songIndexList.size()-1);
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        else {
            currentIndex = songIndexList.get(currentIndexPos - 1);
            currentIndexPos = songIndexList.indexOf(currentIndex);
        }
        return currentIndex;
    }

    public void playNext(View view) {
        if (shuffleFlag){
            int max = (songIndexList.size() - 1);
            int min = 0;
            int random = (new Random()).nextInt((max - min) + 1) + min;


            if (random == currentIndex){
                random = random+1;
                currentIndex = songIndexList.get(random);
            }
            else{
                currentIndex = songIndexList.get(random);
            }
        }
        else {
            currentIndex = getNextIndex();
        }
        currentIndexPos = songIndexList.indexOf(currentIndex);
        Log.d("temasek", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        btnPlayPause.setImageResource(R.drawable.pause_icon);
        backgroundTint();
    }

    public void playPrevious(View view) {
        currentIndex = getPrevIndex();
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        btnPlayPause.setImageResource(R.drawable.pause_icon);
        backgroundTint();
    }

    public void toggleShuffle(View view) {
        if (shuffleFlag) {
            btnShuffle.animate().alpha(0.3f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            btnShuffle.animate().alpha(1f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();

        }
        shuffleFlag = !shuffleFlag;
    }

    public void toggleLoop(View view) {
        if (loopFlag) {
            btnLoop.animate().alpha(0.3f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            btnLoop.animate().alpha(1f).setDuration(150).setInterpolator(new AccelerateInterpolator()).start();
        }
        loopFlag = !loopFlag; //Set loopFlag to the opposite state of what it was before the loop button was pressed
    }

    public void likeToggle(View view) {
        if (likeFlag==false) {
            btnLike.animate().alpha(1f).setDuration(150).start();
            playlistCollection.addToLikedList(id);
            saveData(PlaylistCollection.likedList);
        }
        else{
            btnLike.animate().alpha(0f).setDuration(150).start();
            playlistCollection.removeFrmLikedList(id);
            saveData(PlaylistCollection.likedList);
        }
        Log.d("Liked", " " + PlaylistCollection.likedList.size());
        likeFlag = !likeFlag;
    }

    public void checkLike(){
        if (playlistCollection.isPresent(id)){
            btnLike.animate().alpha(1f).setDuration(0).start();
            likeFlag = true;
        }
        else{
            btnLike.animate().alpha(0f).setDuration(0).start();
            likeFlag = false;
        }
    }

    public void saveData(List<Song> saveList){
        SharedPreferences sharedPreferences = getSharedPreferences("shared pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saveList);
        editor.putString("playlist", json);
        editor.apply();
        Log.d("liked", json);
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



    public void spinCoverArt() {
        rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.imgCoverArt).startAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                isSongPlaying();
            }
        });
    }

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

        Picasso.get().load(this.coverArt).into(new Target() {
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


    Runnable progressBar = new Runnable() {
        int count = 0;
        @Override
        public void run() {
            Log.d("temasek", "running" + count++);
            if (player != null && player.isPlaying()) {
                int currentPos = player.getCurrentPosition();
                int duration = player.getDuration();
                seekBar.setMax(duration);
                seekBar.setProgress(currentPos);
                String timerLabel = createTimeLabel(duration);
                songDurationTxt.setText(timerLabel);
                songProgTxt.setText(createTimeLabel(currentPos));
                handler.postDelayed(this, 1000); //calls runnable repeatedly every 1000ms (1s)
            }
        }
    };


    public void goHome(View view) {
        if (player.isPlaying()) {
            player.release();
            rotateAnimation.cancel();
            handler.removeCallbacks(progressBar);
        }
        Intent goHome = new Intent(this, MainActivity.class);
        this.startActivity(goHome);
        Bungee.slideDown(this);
    }

    public void onBackPressed() {
        if (player.isPlaying()) {
            player.release();
            rotateAnimation.cancel();
            handler.removeCallbacks(progressBar);
        }
        super.onBackPressed();
        Bungee.slideDown(this);
        loadData();
    }
}

