package com.example.decibel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

public class SongCollection {

    //creating lists
    ArrayList<Integer> indexList = new ArrayList<>();
    List<Song> songList = new ArrayList<>();
    List<Song> songListCopy = new ArrayList<>();
    List<Song> forYou = new ArrayList<>();

    public SongCollection() {
        //creating Songs

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

        Song rapGod = new Song(
                "S1023",
                "Rap God",
                "Eminem",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873592925467074630/y2mate.com_-_Eminem_Rap_God_Lyrics_HD.mp3",
                "https://i.scdn.co/image/ab67616d0000b273cb66bcc14c6f857c127d5969");

        Song vaccine = new Song(
                "S1024",
                "Vaccine",
                "Logic",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873595105829863424/Logic_-_Vaccine.mp3",
               "https://i.scdn.co/image/ab67616d0000b273a30d0f11d12398aaf0afdd61");

        Song heardEmSay = new Song(
                "S1025",
                "Heard 'Em Say",
                "Logic",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873595928894930994/Logic_-_Heard_Em_Say_Official_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b2731c76e29153f29cc1e1b2b434");

        Song astronautInTheOcean = new Song(
                "S1026",
                "Astronaut In The Ocean",
                "Masked Wolf",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873596608695140403/Masked_Wolf_-_Astronaut_in_the_Ocean.mp3",
                "https://i.scdn.co/image/ab67616d0000b27384350b406522fc53c1b2a621");

        Song backwards = new Song(
                "S1027",
                "Backwards",
                "Alexander Stewart",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/873597855426818068/Alexander_Stewart_-_Backwards_LyricsLyric_Video.mp3",
                "https://i.scdn.co/image/ab67616d0000b27339b75e94a1f716908a4dd5a2");

        Song rememberThatName = new Song(
                "S1028",
                "Remember The Name (feat. Eminem & 50 Cent)",
                "Ed Sheeran",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873599287433527336/Ed_Sheeran_-_Remember_The_Name_feat._Eminem__50_Cent_Official_Lyric_Video.mp3",
                "https://i.scdn.co/image/ab67616d0000b27373304ce0653c7758dd94b259");

        Song congratulations = new Song(
                "S1029",
                "Congratulations",
                "Post Malone",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873601136593080351/Post_Malone_-_congratulations_Lyrics_ft._Quavo.mp3",
                "https://i.scdn.co/image/ab67616d0000b273894a761abf954838a84c8db9");



        Song takeMeBackToLondon = new Song(
                "S1030",
                "Take Me Back to London (feat. Stormzy)",
                "Ed Sheeran",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873600221215592468/Ed_Sheeran_-_Take_Me_Back_To_London_feat._Stormzy_Official_Lyric_Video.mp3",
                "https://i.scdn.co/image/ab67616d0000b27373304ce0653c7758dd94b259");

        Song theRoadAhead = new Song(
                "S1031",
                "The Road Ahead",
                "NDPeeps",
                "ndp",
                "https://cdn.discordapp.com/attachments/854735014770901055/873605215222374430/NDP_2021_Theme_Song_-_The_Road_Ahead_Official_Music_Video.mp3",
                "https://i.scdn.co/image/ab67616d0000b273d9afc3ca5f3f926140e892cf");

        Song home = new Song(
                "S1032",
                "Home",
                "Kit Chan",
                "ndp",
                "https://cdn.discordapp.com/attachments/854735014770901055/873606684038287360/Singapore_National_Day_Video_-_Home_-_Kit_Chan.mp3",
                "https://i.scdn.co/image/ab67616d0000b273a3441fe0b8a408c254411e80");

        Song inAHeartbeat = new Song(
                "S1033",
                "In A Heartbeat",
                "Kit Chan",
                "ndp",
                "https://cdn.discordapp.com/attachments/854735014770901055/873607194552197150/NDP_2011_Theme_Song_-_In_A_Heartbeat.mp3",
                "https://i.scdn.co/image/ab67616d0000b273a3441fe0b8a408c254411e80");

        Song keanuReeves = new Song(
                "S1034",
                "Keanu Reeves",
                "Logic",
                "rap",
                "https://cdn.discordapp.com/attachments/854735014770901055/873610073203671040/Logic_-_Keanu_Reeves_Official_Audio.mp3",
                "https://i.scdn.co/image/ab67616d0000b2738c04b41739d88c1c27c4a669");

        Song shapeOfYou = new Song(
                "S1035",
                "Shape Of You",
                "Ed Sheeran",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/873611136078061638/y2mate.com_-_Ed_Sheeran_Shape_Of_You_Official_Lyric_Video.mp3",
                "https://i.scdn.co/image/ab67616d0000b273ba5db46f4b838ef6027e6f96");

        Song photograph = new Song(
                "S1036",
                "Photograph",
                "Ed Sheeran",
                "pop",
                "https://cdn.discordapp.com/attachments/854735014770901055/873613259863896144/Photograph.mp3",
                "https://i.scdn.co/image/ab67616d0000b27313b3e37318a0c247b550bccd");

        //adding songs to songList
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
        songList.add(rapGod);
        songList.add(vaccine);
        songList.add(heardEmSay);
        songList.add(astronautInTheOcean);
        songList.add(backwards);
        songList.add(rememberThatName);
        songList.add(congratulations);
        songList.add(takeMeBackToLondon);
        songList.add(theRoadAhead);
        songList.add(home);
        songList.add(inAHeartbeat);
        songList.add(keanuReeves);
        songList.add(shapeOfYou);
        songList.add(photograph);

        //creates indexList and copy of songList
        for (int index = 0; index < songList.size(); index++) {
            indexList.add(index);
            songListCopy.add(songList.get(index));
        }

        //generates ForYouList
        generateForYou();

    }

    //searches for the song with the id given and returns the song itself
    public Song findSongById(String id){
        for (int i = 0; i < songList.size(); i++){
            Song tempSong = songList.get(i);
            if (tempSong.getId().equals(id)){
                return tempSong;
            }
        }
        return null;
    }

    //searches for the song with the id given and returns the song's index
    public int searchSongById(String id) {
        for (int index = 0; index < songList.size(); index++) {
            Song tempSong = songList.get(index);
            if (tempSong.getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    //generates forYou songList
    private void generateForYou(){
        Collections.shuffle(songListCopy);
        for (int i = 0; i < 5; i++){
            forYou.add(songListCopy.get(i));
        }
    }

    //getter methods
    public List<Song> getSongList() {
        return songList;
    }
    public Song getCurrentSong(int currentSongIndex){
        return songList.get(currentSongIndex);
    }
    public ArrayList<Integer> getIndexList(){
        return indexList;
    }
    public List<Song> getForYouList() {
        return forYou;
    }
}
