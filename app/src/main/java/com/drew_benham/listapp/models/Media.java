package com.drew_benham.listapp.models;

import java.util.Date;
import java.util.UUID;

public class Media {

    private UUID uuid;
    private String name;
    private int imageSrc;
    private Date releaseDate;

    public Media() {
        uuid = UUID.randomUUID();
        name = "Not Set";
        imageSrc = 0;
        releaseDate = null;
    }

    public Media(String name, int imageSrc, Date releaseDate) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.imageSrc = imageSrc;
        this.releaseDate = releaseDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
