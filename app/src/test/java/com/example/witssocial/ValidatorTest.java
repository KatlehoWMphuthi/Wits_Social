package com.example.witssocial;

import static com.google.common.truth.Truth.assertThat;

import com.example.witssocial.Utils.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)

public class ValidatorTest {

    @Test
    public void isinputValid(){
        String a = "Katleho";
        Boolean b = new Validator(a).isinputString();
        assertThat(b).isEqualTo(true);
    }
}