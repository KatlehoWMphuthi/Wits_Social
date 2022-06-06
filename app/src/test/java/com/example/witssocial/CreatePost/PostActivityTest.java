package com.example.witssocial.CreatePost;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import static com.google.common.truth.Truth.assertThat;

import android.app.Instrumentation;
import android.provider.ContactsContract;




@RunWith(JUnit4.class)
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


    }
}
