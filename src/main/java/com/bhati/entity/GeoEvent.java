package com.bhati.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aman on 22/11/15.
 */
@Entity
@Table(name = "userdetails")
@Access(value = AccessType.FIELD)
public class GeoEvent implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "timestamp")
    private long timestamp = new Date().getTime();

    public GeoEvent(){
    }

    public GeoEvent(String deviceId,double latitude , double  longitude){
        this.deviceId=deviceId;
        this.latitude=latitude;
        this.longitude=longitude;
        this.timestamp = new Date().getTime();
    }


    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id==0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getdeviceId() {
        return deviceId;
    }

    public void setdeviceId(String deviceId) {
         this.deviceId = deviceId;
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
                "deviceId=" + deviceId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
