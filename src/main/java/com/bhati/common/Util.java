package com.bhati.common;

import com.bhati.security.types.UserDetails;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
public class Util {

    public static HttpEntity<String> addTokenToHeader(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(Constants.ACCESS_TOKEN, token);
        HttpEntity<String> entity = new HttpEntity<String>( headers);
        return entity;
    }

    public static org.springframework.security.core.userdetails.UserDetails mapUserDetails(UserDetails body) {

        return null;
    }
}
