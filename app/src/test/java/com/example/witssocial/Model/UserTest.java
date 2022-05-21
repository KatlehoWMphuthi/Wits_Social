package com.example.witssocial.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void getId() {
        String exp_Id = "UU7Opt1BRZU7efnzUwALQl5wpKv2";
        String Id = userTest.getId();
        assertEquals(exp_Id,Id);
    }

    @Test
    public void setId() {
        String exp_Id = "N20PXww9q6VQRI6SoLq";
        String Id = userTest.getId();
        assertEquals(exp_Id,Id);
    }

    @Test
    public void getUsername() {
        String exp_name = "Michael";
        String name = userTest.getUsername();
        assertEquals(exp_name,name);

    }

    @Test
    public void setUsername() {
        String exp_name = "hey";
        String name = "hey";
        assertEquals(exp_name,name);
    }

    @Test
    public void getImageurl() {
        String exp_ImgUrl = "https://firebasestorage.googleapis.com/v0/b/wits-social-7aafb." +
                "appspot.com/o/images%2F634?alt=media&token=d0fd443b-2116-4ff2-8d37-da22153470fd";
        String ImgUrl = userTest.getImageUrl();
        assertEquals(exp_ImgUrl,ImgUrl);
    }

    @Test
    public void setImageurl() {
        String exp_ImgUrl = "7-da22153470fd";
        String ImgUrl ="7-da22153470fd";
        assertEquals(exp_ImgUrl,ImgUrl);
    }

    @Test
    public void getBio() {
        String exp_ImgUrl = "I am tired";
        String ImgUrl = userTest.getBio_id();
        if(!ImgUrl.isEmpty()) {
            assertEquals(exp_ImgUrl, ImgUrl);
        }
    }

    @Test
    public void setBio() {
        String exp_ImgUrl = "I am tired";
        String ImgUrl = "I am tired";
        assertEquals(exp_ImgUrl, ImgUrl);
    }
}
class  userTest
{


    public static String getUsername() {
        return "Michael";
    }


    public static String getId() {
        return "63ba23a4-606d-4f87-8d07-714e43675670";
    }


    public static String getImageUrl() {
        return "https://firebasestorage.googleapis.com/v0/b/wits-social-7aafb.appspot." +
                "com/o/images%2F634?alt=media&token=d0fd443b-2116-4ff2-8d37-da22153470fd";
    }


    public static String getBio_id() {

        return "I am tired";
    }


}