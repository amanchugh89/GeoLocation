package com.bhati.controller;

import com.bhati.dao.GeoEventDao;
import com.bhati.dao.UserDetailsDao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by aman on 22/11/15.
 */
@RestController
public class GeoController {

    @Autowired
    private UserDetailsDao userDao;

    @Autowired
    private GeoEventDao eventDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam UserDetails userDetails){
        if(userDetails!=null || isUserDetailsValid(userDetails)){

            userDao.save(userDetails);
        }
    }
    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestParam GeoEvent event){
        if(event!=null ||isGeoEventValid(event)){
            eventDao.put(event);
        }
    }

    @RequestMapping(value = "/getGeoId", method = RequestMethod.GET)
    public void getGeoforID(@PathVariable Long id){
        eventDao.get(id);
    }

    @RequestMapping(value = "/getGeoAll", method = RequestMethod.GET)
    public Set<GeoEvent> getAllGeoLocation(){
        Set<GeoEvent> eventSet = eventDao.getAll();
        return eventSet;
    }


    public boolean isUserDetailsValid(UserDetails userDetails) {
        return true;
    }

    public boolean isGeoEventValid(GeoEvent event) {
        return true;
    }


}