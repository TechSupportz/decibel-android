package com.example.decibel;


import android.util.Log;

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
        for (int i = 0; i < likedList.size(); i++){
            Log.d("Liked", "Liked list contains" + likedList.get(i).getId());
        }
    }

    public boolean isPresent(String id) {
        Song tempSong = songCollection.findSongById(id);
        for (int o = 0; o < likedList.size(); o++){
            if (tempSong.getId().equals((likedList.get(o).getId()))){
                return true;
            }
        }
        return false;
    }

    public void removeFrmLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.remove(tempSong);
    }


}
