package com.bhati.dao;

import com.bhati.entity.GeoEvent;

import java.util.List;
import java.util.Set;

/**
 * Created by aman on 22/11/15.
 */
public interface GeoEventDao {
    public void put(GeoEvent event);
    public void put(List<GeoEvent> events);
    public GeoEvent get(long id);
    public Set<GeoEvent> getAll();
}
