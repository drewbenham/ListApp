package com.drew_benham.listapp.models;

public class MediaListType {
    private String listName;
    private int imageSrc;

    public MediaListType(String listName, int imageSrc) {
        this.listName = listName;
        this.imageSrc = imageSrc;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
}
