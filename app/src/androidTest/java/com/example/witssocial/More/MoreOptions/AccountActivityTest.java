package com.example.witssocial.More.MoreOptions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.app.UiAutomation;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AccountActivityTest {

    @Rule
    public ActivityScenarioRule<AccountActivity> accountActivityActivityScenarioRule =
            new ActivityScenarioRule<AccountActivity>(AccountActivity.class);

    @Test
    public void test_PageinView(){
        onView(withId(R.id.profileActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.forgotPassActivity)).check(matches(isDisplayed()));
    }
}