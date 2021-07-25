package com.example.decibel;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;;
    private int coverArt;
    private List<Song> playlistSongs;

    public Playlist(String name, List<Song> playlistSongs , int coverArt){
        this.name = name;
        this.playlistSongs = playlistSongs;
        this.coverArt = coverArt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public void setCoverArt(int coverArt) {
        this.coverArt = coverArt;
    }

    public String getName() {
        return name;
    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public int getCoverArt() {
        return coverArt;
    }
}
