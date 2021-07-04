package com.example.decibel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;

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

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private String coverArt;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private SongCollection songCollection = new SongCollection();
    private ConstraintLayout playSongConstraint;
    RotateAnimation rotateAnimation;
    SeekBar seekBar;
    ImageButton btnPlayPause;
    ImageView background;
    Bitmap bitmapImage;
    Handler handler = new Handler();

    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;
    private Palette.Swatch dominantSwatch;


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

        backgroudTint();
    }


    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            handler.removeCallbacks(progressBar);
            handler.postDelayed(progressBar, 10); //activates runnable
            gracefullyStopWhenMusicEnds();
            setTitle(title);
            spinCoverArt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
            handler.removeCallbacks(progressBar);


        } else {
            player.start();
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            handler.postDelayed(progressBar, 0);
            spinCoverArt();
        }
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

    public void backgroudTint() {

        vibrantSwatch = null;
        lightVibrantSwatch = null;
        darkVibrantSwatch = null;
        mutedSwatch = null;
        lightMutedSwatch = null;
        darkMutedSwatch = null;
        dominantSwatch = null;

        Picasso.get().load(this.coverArt).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("Colour", "bitmap loaded");
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable @org.jetbrains.annotations.Nullable Palette palette) {
                        vibrantSwatch = palette.getVibrantSwatch();
                        lightVibrantSwatch = palette.getLightVibrantSwatch();
                        darkVibrantSwatch = palette.getDarkVibrantSwatch();
                        mutedSwatch = palette.getMutedSwatch();
                        lightMutedSwatch = palette.getLightMutedSwatch();
                        darkMutedSwatch = palette.getDarkMutedSwatch();
                        dominantSwatch = palette.getDominantSwatch();
                        Log.d("Colour", "the swatch is " + vibrantSwatch);

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


    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext, \nthe current index of this song\n" +
                "in the SongCollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        backgroudTint();
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious, \nthe current index of this song\n" +
                "in the SongCollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        backgroudTint();
    }


    private void gracefullyStopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(), "The song has ended and the onCompleteListener is activated\n" +
                        "The button text is changed to 'PLAY'", Toast.LENGTH_LONG).show();
                btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
                handler.removeCallbacks(progressBar);
            }
        });
    }

    Runnable progressBar = new Runnable() {
        int count = 0;
        @Override
        public void run() {
            Log.d("temasek", "running" + count++);
            if (player != null && player.isPlaying()) {
                seekBar.setMax(player.getDuration());
                seekBar.setProgress(player.getCurrentPosition());
                handler.postDelayed(this, 1000); //calls runnable repeatedly every 1000ms (1s)
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "Retrieved position is:" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);

        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        background = findViewById(R.id.background);

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


    public void onBackPressed() {
        if (player.isPlaying()) {
            player.release();
            handler.removeCallbacks(progressBar);
        }
        super.onBackPressed();
    }
}