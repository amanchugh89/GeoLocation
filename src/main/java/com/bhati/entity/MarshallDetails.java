package com.bhati.entity;


import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aman on 23/11/15.
 */
@Entity
@Table(name = "userdetails")
@Access(value = AccessType.FIELD)
public class MarshallDetails implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "marshall")
    private String name;
    @Column(name = "mobile" , unique =true)
    private long mobile;
    @Column(name = "center")
    private String center;
    @Column(name = "badge")
    private String badge;
    @Column(name= "vehicle_number")
    private String vehicleNumber;
    @Column(name= "area")
    private String area;
    @Column(name= "target_start_time")
    @Temporal(TemporalType.TIME)
    private Date targetStartTime;
    @Temporal(TemporalType.TIME)
    @Column(name= "target_reaching_time")
    private Date targetReachingTime;
    @Column(name= "mobile_type")
    @Enumerated(EnumType.STRING)
    private MType mobileType = MType.ANDROID;
    @Column(name= "mobile_company")
    private String company;
    @Column(name= "mobile_model")
    private String model;


    public MarshallDetails(){
    }

    public MarshallDetails(String name, long mobile, String center, String badge, String vehicleNumber){
        this.name=name;
        this.mobile=mobile;
        this.center=center;
        this.badge = badge;
        this.vehicleNumber=vehicleNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
    public String getVehicleNumber() { return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getTargetStartTime() {
        return targetStartTime;
    }

    public void setTargetStartTime(Date targetStartTime) {
        this.targetStartTime = targetStartTime;
    }

    public Date getTargetReachingTime() {
        return targetReachingTime;
    }

    public void setTargetReachingTime(Date targetReachingTime) {
        this.targetReachingTime = targetReachingTime;
    }

    public MType getMobileType() {
        return mobileType;
    }

    public void setMobileType(MType mobileType) {
        this.mobileType = mobileType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id==0;
    }

    @Override
    public String toString() {
        return "MarshallDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile=" + mobile +
                ", center='" + center + '\'' +
                ", badge='" + badge + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", area='" + area + '\'' +
                ", targetStartTime=" + targetStartTime +
                ", targetReachingTime=" + targetReachingTime +
                ", mobileType=" + mobileType +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
