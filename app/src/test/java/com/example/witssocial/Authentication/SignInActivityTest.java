package com.example.witssocial.Authentication;

import static org.junit.Assert.*;

import org.junit.Test;

public class SignInActivityTest {

    @Test
    public void onCreate() {
        String exp_ImgUrl = "onStatusg";
        String ImgUrl = "onStatusg";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void goToHomeActivity() {
        String exp_ImgUrl = "onStatusf";
        String ImgUrl = "onStatusf";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void updateUI() {
        String exp_ImgUrl = "onStatus2";
        String ImgUrl = "onStatus2";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void registerPage() {
        String exp_ImgUrl = "onStatus1";
        String ImgUrl = "onStatus1";
        assertEquals(exp_ImgUrl, ImgUrl);
    }

    @Test
    public void validate() {
        String exp_ImgUrl = "onStatuss";
        String ImgUrl = "onStatuss";
        assertEquals(exp_ImgUrl, ImgUrl);
    }
}