package com.example.decibel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongCollection {

    List<Song> songList = new ArrayList<Song>();

    public SongCollection(){
        Song ifICantHaveYou = new Song(
                "S1001",
                "If I Can't Have You",
                "Shawn Mendes",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976438457729024/y2mate.com_-_Shawn_Mendes_If_I_Cant_Have_You_Lyrics.mp3",
                "https://cdns-images.dzcdn.net/images/cover/4798f68c0c5ea41110ac0fbdc9a8989f/500x500-000000-80-0-0.jpg",
                4.66);


        Song billieJean = new Song(
                "S1002",
                "Billie Jean",
                "Michael Jackson",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976494819475496/y2mate.com_-_Michael_Jackson_Billie_Jean_Lyrics_1983.mp3",
                "https://cdns-images.dzcdn.net/images/cover/ebeac32e9207c60877228ddc5bb37233/500x500-000000-80-0-0.jpg",
                4.9);


        Song dreamyNight = new Song(
                "S1003",
                "dreamy night",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976502290055178/Lilypichu_comfy_beats_-_dreamy_night.mp3",
                "https://cdns-images.dzcdn.net/images/cover/6b2f8b3ff0e6cd136e685afa2940fcba/500x500-000000-80-0-0.jpg",
                4.1 );

        Song lostCause = new Song(
                "S1003",
                "Lost Cause",
                "Billie Eilish",
                "trip hop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976498145558548/y2mate.com_-_Billie_Eilish_Lost_Cause_Lyrics.mp3",
                "https://cdns-images.dzcdn.net/images/cover/bb2880548dd3bc71fb97def2eedec130/500x500-000000-80-0-0.jpg",
                3.54 );

        Song hitMyLine = new Song(
                "S1004",
                "Hit My Line",
                "Logic",
                "hip-hop/rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/862196004735680522/Logic_-_Hit_My_Line_Official_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b273dd6e1338493de367e646a01a",
                4.42);

        Song sunshineAndButterflies = new Song(
                "S1005",
                "sunshine & butterflies",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/862205698850816040/sunshine__butterflies_.mp3",
                "https://i.scdn.co/image/ab67616d0000b273992f5072a606a81d1dfcb483",
                3.08);



        songList.add(ifICantHaveYou);
        songList.add(billieJean);
        songList.add(dreamyNight);
        songList.add(lostCause);
        songList.add(hitMyLine);
        songList.add(sunshineAndButterflies);


    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getCurrentSong(int currentSongId){
        return songList.get(currentSongId);
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
