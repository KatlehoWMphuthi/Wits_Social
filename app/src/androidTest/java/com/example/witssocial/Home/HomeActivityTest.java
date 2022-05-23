package com.example.witssocial.Home;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.witssocial.Authentication.check_mail;

import org.junit.Rule;
import org.junit.Test;

public class HomeActivityTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);
    @Test
    public void onCreate() {
    }
}