package com.bhati;

import com.bhati.controller.GeoController;
import com.bhati.dao.GeoEventDao;
import com.bhati.dao.impl.GeoEventMao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.UserDetails;
import org.junit.Test;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by aman on 24/11/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
public class TestService {


    GeoEventMao mao = new GeoEventMao();
    public static final String REGISTER_URI ="http://localhost:8080/register";

    RestTemplate t = new TestRestTemplate();




   @Test
    public void registerUser(){
        UserDetails details = new UserDetails("aman",9999700996l,"bhati","g123", "dl 1100");
        try {
            HttpEntity<UserDetails> userDetailsHttpEntity = new RequestEntity<UserDetails>(details, HttpMethod.POST,new URI(REGISTER_URI));
           ResponseEntity<UserDetails> ud= t.postForEntity(REGISTER_URI, details, UserDetails.class );
            assert(!ud.getBody().isNew());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
}

        public void testevent() {
            GeoEvent event = new GeoEvent("XXYYXX", 23.4, 12.2);
            GeoEvent event1 = new GeoEvent("XYZXYZ", 23.4, 12.2);
            mao.put(event);
            mao.put(event1);
            assertEquals(mao.get("XXYYXX"), event);
        }
}

