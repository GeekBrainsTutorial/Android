package ru.geekbrains.cityheraldry;

import java.io.Serializable;

public class Parcel implements Serializable {
    private int imageIndex;
    private String cityName;

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCityName() {
        return cityName;
    }

    public Parcel(int imageIndex, String cityName) {
        this.imageIndex = imageIndex;
        this.cityName = cityName;
    }
}