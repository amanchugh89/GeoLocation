/*
package com.bhati.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * Created by Sumiran Chugh on 1/12/2016.
 *//*

public class BimsPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {


    @Value("${bims.token}")
    private String tokenid;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        Object token = request.getParameter(tokenid) ;

        return token;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        */
/*Object token = request.getParameter(tokenid) ;
        if(token==null)
            throw new PreAuthenticatedCredentialsNotFoundException(
                    " token not found in request.");
        return token;*//*

        return getPreAuthenticatedPrincipal(request);
    }
}
*/
