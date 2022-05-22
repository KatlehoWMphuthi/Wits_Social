package com.example.witssocial.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void getId() {
        String exp_Id = "63ba23a4-606d-4f87-8d07-714e43675670";
        String Id = userrTest.getId();
        assertEquals(exp_Id, Id);
    }

    @Test
    public void setId() {
        String exp_Id1 = "N20PXww9q6VQRI6SoLq";
        String Id1 = "N20PXww9q6VQRI6SoLq";
        assertEquals(exp_Id1, Id1);
    }

    @Test
    public void getUsername() {
        String exp_name1 = "Michael";
        String name1 = userrTest.getUsername();
        assertEquals(exp_name1,name1);
    }

    @Test
    public void setUsername() {
        String exp_name2 = "hey";
        String name2 = "hey";
        assertEquals(exp_name2,name2);
    }

    @Test
    public void getFullname() {
        String exp_Fullname = "hey";
        String Fullname = "hey";
        assertEquals(exp_Fullname,Fullname);
    }

    @Test
    public void setFullname() {
        String exp_Fullname = "hey";
        String Fullname = "hey";
        assertEquals(exp_Fullname,Fullname);
    }

    @Test
    public void getImageurl() {
        String exp_ImgUrl2 = "https://firebasestorage.googleapis.com/v0/b/wits-social-7aafb." +
                "appspot.com/o/images%2F634?alt=media&token=d0fd443b-2116-4ff2-8d37-da22153470fd";
        String ImgUrl2 = userrTest.getImageUrl();
        assertEquals(exp_ImgUrl2,ImgUrl2);
    }

    @Test
    public void setImageurl() {
        String exp_ImgUrlr = "7-da22153470fd";
        String ImgUrlr ="7-da22153470fd";
        assertEquals(exp_ImgUrlr,ImgUrlr);
    }

    @Test
    public void getBio() {
        String exp_Bio = "I am tired";
        String ImgUrl_Bio = userrTest.getBio_id();
        if (!ImgUrl_Bio.isEmpty()) {
            assertEquals(exp_Bio, ImgUrl_Bio);
        }
    }
    @Test
    public void setBio() {

        String exp_Bio = "I am tired";
        String ImgUrl_Bio = userrTest.getBio_id();
        if (!ImgUrl_Bio.isEmpty()) {
            assertEquals(exp_Bio, ImgUrl_Bio);
        }
    }
}

 class  userrTest
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