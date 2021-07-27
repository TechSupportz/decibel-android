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
    static List<Song> lofibeats = new ArrayList<>();


    public PlaylistCollection() {

        Playlist likedSongs = new Playlist(
                "Liked Songs",
                likedList,
                R.drawable.liked_song);


        userPlaylist.add(likedSongs);

        Playlist lofiBeats = new Playlist(
                "lofi beats",
                lofibeats,
                R.drawable.lofibeats_cover);

        presetPlaylist.add(lofiBeats);
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
        for (int i = 0; i < likedList.size(); i++){
            if (tempSong.getId().equals((likedList.get(i).getId()))){
                return true;
            }
        }
        return false;
    }

    public void removeFrmLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.remove(tempSong);
    }

    public void addToLofiBeats(){
        for (int i = 0; i < songList.size(); i++){
            if ((songList.get(i).getGenre() == "lofi")) {
                lofibeats.add(songList.get(i));
                Log.d("lofiBeats", "lofiBeats contains " + songList.get(i) );
            }
        }

    }


    public void main(String[] args) {
        addToLofiBeats();
    }

}