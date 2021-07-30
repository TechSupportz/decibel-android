package com.example.decibel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongCollection {

    List<Song> songList = new ArrayList<Song>();

    public SongCollection() {
        Song ifICantHaveYou = new Song(
                "S1000",
                "If I Can't Have You",
                "Shawn Mendes",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976438457729024/y2mate.com_-_Shawn_Mendes_If_I_Cant_Have_You_Lyrics.mp3",
                "https://cdns-images.dzcdn.net/images/cover/4798f68c0c5ea41110ac0fbdc9a8989f/500x500-000000-80-0-0.jpg");


        Song billieJean = new Song(
                "S1001",
                "Billie Jean",
                "Michael Jackson",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976494819475496/y2mate.com_-_Michael_Jackson_Billie_Jean_Lyrics_1983.mp3",
                "https://cdns-images.dzcdn.net/images/cover/ebeac32e9207c60877228ddc5bb37233/500x500-000000-80-0-0.jpg");


        Song dreamyNight = new Song(
                "S1002",
                "dreamy night",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976502290055178/Lilypichu_comfy_beats_-_dreamy_night.mp3",
                "https://cdns-images.dzcdn.net/images/cover/6b2f8b3ff0e6cd136e685afa2940fcba/500x500-000000-80-0-0.jpg");

        Song lostCause = new Song(
                "S1003",
                "Lost Cause",
                "Billie Eilish",
                "trip hop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976498145558548/y2mate.com_-_Billie_Eilish_Lost_Cause_Lyrics.mp3",
                "https://cdns-images.dzcdn.net/images/cover/bb2880548dd3bc71fb97def2eedec130/500x500-000000-80-0-0.jpg");

        Song hitMyLine = new Song(
                "S1004",
                "Hit My Line",
                "Logic",
                "hip-hop/rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/862196004735680522/Logic_-_Hit_My_Line_Official_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b273dd6e1338493de367e646a01a");

        Song sunshineAndButterflies = new Song(
                "S1005",
                "sunshine & butterflies",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/862205698850816040/sunshine__butterflies_.mp3",
                "https://i.scdn.co/image/ab67616d0000b273992f5072a606a81d1dfcb483");

        Song sunflower = new Song(
                "S1006",
                "sunflower",
                "owlh",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618954835001344/yt1s.com_-_sunflower.mp3",
                "https://i.scdn.co/image/ab67616d0000b2735009fad492910417b66ecad3");

        Song pompom = new Song(
                "S1007",
                "POMPOM",
                "LilyPichu",
                "Other",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618958910685224/yt1s.com_-_POMPOM_.mp3",
                "https://i.scdn.co/image/ab67616d0000b2735e14687edda579c549a36a3a");


        songList.add(ifICantHaveYou);
        songList.add(billieJean);
        songList.add(dreamyNight);
        songList.add(lostCause);
        songList.add(hitMyLine);
        songList.add(sunshineAndButterflies);
        songList.add(sunflower);
        songList.add(pompom);
    }

    public Song findSongById(String id){
        for (int i = 0; i < songList.size(); i++){
            Song tempSong = songList.get(i);
            if (tempSong.getId().equals(id)){
                return tempSong;
            }
        }
        return null;
    }

    public int searchSongById(String id) {
        for (int index = 0; index < songList.size(); index++) {
            Song tempSong = songList.get(index);
            if (tempSong.getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getCurrentSong(int currentSongIndex){
        return songList.get(currentSongIndex);
    }

    public int getNextSong(int currentSongIndex) {

        if (currentSongIndex >= songList.size()-1){
            return 0;
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
