package com.example.decibel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

public class SongCollection {

    List<Song> songList = new ArrayList<>();

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
                "hiphop",
                "https://cdn.discordapp.com/attachments/854735014770901055/854976498145558548/y2mate.com_-_Billie_Eilish_Lost_Cause_Lyrics.mp3",
                "https://cdns-images.dzcdn.net/images/cover/bb2880548dd3bc71fb97def2eedec130/500x500-000000-80-0-0.jpg");

        Song hitMyLine = new Song(
                "S1004",
                "Hit My Line",
                "Logic",
                "rap",
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

        Song levitating = new Song(
                "S1008",
                "Levitating (feat. DaBaby)",
                "Dua Lipa",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618965089812511/yt1s.com_-_Levitating_feat_DaBaby.mp3",
                "https://i.scdn.co/image/ab67616d0000b27310052a21c724d68cbd79b5d9");

        Song theProcess = new Song(
                "S1009",
                "The Process",
                "LAKEY INSPIRED",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618966103916604/yt1s.com_-_LAKEY_INSPIRED_The_Process.mp3",
                "https://i.scdn.co/image/ab67616d0000b273bf94f2060589532badcb3075");

        Song neverGonnaGiveYouUp = new Song(
                "S1010",
                "Mystery Song?",
                "Decibel",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618966376939530/yt1s.com_-_Rick_Astley_Never_Gonna_Give_You_Up_Official_Music_Video.mp3",
                "https://cdn.discordapp.com/attachments/854735014770901055/871302414928580608/206-2063136_super-mario-coin-block-hd-png-download.png");

        Song lights = new Song(
                "S1011",
                "Lights",
                "elijah woods",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618967059955782/yt1s.com_-_elijah_woods_lights_lyric_video.mp3",
                "https://i.scdn.co/image/ab67616d0000b273de197f534b230a0ad9adff42");

        Song lovely = new Song(
                "S1012",
                "lovely",
                "Billie Eilish",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618967723049000/yt1s.com_-_Billie_Eilish_lovely_Lyrics_ft_Khalid.mp3",
                "https://i.scdn.co/image/ab67616d0000b2738a3f0a3ca7929dea23cd274c");

        Song loveSomebody = new Song(
                "S1013",
                "Love Somebody",
                "Lauv",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618969786384424/yt1s.com_-_Lauv_Love_Somebody_Lyrics.mp3",
                "https://i.scdn.co/image/ab67616d0000b2735fb9e3db7385a470bdf559a2");

        Song noTimeToDie = new Song(
                "S1014",
                "No Time To Die",
                "Billie Eilish",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618975693275136/yt1s.com_-_Billie_Eilish_No_Time_To_Die_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b273f7b7174bef6f3fbfda3a0bb7");

        Song walkingWithYou = new Song(
                "S1015",
                "walking with you",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618976406569041/yt1s.com_-_walking_with_you_.mp3",
                "https://i.scdn.co/image/ab67616d0000b27333673961fb492c0e0b042030");

        Song dreamyNightmare = new Song(
                "S1016",
                "dreamy nightmare",
                "LilyPichu",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867618998811623455/yt1s.com_-_dreamy_nightmares_.mp3",
                "https://i.scdn.co/image/ab67616d0000b273cbd1eaf607ada181c93ee8ac");

        Song lakeview = new Song(
                "S1017",
                "Lakeview",
                "Oaty",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619000534827018/yt1s.com_-_Lakeview.mp3",
                "https://i.scdn.co/image/ab67616d0000b2730d5ece4c8b55da3cd732a2da");

        Song wakingUp = new Song(
                "S1018",
                "Waking Up",
                "Kyoto Dreams",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619002675494962/yt1s.com_-_Waking_Up.mp3",
                "https://i.scdn.co/image/ab67616d0000b273475f3b62832a215b6c0a71c9");

        Song hope = new Song(
                "S1019",
                "Hope",
                "yuhei minura",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619003500331018/yt1s.com_-_Hope.mp3",
                "https://i.scdn.co/image/ab67616d0000b273b44dc5b239db9b919736ba2c");

        Song roots = new Song(
                "S1019",
                "Roots",
                "Kupla",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619004554018826/yt1s.com_-_Roots_Original_Mix.mp3",
                "https://i.scdn.co/image/ab67616d0000b273efa526f46b6068fea16d4976");

        Song betterDays = new Song(
                "S1020",
                "Better Days",
                "Lusca061",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619015383187456/yt1s.com_-_Better_Days.mp3",
                "https://i.scdn.co/image/ab67616d0000b27392fa2547974a86e9d559c09e");

        Song tigerPark = new Song(
                "S1021",
                "Tiger Park",
                "LoFi Boy",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619019463589908/yt1s.com_-_LoFi_Boy_Tiger_Park_w_BVG.mp3",
                "https://i.scdn.co/image/ab67616d0000b2734efebbd34e03b599376165b3");

        Song jasmine = new Song(
                "S1022",
                "Jasmine",
                "C4C",
                "lofi",
                "https://cdn.discordapp.com/attachments/854735014770901055/867619023352758302/yt1s.com_-_C4C_TLion_Jasmine_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b2732173d156062395f44e048aef");



        songList.add(ifICantHaveYou);
        songList.add(billieJean);
        songList.add(dreamyNight);
        songList.add(lostCause);
        songList.add(hitMyLine);
        songList.add(sunshineAndButterflies);
        songList.add(sunflower);
        songList.add(pompom);
        songList.add(levitating);
        songList.add(theProcess);
        songList.add(neverGonnaGiveYouUp);
        songList.add(lights);
        songList.add(lovely);
        songList.add(loveSomebody);
        songList.add(noTimeToDie);
        songList.add(walkingWithYou);
        songList.add(dreamyNightmare);
        songList.add(lakeview);
        songList.add(wakingUp);
        songList.add(hope);
        songList.add(roots);
        songList.add(betterDays);
        songList.add(tigerPark);
        songList.add(jasmine);

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
