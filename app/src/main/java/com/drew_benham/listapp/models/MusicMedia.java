package com.drew_benham.listapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "media_table")
public class MusicMedia extends Media {

    @ColumnInfo(name = "song_list")
    private HashMap<String, List<String>> songlist;

    @Ignore
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
