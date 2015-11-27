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

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
    public UserDetails register(@RequestBody UserDetails userDetails) {
        if (userDetails != null && isUserDetailsValid(userDetails)) {
            return userDao.save(userDetails);
        }
        return null;
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public UserDetails getUser(@PathVariable Long id) {
        return userDao.findOne(id);
    }


    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestBody GeoEvent event) {
        if (event != null || isGeoEventValid(event)) {
            event.setuserId(event.getId());
            event.setId(0);
            UserDetails userDetails = userDao.findOne(event.getuserId());
            event.setUserDetails(userDetails);
            eventDao.put(event);
            if (isEventPersistanceEnabled)
                eventRepository.save(event);
        }
    }

    @RequestMapping(value = "/getGeoForUsers", method = RequestMethod.POST)
    public Set<GeoEvent> getEventsForUsers(@RequestBody Set<Long> userList) {
        Set<GeoEvent> geoEvents = new HashSet<>();
        userList.stream().filter((p) -> userDao.findOne(p) != null ).forEach((p) -> {
            if(eventDao.get(p)!=null)
            geoEvents.add(eventDao.get(p));
            System.out.println(p);});
        return geoEvents;
    }

    @RequestMapping(value = "/getGeoId/{id}", method = RequestMethod.GET)
    public GeoEvent getGeoforID(@PathVariable long id) {
        return eventDao.get(id);
    }

    @RequestMapping(value = "/getGeoAll", method = RequestMethod.GET)
    public Set<Object> getAllGeoLocation() {
        Set<Object> eventSet = eventDao.getAll();
        return eventSet;
    }


    public boolean isUserDetailsValid(UserDetails userDetails) {
        if (Validator.isValidOnlyString(userDetails.getName())) ;
        return true;
    }

    public boolean isGeoEventValid(GeoEvent event) {
        return true;
    }


    @RequestMapping(value = "/trackUser/{id}/{from}/{to}", method = RequestMethod.GET)
    public Set<GeoEvent>trackUser(@PathVariable long id, @PathVariable long from, @PathVariable long to) {
        Set<GeoEvent> geoEvents = new TreeSet<>((p1,p2)->p1.getId().intValue()-p2.getId().intValue());
        if (userDao.findOne(id) != null)
        eventRepository.findByuserId(id).
                forEach((p) -> {
                    p.setUserDetails(userDao.findOne(p.getuserId()));
                    if(p.getUserDetails()!= null) {
                       if(p.getTimestamp()>= from && p.getTimestamp() <= to){
                           geoEvents.add(p);
                       }
                    }
                });
        return geoEvents;
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public Set<UserDetails> getAllUsers() {
        Set<UserDetails> l = new HashSet<>();
        userDao.findAll().forEach((p) -> l.add(p));
        return l;
    }

    @PostConstruct
    void initMap() {
        eventRepository.findLatestDistinctEvents().forEach((p) -> {
            p.setUserDetails(userDao.findOne(p.getuserId()));
            eventDao.put(p);
        });

    }
}