package com.example.decibel;

public class Song {

    private String id;
    private String title;
    private String artist;
    private String findLink;
    private String coverImage;
    private double songLength;


    public Song(String id, String title, String artist, String genre, String findLink, String coverImage, double songLength){

        this.id = id;
        this.title = title;
        this.artist = artist;
        this.findLink = findLink;
        this.songLength = songLength;
        this.coverImage = coverImage;
    }
    public void setId(String id){this.id=id;}
    public void setTitle(String title){this.title=title;}
    public void setArtist(String artist){this.artist=artist;}
    public void setFindLink(String findLink){this.findLink=findLink;}
    public void setSongLength(double songLength){this.songLength=songLength;}
    public void setCoverImage(String coverImage){this.coverImage=coverImage;}


    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getFindLink(){return findLink;}
    public double getSongLength(){return songLength;}
    public String getCoverImage(){return coverImage;}
}
