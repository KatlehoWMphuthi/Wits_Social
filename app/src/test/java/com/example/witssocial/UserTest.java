package com.example.witssocial;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;


@RunWith(JUnit4.class)

public class UserTest {
    User person;
    String a,b,c,d;



    @Test
    public void getId() {
        person = new User();
        String a = person.getId();
        String b = a;

        assertThat(a).isNotEmpty();
    }

    @Test
    public void getUsername() {
        person = new User();
        String a = person.getId();
        String b = a;

        assertThat(a).isNotEmpty();
    }
}