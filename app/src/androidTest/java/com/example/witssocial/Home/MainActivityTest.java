package com.example.witssocial.Home;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.witssocial.Authentication.check_mail;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void onPause() {
    }
}