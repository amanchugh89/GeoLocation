package com.bhati.controller;

import com.bhati.dao.GeoEventDao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import com.bhati.repository.GeoEventRepository;
import com.bhati.repository.UserDetailsRepository;
import com.bhati.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
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

    @Value("${event.persistance}")
    boolean isEventPersistanceEnabled;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
            event.setuserId(event.getId());
            event.setId(0);
            UserDetails userDetails =userDao.findOne(event.getuserId());
            event.setUserDetails(userDetails);
            eventDao.put(event);
            if(isEventPersistanceEnabled)
                eventRepository.save(event);
        }
    }

    @RequestMapping(value = "/getGeoId", method = RequestMethod.GET)
    public void getGeoforID(@PathVariable long id){
        eventDao.get(id);
    }

    @RequestMapping(value = "/getGeoAll", method = RequestMethod.GET)
    public Set<Object> getAllGeoLocation(){
        Set<Object> eventSet = eventDao.getAll();
        return eventSet;
    }


    public boolean isUserDetailsValid(UserDetails userDetails) {
        if(Validator.isValidOnlyString(userDetails.getName()));
        return true;
    }

    public boolean isGeoEventValid(GeoEvent event)
    {
        return true;
    }


    @RequestMapping("/trackUser/{id}")
    public Set<GeoEvent> trackUser(@PathVariable long id){
        Set<GeoEvent> geoEvents = new HashSet<>();
        if(userDao.findOne(id)!=null);
        eventRepository.findByuserId(id).
                forEach((p)-> {p.setUserDetails(userDao.findOne(p.getuserId()));
                geoEvents.add(p);});
return geoEvents;
    }

    @RequestMapping("/getAllUsers")
    public Set<UserDetails> getAllUsers(){
        Set<UserDetails> l = new HashSet<>();
        userDao.findAll().forEach((p)-> l.add(p));
        return l;
    }

    @PostConstruct
void initMap(){
        eventRepository.findLatestDistinctEvents().forEach((p) -> {
            p.setUserDetails(userDao.findOne(p.getuserId()));
            eventDao.put(p);});

}
}