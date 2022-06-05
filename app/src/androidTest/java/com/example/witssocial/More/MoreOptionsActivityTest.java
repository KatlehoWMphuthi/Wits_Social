package com.example.witssocial.More;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;

public class MoreOptionsActivityTest {
    @Rule
    public ActivityScenarioRule<MoreOptionsActivity> moreOptionsActivityActivityScenarioRule=
            new ActivityScenarioRule<MoreOptionsActivity>(MoreOptionsActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.btnSignout)).check(matches(isDisplayed()));
    }
}