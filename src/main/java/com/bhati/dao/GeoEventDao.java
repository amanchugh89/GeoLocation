package com.bhati.dao;

import com.bhati.entity.GeoEvent;

import java.util.HashSet;
import java.util.List;


/**
 * Created by aman on 22/11/15.
 */

public interface GeoEventDao {
    public void put(GeoEvent event);
    public void put(List<GeoEvent> events);

    public GeoEvent get(long id);


    public HashSet<GeoEvent> getAll();
}
