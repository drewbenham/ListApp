package com.drew_benham.listapp.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MusicMedia extends Media {

    private HashMap<String, List<String>> songlist;

    public MusicMedia() {
        songlist = new HashMap<>();
    }

    public MusicMedia(String title, String subTitle, int imageSrc, Date releaseDate, HashMap<String, List<String>> songlist) {
        super(title, subTitle, imageSrc, releaseDate);

        this.songlist = songlist;
    }

    public HashMap<String, List<String>> getSonglist() {
        return songlist;
    }

    public void setSonglist(HashMap<String, List<String>> songlist) {
        this.songlist = songlist;
    }
}
