package com.example.witssocial.More;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoreOptionsActivityTest {
    @Rule
    public ActivityScenarioRule<MoreOptionsActivity> moreOptionsActivityActivityScenarioRule=
            new ActivityScenarioRule<MoreOptionsActivity>(MoreOptionsActivity.class);

    @Test
    public void onCreate() {
    }
}