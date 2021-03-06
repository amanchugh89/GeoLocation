package com.bhati;

import com.bhati.dao.impl.GeoEventMao;
import com.bhati.entity.GeoEvent;
import com.bhati.entity.MarshallDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public static final String TEST_URI ="http://localhost:8080/addEvent";

    RestTemplate t = new TestRestTemplate();




   @Test
    public void registerUser(){
        MarshallDetails details = new MarshallDetails("aman",9999700996l,"bhati","g123", "dl 1100");
        try {
            HttpEntity<MarshallDetails> userDetailsHttpEntity = new RequestEntity<MarshallDetails>(details, HttpMethod.POST,new URI(REGISTER_URI));
           ResponseEntity<MarshallDetails> ud= t.postForEntity(REGISTER_URI, details, MarshallDetails.class );
            assert(!ud.getBody().isNew());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
}
        @Test
        public void testevent() {

            GeoEvent event = new GeoEvent(1,11.22,22.34);
            GeoEvent event2 = new GeoEvent(2,21.22,24.34);
            try {
                HttpEntity<GeoEvent> eventsHttpEntity = new RequestEntity<GeoEvent>(event, HttpMethod.POST,new URI(TEST_URI));
                ResponseEntity<GeoEvent> ud= t.postForEntity(TEST_URI, eventsHttpEntity, GeoEvent.class );
                assert(!ud.getBody().isNew());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
}

