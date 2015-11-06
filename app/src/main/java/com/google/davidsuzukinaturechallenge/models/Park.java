package com.google.davidsuzukinaturechallenge.models;

/**
 * Created by jenna on 15-10-13.
 */
public class Park {

    private String mName;
    private Double mLat;
    private Double mLng;
    private float mDistance;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLng() {
        return mLng;
    }

    public void setLng(Double lng) {
        mLng = lng;
    }

    public float getDistance() {
        return mDistance;
    }

    public void setDistance(float distance) {
        mDistance = distance;
    }

}
