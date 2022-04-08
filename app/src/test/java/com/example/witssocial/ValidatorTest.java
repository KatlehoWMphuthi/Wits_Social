package com.example.witssocial;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;


@RunWith(JUnit4.class)

public class ValidatorTest {

    @Test
    public void isinputValid(){
        String a = "Katleho";
        Boolean b = new Validator(a).isinputString();
        assertThat(b).isEqualTo(true);
    }
}