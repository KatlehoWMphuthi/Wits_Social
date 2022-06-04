package com.example.witssocial.Notification;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class NotificationTest {
    Notification notification= new Notification("userid","text","postid",true);
    @Test
    public void getUserid() {
        assertThat(notification.getUserid()).isInstanceOf(String.class);
    }

    @Test
    public void setUserid() {

        notification.setUserid("userid");
        assertThat(notification.getUserid()).isNotEmpty();
    }

    @Test
    public void getText() {
        assertThat(notification.getText()).isNotEmpty();
    }

    @Test
    public void setText() {
        notification.setText("text");
        assertThat(notification.getText()).isNotEmpty();
    }

    @Test
    public void getPostid() {
        assertThat(notification.getPostid()).isInstanceOf(String.class);
    }

    @Test
    public void setPostid() {
        notification.setPostid("postid");
        assertThat(notification.getPostid()).isNotEmpty();
    }

    @Test
    public void isIspost() {
        assertThat(notification.isIspost()).isTrue();
    }

    @Test
    public void setIspost() {
        notification.setIspost(false);
        assertThat(notification.isIspost()).isInstanceOf(Boolean.class);
    }
}