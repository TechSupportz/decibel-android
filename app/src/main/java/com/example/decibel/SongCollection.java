package com.example.decibel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongCollection {

    List<Song> songList = new ArrayList<Song>();

    private Song songs[] = new Song[4];

    public SongCollection(){
        Song theWayYouLookTonight = new Song(
                "S1001",
                "If I Can't Have You",
                "Shawn Mendes",
                "pop",
                "https://cdns-preview-3.dzcdn.net/stream/c-3394022de0f5e5604d469ad80c860636-8.mp3",
                "https://cdns-images.dzcdn.net/images/cover/4798f68c0c5ea41110ac0fbdc9a8989f/500x500-000000-80-0-0.jpg",
                4.66);


        Song billieJean = new Song(
                "S1002",
                "Billie Jean",
                "Michael Jackson",
                "pop",
                "https://p.scdn.co/mp3-preview/14a1ddedf05a15ad0ac11ce28b40ea1a15fabd20?cid=2afe87a64b0042dabf51f37318616965",
                "https://cdns-images.dzcdn.net/images/cover/ebeac32e9207c60877228ddc5bb37233/500x500-000000-80-0-0.jpg",
                4.9);


        Song dreamyNight = new Song(
                "S1003",
                "dreamy night",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/854735119931932702/Lilypichu_comfy_beats_-_dreamy_night.mp3",
                "https://cdns-images.dzcdn.net/images/cover/6b2f8b3ff0e6cd136e685afa2940fcba/500x500-000000-80-0-0.jpg",
                4.1 );

        Song lostCause = new Song(
                "S1003",
                "Lost Cause",
                "Billie Eilish",
                "trip hop",
                "https://cdns-preview-c.dzcdn.net/stream/c-ca6ca9fed6bff13203400750db2de54a-4.mp3",
                "https://cdns-images.dzcdn.net/images/cover/bb2880548dd3bc71fb97def2eedec130/500x500-000000-80-0-0.jpg",
                3.54 );


        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = dreamyNight;
        songs[3] = lostCause;

        songList.addAll(Arrays.asList(songs));

    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getCurrentSong(int currentSongId){
        return songs[currentSongId];
    }


    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >= songs.length-1){
            return currentSongIndex;
        }
        else{
            return currentSongIndex + 1;
        }
    }

    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0){
            return currentSongIndex;
        }
        else{
            return currentSongIndex - 1;
        }
    }
}
