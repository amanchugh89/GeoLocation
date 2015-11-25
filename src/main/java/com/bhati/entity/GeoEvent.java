package com.bhati.entity;

import java.util.Date;

/**
 * Created by aman on 22/11/15.
 */
public class GeoEvent {

    private long id;
    private double latitude;
    private double longitude;
    private long timestamp;

    public GeoEvent(){
    }

    public GeoEvent(long id,double latitude , double  longitude){
        this.id=id;
        this.latitude=latitude;
        this.longitude=longitude;
        this.timestamp = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getTimestamp() {
        return timestamp;
    }

    /*public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }*/

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "GeoEvent{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
