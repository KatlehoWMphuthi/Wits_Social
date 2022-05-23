package com.example.witssocial.Authentication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;

public class check_mailTest {
    @Rule
    public ActivityScenarioRule<check_mail> check_mailActivityScenarioRule =
            new ActivityScenarioRule<check_mail>(check_mail.class);
    @Test
    public void onCreate() {

    }
}