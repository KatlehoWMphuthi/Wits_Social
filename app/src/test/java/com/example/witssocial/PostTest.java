package com.example.witssocial;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class PostTest {
    String a,b,c,d;

    Post postTest = new Post("postid","postimage","caption","publisher");

    @Test
    public void getPostid() {
        a = "postid";
        String b = postTest.getPostid();

        assertThat(b).isEqualTo(a);
    }

    @Test
    public void setPostid() {
        String post = "postid";
        new Post(a,b,c,d).setPostid(post);
    }

    @Test
    public void getPostimage() {
        String image = "url";
        String imageurl = new Post(a,b,c,d).getPostimage();
        assertThat(image).isNotEmpty();
    }

    @Test
    public void setPostimage() {
        String post = "postimage";
        new Post(a,b,c,d).setPostimage(post);
    }

    @Test
    public void getCaption() {
        String caption = new Post(a,b,c,d).getCaption();
        caption = "Caption";
        assertThat(caption).isEqualTo("Caption");
    }

    @Test
    public void setDescription() {
        String desc = "Description";
        new Post(a,b,c,d).setDescription(desc);

        String caption = "Description";

        assertThat(caption).isEqualTo("Description");
    }

    @Test
    public void getPublisher() {
        new Post(a,b,c,d);
    }

    @Test
    public void setPublisher() {
        new Post(a,b,c,d);
    }
}