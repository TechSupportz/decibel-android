package com.example.decibel;

public class Song {

    //declare variable
    private String id;
    private String title;
    private String artist;
    private String genre;
    private String songLink;
    private String coverArt;


    public Song(String id, String title, String artist, String genre, String songLink, String coverArt){
        //assign variables to respective values
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.songLink = songLink;
        this.coverArt = coverArt;
    }

    //setter methods
    public void setId(String id){this.id=id;}
    public void setTitle(String title){this.title=title;}
    public void setArtist(String artist){this.artist=artist;}
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setFindLink(String songLink){this.songLink = songLink;}
    public void setCoverImage(String coverImage){this.coverArt=coverImage;}

    //getter methods
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public String getGenre() {
        return genre;
    }
    public String getSongLink() {
        return songLink;
    }
    public String getCoverArt() {
        return coverArt;
    }

}
