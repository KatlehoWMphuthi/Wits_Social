package com.example.witssocial.More.MoreOptions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.annotation.ContentView;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppSettingsActivityTest {

    @Rule
    public ActivityScenarioRule<AppSettingsActivity> appSettingsActivityActivityScenarioRule =
            new ActivityScenarioRule<>(AppSettingsActivity.class);
    @Test
    public void onCreate() {

        onView(withId(R.id.Switch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}