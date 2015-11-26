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

    private Map<String,GeoEvent> map = new HashMap<>();


    @Override
    public void put(GeoEvent event) {
        String id = event.getdeviceId();
        map.put(id , event);

    }

    @Override
    public void put(List<GeoEvent> events) {
        for (GeoEvent event :events){
            put(event);
        }
    }

    @Override
    public GeoEvent get(String deviceid) {
        return map.get(deviceid);
    }

    @Override
    public HashSet<GeoEvent> getAll() {
        HashSet<GeoEvent> events = new HashSet<>();
        for(Map.Entry entry : map.entrySet()){
            events.add(map.get(entry.getKey()));
        }
        return events;
    }
}
