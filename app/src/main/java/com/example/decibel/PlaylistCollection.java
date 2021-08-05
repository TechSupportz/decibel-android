package com.example.decibel;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlaylistCollection {

    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.songList;

    List<Playlist> presetPlaylist  = new ArrayList<>();
    List<Playlist> customPlaylist = new ArrayList<>();
    static List<Song> likedList = new ArrayList<>();
    static List<Song> lofibeats = new ArrayList<>();
    static List<Song> popforDays = new ArrayList<>();


    public PlaylistCollection() {

        Playlist likedSongs = new Playlist(
                "P2000",
                "Liked Songs",
                "You",
                likedList,
                R.drawable.liked_song);


        presetPlaylist.add(likedSongs);

        Playlist lofiBeats = new Playlist(
                "P2001",
                "lofi beats",
                "Decibel",
                lofibeats,
                R.drawable.lofibeats_cover);

        Playlist popForDays = new Playlist(
                "P2002",
                "Pop for days",
                "Decibel",
                popforDays,
                R.drawable.home1);

        presetPlaylist.add(lofiBeats);
        presetPlaylist.add(popForDays);
    }

    public Playlist getCurrentPlaylist(String listType, int currentPlaylistIndex){
        if (listType.equals("preset")){
            return presetPlaylist.get(currentPlaylistIndex);
        }
        else if (listType.equals("custom")){
            return customPlaylist.get(currentPlaylistIndex);
        }
        return null;
    }

    public void addToLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.add(tempSong);
        for (int i = 0; i < likedList.size(); i++){
            Log.d("Liked", "Liked list contains" + (likedList.get(i)).getId());
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
        for (int i = 0; i < likedList.size(); i++){
            if (tempSong.getId().equals((likedList.get(i).getId()))) {
                likedList.remove(i);
                break;
            }
        }
    }

    public void addToLofiBeats(){
        if (lofibeats.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "lofi")) {
                    lofibeats.add(songList.get(i));
                    Log.d("lofiBeats", "lofiBeats contains " + songList.get(i));
                }
            }
        }
    }

    public void addToPop(){
        if (popforDays.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "pop")) {
                    popforDays.add(songList.get(i));
                    Log.d("pop", "pop contains " + songList.get(i));
                }
            }
        }
    }




}
