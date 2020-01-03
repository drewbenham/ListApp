package com.drew_benham.listapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "media_table")
public class MusicMedia extends Media {

    @ColumnInfo(name = "music_medium")
    private String musicMedium;

    @ColumnInfo(name = "song_list")
    private HashMap<String, List<String>> songlist;

    @Ignore
    public MusicMedia() {
        musicMedium = "Not Set";
        songlist = new HashMap<>();
    }

    public MusicMedia(String title, String subTitle, int imageSrc, Date releaseDate, String musicMedium, HashMap<String, List<String>> songlist) {
        super(title, subTitle, imageSrc, releaseDate);

        this.musicMedium = musicMedium;
        this.songlist = songlist;
    }

    public String getMusicMedium() {
        return musicMedium;
    }

    public void setMusicMedium(String musicMedium) {
        this.musicMedium = musicMedium;
    }

    public HashMap<String, List<String>> getSonglist() {
        return songlist;
    }

    public void setSonglist(HashMap<String, List<String>> songlist) {
        this.songlist = songlist;
    }
}
