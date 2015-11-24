package com.bhati.entity;


import org.springframework.data.domain.Persistable;
import javax.persistence.*;

/**
 * Created by aman on 23/11/15.
 */
@Entity
@Table(name = "userdetails")
@Access(value = AccessType.FIELD)
public class UserDetails implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "mobile" , unique =true)
    private long mobile;
    @Column(name = "center")
    private String center;
    @Column(name = "batch")
    private String batch;


    public UserDetails(){
    }

    public UserDetails(String name, long mobile, String center , String batch){
        this.name=name;
        this.mobile=mobile;
        this.center=center;
        this.batch=batch;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "name='" + name + '\'' +
                ", mobile=" + mobile +
                ", center='" + center + '\'' +
                ", batch='" + batch + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id==0;
    }
}
