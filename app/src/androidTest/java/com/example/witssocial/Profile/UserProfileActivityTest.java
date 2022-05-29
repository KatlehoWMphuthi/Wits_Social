package com.example.witssocial.Profile;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserProfileActivityTest {
    @Rule
    public ActivityScenarioRule<UserProfileActivity> userProfileActivityActivityScenarioRule=
            new ActivityScenarioRule<UserProfileActivity>(UserProfileActivity.class);

    @Test
    public void onCreate(){
    }
}