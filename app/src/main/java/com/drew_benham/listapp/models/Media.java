package com.drew_benham.listapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "media_table")
public class Media implements Serializable {
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_LETTER = 2;

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "sub_title")
    private String subTitle;
    @ColumnInfo(name = "image_src")
    private int imageSrc;
    @ColumnInfo(name = "release_date")
    private Date releaseDate;

    // TODO: 7/22/19 This is for sorting the list alphabetically.
    private int type;

    public Media() {
        title = "Not Set";
        subTitle = "Not Set";
        imageSrc = 0;
        releaseDate = null;
        type = TYPE_MEDIA;
    }

    public Media(String letterTitle) {
        title = letterTitle;
        subTitle = "";
        imageSrc = 0;
        releaseDate = null;
        type = TYPE_LETTER;
    }


    public Media(String title, String subtitle, int imageSrc, Date releaseDate) {
        this.title = title;
        this.subTitle = subtitle;
        this.imageSrc = imageSrc;
        this.releaseDate = releaseDate;
        type = TYPE_MEDIA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
