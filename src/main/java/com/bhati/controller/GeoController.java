package com.bhati.controller;

import com.bhati.dao.GeoEventDao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import com.bhati.repository.GeoEventRepository;
import com.bhati.repository.UserDetailsRepository;
import com.bhati.util.Constants;
import com.bhati.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import javax.ws.rs.Consumes;

/**
 * Created by aman on 22/11/15.
 */
@RestController
public class GeoController {

    @Autowired
    private UserDetailsRepository userDao;

    @Autowired
    private GeoEventDao eventDao;

    @Autowired
    private GeoEventRepository eventRepository;

    @Value(Constants.EVENT_PERSISTANCE_ENABLED)
    boolean isEventPersistanceEnabled;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
   // @Consumes("application/json")
    @Consumes("application/json")
    public UserDetails register(@RequestBody UserDetails userDetails){
        if(userDetails!=null && isUserDetailsValid(userDetails)){
           return userDao.save(userDetails);
        }
        return null;
    }


    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
//    @Consumes("application/json")
    public void addEvent(@RequestBody GeoEvent event){
        if(event!=null ||isGeoEventValid(event)){
            eventDao.put(event);
            if(isEventPersistanceEnabled) {
                eventRepository.save(event);
            }
        }
    }

    @RequestMapping(value = "/getGeoId", method = RequestMethod.GET)
    public void getGeoforID(@PathVariable String deviceId){
        eventDao.get(deviceId);
    }

    @RequestMapping(value = "/getGeoAll", method = RequestMethod.GET)
    public Set<GeoEvent> getAllGeoLocation(){
        Set<GeoEvent> eventSet = eventDao.getAll();
        return eventSet;
    }


    public boolean isUserDetailsValid(UserDetails userDetails) {
        if(Validator.isValidOnlyString(userDetails.getName()));
        return true;
    }

    public boolean isGeoEventValid(GeoEvent event) {
        return true;
    }


}