package com.bhati.security;

import com.bhati.common.Util;
import com.bhati.security.types.AreaList;
import com.bhati.security.types.CentreList;
import com.bhati.security.types.GetAreasResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
public class BimsAuthUserDetailsService implements AuthenticationUserDetailsService {

    private static final Log logger = LogFactory.getLog(BimsAuthUserDetailsService.class);




    private String bimsCentreUrl;
    private String bimsValidateUrl;
    private String bimsAreaUrl;

    private RestTemplate restTemplate = new RestTemplate();
    private com.bhati.security.types.UserDetails userDetails =null;


    public BimsAuthUserDetailsService(String bimsValidateUrl, String bimsAreaUrl, String bimsCentreUrl) {
        this.bimsValidateUrl = bimsValidateUrl;
        this.bimsAreaUrl = bimsAreaUrl;
        this.bimsCentreUrl = bimsCentreUrl;
        assert bimsValidateUrl != null && bimsAreaUrl != null && bimsCentreUrl != null;
    }

    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        HttpEntity<String> entity = Util.addTokenToHeader((String) token.getCredentials());

        ResponseEntity<com.bhati.security.types.UserDetails> response = restTemplate.exchange(bimsValidateUrl, HttpMethod.GET, entity, com.bhati.security.types.UserDetails.class, token);
        boolean notvalid = response.getBody().isHasError();
        if (notvalid)
            throw new UsernameNotFoundException("Username not found in BIMS");

        userDetails = response.getBody();
        try {
            fetchAreaList((String)token.getCredentials());
        }
        catch (Exception e){
            throw  new UsernameNotFoundException("error fetching area or centre");
        }
        return userDetails;
    }


    private void fetchAreaList(String token) throws IOException, URISyntaxException {

        HttpEntity<String> entity = Util.addTokenToHeader(token);
        ResponseEntity<AreaList> response = restTemplate.exchange(bimsAreaUrl, HttpMethod.GET, entity, AreaList.class, token);
        System.out.println(response.getBody());
        for (GetAreasResult areaL : response.getBody().getGetAreasResult()) {
            userDetails.getAreaMap().put(areaL.getAreaId(), areaL);
            {
                fetchCentreList(areaL, entity);
            }
        }

    }

    private void fetchCentreList(GetAreasResult areaL, HttpEntity<String> entity) {

        ResponseEntity<CentreList> centerRe = restTemplate.exchange(bimsCentreUrl, HttpMethod.GET, entity, CentreList.class, areaL.getAreaId());
        userDetails.getAreaCMap().put(areaL.getAreaId().trim(), centerRe.getBody());
    }


}
