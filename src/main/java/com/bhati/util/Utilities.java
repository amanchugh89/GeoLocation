package com.bhati.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Created by aman on 22/11/15.
 */
@Component
public class Utilities {

    @Autowired
    private JavaMailSender javaMailSender;

    public static void toJson(){

    }

    public MimeMessage send(String message) {
        MimeMessage mailMessage =javaMailSender.createMimeMessage();

        
        javaMailSender.send(mailMessage);
        return mailMessage;
    }

    public static long getTimeStamp(){
        Long timestamp = System.currentTimeMillis();
        return timestamp;
    }

   public static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double d = R * c; // Distance in km
        return d.intValue();
    }

    static double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}
