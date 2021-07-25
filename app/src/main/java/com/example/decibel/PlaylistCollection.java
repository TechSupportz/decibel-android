package com.example.decibel;


import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlaylistCollection {

    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.songList;

    List<Playlist> presetPlaylist  = new ArrayList<>();
    List<Playlist> userPlaylist = new ArrayList<>();
    static List<Song> likedList = new ArrayList<>();

    public PlaylistCollection() {

        Playlist likedSongs = new Playlist(
                "Liked Songs",
                likedList,
                R.drawable.liked_song);



        userPlaylist.add(likedSongs);
    }

    public void addToLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.add(tempSong);
    }

    public void removeFrmLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.remove(tempSong);
    }


}
