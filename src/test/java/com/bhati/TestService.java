package com.bhati;

import com.bhati.controller.GeoController;
import com.bhati.entity.UserDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aman on 24/11/15.
 */
public class TestService {


    GeoController controller = new GeoController();

    @Test
    public void registerUser(){
        UserDetails details = new UserDetails("aman",9999700996l,"bhati","g123");
        controller.register(details);

    }
}

