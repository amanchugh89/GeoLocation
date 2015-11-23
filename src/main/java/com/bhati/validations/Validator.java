package com.bhati.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aman on 23/11/15.
 */
public class Validator {
    public static boolean isValidString(){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("I am a string");
        boolean b = m.find();
        
    }

    public static boolean isValidNumber(){

    }


}
