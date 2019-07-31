package com.drew_benham.listapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Media implements Serializable {
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_LETTER = 2;

    private UUID uuid;
    private String title;
    private String subTitle;
    private int imageSrc;
    private Date releaseDate;

    // TODO: 7/22/19 This is for sorting the list alphabetically.
    private int type;

    public Media() {
        uuid = UUID.randomUUID();
        title = "Not Set";
        subTitle = "Not Set";
        imageSrc = 0;
        releaseDate = null;
        type = TYPE_MEDIA;
    }

    public Media(String letterTitle) {
        uuid = UUID.randomUUID();
        title = letterTitle;
        subTitle = "";
        imageSrc = 0;
        releaseDate = null;
        type = TYPE_LETTER;
    }


    public Media(String title, String subtitle, int imageSrc, Date releaseDate) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.subTitle = subtitle;
        this.imageSrc = imageSrc;
        this.releaseDate = releaseDate;
        type = TYPE_MEDIA;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
