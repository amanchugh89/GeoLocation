package com.bhati.dao.impl;

import com.bhati.dao.GeoEventDao;
import com.bhati.entity.GeoEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by aman on 22/11/15.
 */
@Component("geoEventMao")
@Scope("singleton")
public class GeoEventMao implements GeoEventDao {

    private Map<Long, GeoEvent> map = new HashMap<>();

    @Override
    public void put(GeoEvent event) {
        long id = event.getuserId();
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
    public HashSet<Object> getAll() {
        HashSet<Object> events = new HashSet<>();
        for(Map.Entry entry : map.entrySet()){
            events.add(map.get(entry.getKey()));
        }
        return events;
    }
}
