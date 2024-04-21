package com.nc.models;

import lombok.Data;

@Data
public class GalleryModel {
    String image;
    String title;
    String time;
    String  link;

    public GalleryModel(String image, String title, String time,String link) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.link = link;
    }
}
