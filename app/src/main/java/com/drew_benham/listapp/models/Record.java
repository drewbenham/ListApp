package com.drew_benham.listapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Record extends Media {

    private List<String> songlist;

    public Record() {
        songlist = new ArrayList<>();
    }

    public Record(String title, String subTitle, int imageSrc, Date releaseDate, List<String> songlist) {
        super(title, subTitle, imageSrc, releaseDate);

        this.songlist = songlist;
    }

    public List<String> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<String> songlist) {
        this.songlist = songlist;
    }
}
