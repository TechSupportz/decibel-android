package com.example.decibel;

public class SongCollection {

    private Song songs[] = new Song[25];

    public SongCollection(){
        Song theWayYouLookTonight = new Song(
                "S1001",
                "The Way You Look Tonight",
                "Michael Bublé",
                "pop",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                "https://cdns-images.dzcdn.net/images/cover/a5dd92ae1b53ad19f39e3036dd510541/500x500-000000-80-0-0.jpg",
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
                "https://p.scdn.co/mp3-preview/9956e838630df9a0dfdb3753ad38ef1fc7cee5e7?cid=2afe87a64b0042dabf51f37318616965",
                "https://cdns-images.dzcdn.net/images/cover/6b2f8b3ff0e6cd136e685afa2940fcba/500x500-000000-80-0-0.jpg",
                4.1 );


        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = dreamyNight;
    }

    public Song getCurrentSong(int currentSongId){
        return songs[currentSongId];
    }

    //Finds song which has the same id and returns its position the the songs array
    public int searchSongById(String id){
        for (int index=0; index < songs.length; index++){
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id)){
                return index;
            }
        }
        return -1;
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
