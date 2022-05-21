package com.example.witssocial.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SocialTest {

    @Test
    public void getFacebook() {
        String exp_link = "https://www.facebook.com/MichaelMdlaviaMalapane/";
        String link = UserSocialMedia.getfaceBookLink();
        if(!link.isEmpty()) {
            assertEquals(exp_link, link);
        }
    }

    @Test
    public void getInstagram() {
        String exp_link = "https://www.instagram.com/MichaelMdlaviaMalapane/";
        String link = UserSocialMedia.getInstaLink();
        if(!link.isEmpty()) {
            assertEquals(exp_link, link);
        }
    }

    @Test
    public void getLinkedin() {
        String exp_link = "https://www.linkedIn.com/MichaelMdlaviaMalapane/";;
        String link = UserSocialMedia.getLinkedIn();
        if(!link.isEmpty()) {
            assertEquals(exp_link, link);
        }
    }

    @Test
    public void getTwitter() {
        String exp_link = "https://www.twitter.com/MichaelMdlaviaMalapane/";;
        String link = UserSocialMedia.getTwiterlink();
        if(!link.isEmpty()) {
            assertEquals(exp_link, link);
        }
    }

    @Test
    public void getWebsite() {
        String exp_link = "https://www.michaelmalapane.com";
        String link = UserSocialMedia.getWebsiteLink();
        if(!link.isEmpty()) {
            assertEquals(exp_link, link);
        }
    }
}
class  UserSocialMedia
{


    public static String getfaceBookLink() {
        return "https://www.facebook.com/MichaelMdlaviaMalapane/";
    }
    public static String getInstaLink() {
        return "https://www.instagram.com/MichaelMdlaviaMalapane/";
    }
    public static String getTwiterlink() {
        return "https://www.twitter.com/MichaelMdlaviaMalapane/";
    }
    public static String getWebsiteLink() {
        return "https://www.michaelmalapane.com";
    }


    public static String getLinkedIn() {
        return "https://www.linkedIn.com/MichaelMdlaviaMalapane/";
    }
}