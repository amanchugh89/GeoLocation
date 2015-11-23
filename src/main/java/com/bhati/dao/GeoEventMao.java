package com.bhati.dao;

import com.bhati.entity.GeoEvent;

import java.util.*;

/**
 * Created by aman on 22/11/15.
 */
public class GeoEventMao implements GeoEventDao {

    private Map<Long,GeoEvent> map = new HashMap<>();
    @Override
    public void put(GeoEvent event) {
        long id = event.getId();
        map.put(id , event);

    }

    @Override
    public void put(List<GeoEvent> events) {
        for (GeoEvent event :events){
            put(event);
        }
    }

    @Override
    public GeoEvent get(long id) {
        return map.get(id);
    }

    @Override
    public Set<GeoEvent> getAll() {
        Set<GeoEvent> events = new HashSet<>();
        for(Map.Entry entry : map.entrySet()){
            events.add(map.get(entry.getKey()));
        }
        return events;
    }
}
