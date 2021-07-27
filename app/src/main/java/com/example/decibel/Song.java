package com.example.decibel;

public class Song {

    private String id;
    private String title;
    private String artist;
    private String genre;
    private String findLink;
    private String coverArt;


    public Song(String id, String title, String artist, String genre, String findLink, String coverArt){

        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.findLink = findLink;
        this.coverArt = coverArt;
    }

    public void setId(String id){this.id=id;}
    public void setTitle(String title){this.title=title;}
    public void setArtist(String artist){this.artist=artist;}
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setFindLink(String findLink){this.findLink=findLink;}
    public void setCoverImage(String coverImage){this.coverArt=coverImage;}


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
    public String getFindLink() {
        return findLink;
    }
    public String getCoverArt() {
        return coverArt;
    }

}
