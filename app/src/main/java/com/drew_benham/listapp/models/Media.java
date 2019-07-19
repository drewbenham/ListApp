package com.drew_benham.listapp.models;

import java.util.Date;
import java.util.UUID;

public class Media {

    private UUID uuid;
    private String title;
    private String subTitle;
    private int imageSrc;
    private Date releaseDate;

    public Media() {
        uuid = UUID.randomUUID();
        title = "Not Set";
        subTitle = "Not Set";
        imageSrc = 0;
        releaseDate = null;
    }

    public Media(String title, String subtitle, int imageSrc, Date releaseDate) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.subTitle = subtitle;
        this.imageSrc = imageSrc;
        this.releaseDate = releaseDate;
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
}
