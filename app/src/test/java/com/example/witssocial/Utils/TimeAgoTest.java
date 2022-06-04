package com.example.witssocial.Utils;

import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TimeAgoTest {

    @Test
    public void getTimeAgo() {
        String time = TimeAgo.getTimeAgo(1);
        assertThat(time).isInstanceOf(String.class);
    }
}