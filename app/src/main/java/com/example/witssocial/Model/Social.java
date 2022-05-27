package com.example.witssocial.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Social {
    String facebook,website, twitter,instagram,linkedin;


    public Social(String website, String facebook,String twitter,String linkedin,String instagram){
        this.website = website;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.linkedin = linkedin;


    }
    public Social(){}

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getWebsite() {
        return website;
    }




}
