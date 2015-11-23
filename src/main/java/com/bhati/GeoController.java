package com.bhati;

import com.bhati.dao.GeoEventDao;
import com.bhati.dao.GeoEventMao;
import com.bhati.dao.UserDetailsDao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by aman on 22/11/15.
 */
@RestController
public class GeoController {

    @Autowired
    private UserDetailsDao userDao;

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

    public void getGeoLocation(@PathVariable Long id){

    }

    @RequestMapping(value = "/getallGeo", method = RequestMethod.GET)
    public String getAllGeoLocation(){
        Set<GeoEvent> eventSet = eventDao.getAll();
        for(GeoEvent event :eventSet){
            
        }

    }

    public void setEventforId(){
    }

    public boolean isUserDetailsValid(UserDetails userDetails) {
        return true;
    }

    public boolean isGeoEventValid(GeoEvent event) {
        return true;
    }


}
