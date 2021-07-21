package com.example.decibel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private String coverArt;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    SongCollection songCollection = new SongCollection();
    List<Song> shuffleList = songCollection.getSongList();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "Retrieved position is:" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnLoop = findViewById(R.id.btnLoop);
        background = findViewById(R.id.backgroundImage);
        songProgTxt = findViewById(R.id.songProgTxt);
        songDurationTxt = findViewById(R.id.songDurationTxt);

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

        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.fileLink = song.getFindLink();
        this.coverArt = song.getCoverArt();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.get().load(coverArt).into(iCoverArt);

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
            gracefullyStopWhenMusicEnds();
            setTitle(title);
            spinCoverArt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gracefullyStopWhenMusicEnds(){
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

    public void playNext(View view) {
        if (shuffleFlag){
            int max = (songCollection.getSongList()).size() - 1;
            int min = 0;
            int random = (new Random()).nextInt((max - min) + 1) + min;

            if (random == currentIndex){
                currentIndex = random+1;
            }
            else{
                currentIndex = random;
            }
        }
        else {
            currentIndex = songCollection.getNextSong(currentIndex);
        }
        Log.d("temasek", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        backgroundTint();
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        backgroundTint();
    }

    public void toggleShuffle(View view) {
        if (shuffleFlag) {
            btnShuffle.animate().alpha(0.3f).setDuration(300).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            btnShuffle.animate().alpha(1f).setDuration(300).setInterpolator(new AccelerateInterpolator()).start();

        }
        shuffleFlag = !shuffleFlag;
    }

    public void toggleLoop(View view) {
        if (loopFlag) {
            btnLoop.animate().alpha(0.3f).setDuration(300).setInterpolator(new AccelerateInterpolator()).start();
        }
        else{
            btnLoop.animate().alpha(1f).setDuration(300).setInterpolator(new AccelerateInterpolator()).start();
        }
        loopFlag = !loopFlag; //Set loopFlag to the opposite state of what it was before the loop button was pressed
    }

    public void goHome(View view) {
        Intent goHome = new Intent(this, MainActivity.class);
        this.startActivity(goHome);
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
                int duration = player.getDuration();
                int currentPos = player.getCurrentPosition();
                seekBar.setMax(duration);
                seekBar.setProgress(currentPos);
                String timerLabel = createTimeLabel(duration);
                songDurationTxt.setText(timerLabel);
                songProgTxt.setText(createTimeLabel(currentPos));
                handler.postDelayed(this, 1000); //calls runnable repeatedly every 1000ms (1s)
            }
        }
    };




    public void onBackPressed() {
        if (player.isPlaying()) {
            player.release();
            handler.removeCallbacks(progressBar);
        }
        super.onBackPressed();
    }
}

