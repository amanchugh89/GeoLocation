package com.bhati.util;

import com.bhati.entity.UserDetails;
import com.bhati.repository.GeoEventRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aman on 26/11/15.
 */
public class ColdStart {
    public static Map<String,String> buildDetailsMap(UserDetails details){
        Map<String,String> detailsMap = new HashMap<>();
        if(details!=null){
            detailsMap.put("name",details.getName());
            detailsMap.put("vehicleNumber",details.getVehicleNumber());
            detailsMap.put("mobile",Long.toString(details.getMobile()));
        }
        return detailsMap;
    }
    public static void buildEventMap( GeoEventRepository eventRepository)
    {

    }

}
