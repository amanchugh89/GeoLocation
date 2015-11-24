package com.bhati;

import com.bhati.controller.GeoController;
import com.bhati.dao.GeoEventDao;
import com.bhati.dao.impl.GeoEventMao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aman on 24/11/15.
 */

public class TestService {


    GeoController controller = new GeoController();
    GeoEventDao mao = new GeoEventMao();

//    @Test
//    public void registerUser(){
//        UserDetails details = new UserDetails("aman",9999700996l,"bhati","g123");
//        controller.register(details);
//    }
    @Test
    public void testevent(){
        GeoEvent event  = new GeoEvent(123l,23.4,12.2,312121l);
        GeoEvent event1  = new GeoEvent(113l,23.4,12.2,312121l);
        mao.put(event);
        mao.put(event1);
        assertEquals(mao.get(123l),event);


    }
}

