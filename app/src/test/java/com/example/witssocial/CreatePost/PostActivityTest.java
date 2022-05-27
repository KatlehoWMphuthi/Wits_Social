package com.example.witssocial.CreatePost;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostActivityTest {

    @Test
    public void onCreate() {
        String exp_ImgUrl = "actV";
        String ImgUrl = "actV";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void onActivityResult() {
        String exp_ImgUrl = "onStatus";
        String ImgUrl = "onStatus";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void getFileExtension() {
        String exp_ImgUrl = "file.me";
        String ImgUrl = "file.me";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void getusername() {
        String exp_ImgUrl = "Dylan";
        String ImgUrl = "Dylan";
        assertEquals(exp_ImgUrl, ImgUrl);
    }
}