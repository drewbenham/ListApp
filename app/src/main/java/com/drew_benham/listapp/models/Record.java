package com.drew_benham.listapp.models;

import android.util.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Record extends Media {

    private HashMap<String, List<Pair<String, String>>> songlist;

    public Record() {
        songlist = new HashMap<String, List<Pair<String, String>>>();
    }

    public Record(String title, String subTitle, int imageSrc, Date releaseDate, HashMap<String, List<Pair<String, String>>> songlist) {
        super(title, subTitle, imageSrc, releaseDate);

        this.songlist = songlist;
    }

    public HashMap<String, List<Pair<String, String>>> getSonglist() {
        return songlist;
    }

    public void setSonglist(HashMap<String, List<Pair<String, String>>> songlist) {
        this.songlist = songlist;
    }
}
