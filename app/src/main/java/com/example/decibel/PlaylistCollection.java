package com.example.decibel;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlaylistCollection {

    //creation of playlists
    SongCollection songCollection = new SongCollection();
    List<Song> songList = songCollection.songList;

    List<Playlist> presetPlaylist  = new ArrayList<>();
    List<Playlist> customPlaylist = new ArrayList<>();
    List<Playlist> artistPlaylist = new ArrayList<>();
    static List<Song> likedList = new ArrayList<>();

    static List<Song> lofibeats = new ArrayList<>();
    static List<Song> popforDays = new ArrayList<>();
    static List<Song> justFireRap = new ArrayList<>();
    static List<Song> ndpSongs = new ArrayList<>();

    static List<Song> lilypichu = new ArrayList<>();
    static List<Song> billieeilish = new ArrayList<>();
    static List<Song> logic301 = new ArrayList<>();
    static List<Song> edSheeran = new ArrayList<>();

    public PlaylistCollection() {
        //creating Playlists

        Playlist likedSongs = new Playlist(
                "P1000",
                "Liked Songs",
                "You",
                likedList,
                R.drawable.liked_song);

        //adding playlists to list
        customPlaylist.add(likedSongs);

        Playlist lofiBeats = new Playlist(
                "P2000",
                "lofi beats",
                "Decibel",
                lofibeats,
                R.drawable.lofibeats_cover);

        Playlist popForDays = new Playlist(
                "P2001",
                "Pop for days",
                "Decibel",
                popforDays,
                R.drawable.pop_art);

        Playlist fireRap = new Playlist(
                "P2002",
                "Just Fire Rap",
                "Decibel",
                justFireRap,
                R.drawable.rap_cover);

        Playlist ndp = new Playlist(
                "P2003",
                "NDP Collection",
                "Decibel",
                ndpSongs,
                R.drawable.ndp_cover);

        //adding playlists to list
        presetPlaylist.add(lofiBeats);
        presetPlaylist.add(popForDays);
        presetPlaylist.add(fireRap);
        presetPlaylist.add(ndp);

        Playlist lilyPichu = new Playlist(
                "P3000",
                "LilyPichu",
                "Decibel",
                lilypichu,
                R.drawable.lilypichu_profile);

        Playlist billieEilish = new Playlist(
                "P3001",
                "Billie Eilish",
                "Decibel",
                billieeilish,
                R.drawable.billieeilish_profile);

        Playlist logic = new Playlist(
                "P3002",
                "Logic",
                "Decibel",
                logic301,
                R.drawable.logic_profile);

        Playlist edsheeran = new Playlist(
                "P3003",
                "Ed Sheeran",
                "Decibel",
                edSheeran,
                R.drawable.edsheeran_profile);

        //adding playlists to list
        artistPlaylist.add(lilyPichu);
        artistPlaylist.add(billieEilish);
        artistPlaylist.add(logic);
        artistPlaylist.add(edsheeran);
    }

    //returns playlist based on its type and index
    public Playlist getCurrentPlaylist(String listType, int currentPlaylistIndex){
        if (listType.equals("preset")){
            return presetPlaylist.get(currentPlaylistIndex);
        }
        else if (listType.equals("custom")){
            return customPlaylist.get(currentPlaylistIndex);
        }
        else if (listType.equals("artist")){
            return artistPlaylist.get(currentPlaylistIndex);
        }
        return null;
    }

    //checks if song is present in likedList
    public boolean isPresent(String id) {
        Song tempSong = songCollection.findSongById(id);
        for (int i = 0; i < likedList.size(); i++){
            if (tempSong.getId().equals((likedList.get(i).getId()))){
                return true;
            }
        }
        return false;
    }

    //adds song to liked list
    public void addToLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        likedList.add(tempSong);
        for (int i = 0; i < likedList.size(); i++){
            Log.d("Liked", "Liked list contains" + (likedList.get(i)).getId());
        }
    }

    //removes song from liked list
    public void removeFrmLikedList(String id){
        Song tempSong = songCollection.findSongById(id);
        for (int i = 0; i < likedList.size(); i++){
            if (tempSong.getId().equals((likedList.get(i).getId()))) {
                likedList.remove(i);
                break;
            }
        }
    }

    //singular method to be run all addTo methods
    public void collatePlaylists(){
        addToLofiBeats();
        addToPop();
        addToRap();
        addToNdp();

        addToLilyPichu();
        addToBillie();
        addToLogic();
        addToEdSheeran();
    }

    //generates lofi playlist
    private void addToLofiBeats(){
        if (lofibeats.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "lofi")) {
                    lofibeats.add(songList.get(i));
                    Log.d("lofiBeats", "lofiBeats contains " + songList.get(i));
                }
            }
        }
    }

    //generates pop playlist
    private void addToPop(){
        if (popforDays.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "pop")) {
                    popforDays.add(songList.get(i));
                    Log.d("pop", "pop contains " + songList.get(i));
                }
            }
        }
    }

    //generates rap playlist
    private void addToRap(){
        if (justFireRap.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "rap")) {
                    justFireRap.add(songList.get(i));
                    Log.d("rap", "rap contains " + songList.get(i));
                }
            }
        }
    }

    //generates ndp playlist
    private void addToNdp(){
        if (ndpSongs.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getGenre() == "ndp")) {
                    ndpSongs.add(songList.get(i));
                    Log.d("rap", "rap contains " + songList.get(i));
                }
            }
        }
    }

    //generates lilypichu playlist
    private void addToLilyPichu(){
        if (lilypichu.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getArtist() == "LilyPichu")) {
                    lilypichu.add(songList.get(i));
                    Log.d("artist", "lily contains " + songList.get(i));
                }
            }
        }
    }

    //generates billie eilish playlist
    private void addToBillie(){
        if (billieeilish.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getArtist() == "Billie Eilish")) {
                    billieeilish.add(songList.get(i));
                    Log.d("artist", "billie eilish contains " + songList.get(i));
                }
            }
        }
    }

    //generates logic playlist
    private void addToLogic(){
        if (logic301.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getArtist() == "Logic")) {
                    logic301.add(songList.get(i));
                    Log.d("artist", "Logic contains " + songList.get(i));
                }
            }
        }
    }

    //generates ed sheeran playlist
    private void addToEdSheeran(){
        if (edSheeran.size()==0) {
            for (int i = 0; i < songList.size(); i++) {
                if ((songList.get(i).getArtist() == "Ed Sheeran")) {
                    edSheeran.add(songList.get(i));
                    Log.d("artist", "Ed Sheeran contains " + songList.get(i));
                }
            }
        }
    }




}
