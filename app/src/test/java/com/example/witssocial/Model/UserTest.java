package com.example.witssocial.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    User user = new User("63ba23a4-606d-4f87-8d07-714e43675670","Michael","fullname","imageurl","bio");
    @Test
    public void getId() {
        String exp_Id = "63ba23a4-606d-4f87-8d07-714e43675670";

        String Id = user.getId();
        assertEquals(exp_Id, Id);
    }

    @Test
    public void setId() {
        User user = new User();
        user.setId("N20PXww9q6VQRI6SoLq");
        String Id1 = "N20PXww9q6VQRI6SoLq";
        assertEquals(user.getId(), Id1);
    }

    @Test
    public void getUsername() {
        String exp_name1 = "Michael";
        String name1 = user.getUsername();
        assertEquals(exp_name1,name1);
    }

    @Test
    public void setUsername() {
        User user = new User();
        user.setUsername("hey");
        String name2 = "hey";
        assertEquals(user.getUsername(),name2);
    }

    @Test
    public void getFullname() {
        String exp_Fullname = user.getFullname();
        String Fullname = "fullname";
        assert(exp_Fullname).equals(Fullname);
    }

    @Test
    public void setFullname() {
        String exp_Fullname = "hey";
        User user = new User();
        user.setFullname(exp_Fullname);
        String Fullname = user.getFullname();
        assertEquals(exp_Fullname,Fullname);
    }

    @Test
    public void getImageurl() {
       String image  =  user.getImageurl();
       assertEquals(image,user.getImageurl());
    }

    @Test
    public void setImageurl() {
        String exp_ImgUrlr = "7-da22153470fd";
        User user = new User();
        user.setImageurl(exp_ImgUrlr);
        String ImgUrlr =user.getImageurl();
        assertEquals(exp_ImgUrlr,ImgUrlr);
    }

    @Test
    public void getBio() {
        String exp_Bio = "bio";
        String ImgUrl_Bio = user.getBio();
        if (!ImgUrl_Bio.isEmpty()) {
            assertEquals(exp_Bio, ImgUrl_Bio);
        }
    }
    @Test
    public void setBio() {

        String exp_Bio = "I am tired";
        User user = new User();
        user.setBio(exp_Bio);
        String ImgUrl_Bio = user.getBio();
        if (!ImgUrl_Bio.isEmpty()) {
            assertEquals(exp_Bio, ImgUrl_Bio);
        }
    }
}
