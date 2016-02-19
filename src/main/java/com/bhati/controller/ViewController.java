package com.bhati.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sumiran Chugh on 2/18/2016.
 *
 * @copyright atlas
 */

@Controller
public class ViewController {


    @RequestMapping("/")
    public String home(){
        return "Master/userRegistration";
    }

    @RequestMapping("/map")
    public String map(){
        return "Master/map";
    }



}
