package com.bhati.controller.rest;

import com.bhati.dao.GeoEventDao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.MarshallDetails;
import com.bhati.repository.GeoEventRepository;
import com.bhati.repository.UserDetailsRepository;
import com.bhati.security.types.AreaList;
import com.bhati.security.types.CentreList;
import com.bhati.security.types.GetAreasResult;
import com.bhati.security.types.UserDetails;
import com.bhati.util.Utilities;
import com.bhati.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Created by aman on 22/11/15.
 */
@RestController
@RequestMapping("/rest")
public class GeoController {

    @Autowired
    private UserDetailsRepository userDao;

    @Autowired
    private GeoEventDao eventDao;

    @Autowired
    private GeoEventRepository eventRepository;

    @Value("${event.persistance}")
    boolean isEventPersistanceEnabled;

    @Value("${event.threshold}")
    private double threshold;

    @Autowired
    private Utilities utilities;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Consumes("application/json")
    public MarshallDetails register(@RequestBody MarshallDetails marshallDetails) {
        if (marshallDetails != null && isUserDetailsValid(marshallDetails)) {
            return userDao.save(marshallDetails);
        }
        return null;
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public MarshallDetails getUser(@PathVariable Long id) {
        return userDao.findOne(id);
    }



    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestBody GeoEvent event) {
        if (event != null || isGeoEventValid(event)) {
            event.setuserId(event.getId());
            event.setId(0);
            MarshallDetails marshallDetails = userDao.findOne(event.getuserId());
            event.setMarshallDetails(marshallDetails);
            eventDao.put(event);
            if (isEventPersistanceEnabled)
                eventRepository.save(event);
        }
    }

    @RequestMapping(value = "/getGeoForUsers", method = RequestMethod.POST)
    public Set<GeoEvent> getEventsForUsers(@RequestBody Set<Long> userList) {
        Set<GeoEvent> geoEvents = new HashSet<>();
        userList.stream().filter((p) -> userDao.findOne(p) != null).forEach((p) -> {
            if (eventDao.get(p) != null)
                geoEvents.add(eventDao.get(p));
            System.out.println(p);
        });
        return geoEvents;
    }

    @RequestMapping(value = "/getGeoId/{id}", method = RequestMethod.GET)
    public GeoEvent getGeoforID(@PathVariable long id) {
        return eventDao.get(id);
    }

    @RequestMapping(value = "/getGeoAll/{hour}", method = RequestMethod.GET)
    public Set<GeoEvent> getAllGeoLocation(@PathVariable Integer hour) {
        Set<GeoEvent> eventSet = new HashSet<>();
                eventDao.getAll().stream().filter((p) -> p.getTimestamp() >
                                LocalDateTime.now(ZoneOffset.UTC).minusHours(hour).toInstant(ZoneOffset.UTC).toEpochMilli()
                ).forEach((p) -> eventSet.add(p));
        return eventSet;
    }



    @RequestMapping(value = "/trackUser/{id}/{from}/{to}", method = RequestMethod.GET)
    public Set<GeoEvent> trackUser(@PathVariable long id, @PathVariable long from, @PathVariable long to) {
        Set<GeoEvent> geoEvents = new TreeSet<>((p1, p2) -> p1.getId().intValue() - p2.getId().intValue());
        if (userDao.findOne(id) != null)
            eventRepository.findByuserId(id).
                    forEach((p) -> {
                        p.setMarshallDetails(userDao.findOne(p.getuserId()));
                        if (p.getMarshallDetails() != null) {
                            if (p.getTimestamp() >= from && p.getTimestamp() <= to) {
                                geoEvents.add(p);
                            }
                        }
                    });
        return geoEvents;
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public Set<MarshallDetails> getAllUsers() {
        Set<MarshallDetails> l = new HashSet<>();
        userDao.findAll().forEach((p) -> l.add(p));
        return l;
    }

    @RequestMapping(value = "/getNoUpdates/{min}",method = RequestMethod.GET)
    public Set<GeoEvent> getNoUpdateSince(@PathVariable int min) {
        Set<GeoEvent> userDetailses = new HashSet<>();
        eventDao.getAll().stream().filter((p) -> {
            return p.getTimestamp() >=LocalDateTime.ofEpochSecond(p.getTimestamp(), 0, ZoneOffset.UTC).minusMinutes(min).toInstant(ZoneOffset.UTC).toEpochMilli();
        }).forEach((p) -> userDetailses.add(p));
        return userDetailses;

    }

@RequestMapping(value = "/getSteadyUsers",method = RequestMethod.GET)
    public Set<GeoEvent> getSteadyUsers(){
        Set<GeoEvent> geoEvents = new HashSet<>();
        userDao.findAll().forEach((p)-> {

            List<GeoEvent> eventList =eventRepository.findByuserId(p.getId());
            eventList.sort((p1,p2)->p2.getId().intValue()-p1.getId().intValue());
            eventList.stream().limit(2).reduce((p1,p2)-> {
                double d=Utilities.getDistanceFromLatLonInKm(p1.getLatitude(),p1.getLongitude(),p2.getLatitude(),p2.getLongitude());
          long time = p1.getTimestamp()-p2.getTimestamp();
              time=  time==0? 1:time;
                if(d/time > threshold){
                    geoEvents.add(p1);
                }
                return p1;
            });
        });
        return geoEvents;
    }


    @RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
    public AreaList getAreaList(HttpServletRequest request)  {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AreaList al = new AreaList();
        List<GetAreasResult> l = new ArrayList<>();
        al.setGetAreasResult(l);
        l.addAll(userDetails.getAreaMap().values());
        return al;
    }


    @RequestMapping(value = "/getCentreList/{AREAID}", method = RequestMethod.POST)
    public CentreList getCentreList(@PathVariable String AREAID)  {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (userDetails.getAreaCMap().get(AREAID));
    }
    /*@PostConstruct
    void initMap() {
        eventRepository.findLatestDistinctEvents().forEach((p) -> {
            p.setMarshallDetails(userDao.findOne(p.getuserId()));
            eventDao.put(p);
        });

    }*/

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception e) {
        utilities.send(e.getMessage());

        return "Error received: " + e.getMessage();

    }

    private boolean isUserDetailsValid(MarshallDetails marshallDetails) {
        if (Validator.isValidOnlyString(marshallDetails.getName())) ;
        return true;
    }

    private boolean isGeoEventValid(GeoEvent event) {
        return true;
    }


}