package com.bhati;

import com.bhati.entity.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aman on 22/11/15.
 */
@RestController
public class GeoController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam UserDetails userDetails){
        if(userDetails!=null){

        }
    }

    public void addEvent(){

    }

    public void getGeoLocation(){

    }

    public void getAllGeoLocation(){

    }

    public void setEventforId(){

    }
    public void validateUserDetails(UserDetails userDetails) {

    }


}
