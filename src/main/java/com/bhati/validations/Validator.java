package com.bhati.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aman on 23/11/15.
 */
public class Validator {
    public static boolean isValidString(String str){
       boolean isvalid=false;
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        isvalid = m.find();
        return  isvalid;

    }


}
