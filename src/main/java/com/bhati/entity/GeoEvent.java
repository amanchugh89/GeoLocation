package com.bhati.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aman on 22/11/15.
 */
@Entity
@Table(name = "geo_event")
@Access(value = AccessType.FIELD)
@NamedQueries( {@NamedQuery(name = "GeoEvent.findLatestDistinctEvents",query = " Select G from  GeoEvent G WHERE G.timestamp = (Select Max(e.timestamp) from GeoEvent e where  G.userId=e.userId)")})
public class GeoEvent implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "timestamp")
    private long timestamp = new Date().getTime();

    @Transient
    private UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public GeoEvent(){
    }

    public GeoEvent(long userId,double latitude , double  longitude){
        this.userId = userId;
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

    public long getuserId() {
        return userId;
    }

    public void setuserId(long deviceId) {
         this.userId = deviceId;
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
                "userId=" + userId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
