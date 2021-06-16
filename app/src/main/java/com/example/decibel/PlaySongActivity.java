package com.example.decibel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decibel.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink= "";
    private String coverArt;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private SongCollection songCollection = new SongCollection();
    SeekBar seekBar;
    ImageButton btnPlayPause;
    Handler handler = new Handler();

    public void displaySongBasedOnIndex(int selectedIndex){

        Song song = songCollection.getCurrentSong(currentIndex);

        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.fileLink = song.getFindLink();
        this.coverArt  = song.getCoverArt();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.get().load(coverArt).into(iCoverArt);
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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {
            player.pause();
            //btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
            handler.removeCallbacks(progressBar);
        }
        else {
            player.start();
           // btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            handler.postDelayed(progressBar, 0);
        }
    }

    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext, \nthe current index of this song\n" +
                "in the SongCollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious, \nthe current index of this song\n" +
                "in the SongCollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }


    private void gracefullyStopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(), "The song has ended and the onCompleteListener is activated\n" +
                        "The button text is changed to 'PLAY'", Toast.LENGTH_LONG).show();
                //btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
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