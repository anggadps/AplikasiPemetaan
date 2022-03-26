package com.anggadps.gisreklame.models;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("nama_tempat")
    private String imageLocationName;
    @SerializedName("lat")
    private String latutide;
    @SerializedName("lng")
    private String longitude;


    public LocationModel(String imageLocationName, String latutide, String longitude) {
        this.imageLocationName = imageLocationName;
        this.latutide = latutide;
        this.longitude = longitude;
}

    public LocationModel() {
    }


    public String getImageLocationName() {
        return imageLocationName;
    }

    public void setImageLocationName(String imageLocationName) {
        this.imageLocationName = imageLocationName;
    }

    public String getLatutide() {
        return latutide;
    }

    public void setLatutide(String latutide) {
        this.latutide = latutide;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}