package com.bhati.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sumiran Chugh on 1/10/2016.
 */

public class HttpEntryPoint implements AuthenticationEntryPoint {

    private String bimsUrl;

    private String clientId;

    public HttpEntryPoint(String bimsUrl, String clientId) {
        this.bimsUrl = bimsUrl;
        this.clientId = clientId;
        assert bimsUrl != null;
        assert clientId != null;
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendRedirect(new StringBuilder(bimsUrl).append("?").append(clientId).toString());
    }
}
