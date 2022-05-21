package com.example.witssocial;

import static com.google.common.truth.Truth.assertThat;

import com.example.witssocial.Model.Post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PostTest {
    String a,b,c,d;

    Post postTest = new Post("postid","postimage","caption", "profilepicture");

    @Test

    public void getImage(){
        String image = postTest.getImage();
        assertThat(image).isNotEmpty();
    }

    @Test
    public void getCaption(){
        String caption  = postTest.getCaption();
        assertThat(caption).isNotEmpty();
    }

    @Test
    public void getUsername() {
        String name = postTest.getUsername();

        assertThat(name).isNotEmpty();
    }

    @Test
    public  void getProfilePicture(){
        String image = postTest.getProfilePicture();
        assertThat(image).isNotEmpty();
    }
}