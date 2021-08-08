package com.example.decibel;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    //declare variable
    private String id;
    private String name;
    private String creator;
    private int coverArt;
    private List<Song> playlistSongs;

    public Playlist(String id, String name, String creator, List<Song> playlistSongs , int coverArt){
        //assign variables to respective values
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.playlistSongs = playlistSongs;
        this.coverArt = coverArt;
    }

    //setter methods
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
    public void setCoverArt(int coverArt) {
        this.coverArt = coverArt;
    }

    //getter methods
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCreator() {
        return creator;
    }
    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }
    public int getCoverArt() {
        return coverArt;
    }
}
