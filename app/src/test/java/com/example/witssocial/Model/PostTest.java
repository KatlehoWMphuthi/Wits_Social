package com.example.witssocial.Model;

import static org.junit.Assert.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class PostTest {

    @Test
    public void getImage() {
        String exp_imgUrl = "63ba23a4-606d-4f87-8d07-714e43675670";
        String imgUrl = firebase.getImg_url();
        assertEquals(exp_imgUrl,imgUrl);
    }

    @Test
    public void getCaption() {
        String exp_imgCaption = "Games";
        String imgCaption = firebase.getImgCaption();
        assertEquals(exp_imgCaption,imgCaption);
    }

    @Test
    public void getUsername() {
        String exp_name = "Michael";
        String name = firebase.getName();
        assertEquals(exp_name,name);

    }

    @Test
    public void getPostid() {
        String exp_postID = "N2OoFRT7r_3iY_Ixl2K";
        String id = firebase.getPost_id();
        assertEquals(exp_postID,id);

    }

}

class  firebase
{

    String name;
    String img_url;
    String imgCaption;
    String Post_id;

    public static String getName() {
        return "Michael";
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getImg_url() {
        return "63ba23a4-606d-4f87-8d07-714e43675670";
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public static String getImgCaption() {
        return "Games";
    }

    public void setImgCaption(String imgCaption) {
        this.imgCaption = imgCaption;
    }

    public static String getPost_id() {
        return "N2OoFRT7r_3iY_Ixl2K";
    }

    public void setPost_id(String post_id) {
        Post_id = post_id;
    }
}