package com.example.witssocial.Authentication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.annotation.ContentView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;

public class ForgotPasswordActivityTest {
    @Rule
    public ActivityScenarioRule<ForgotPasswordActivity> forgotPasswordActivityActivityScenarioRule=
            new ActivityScenarioRule<ForgotPasswordActivity>(ForgotPasswordActivity.class);
    @Test
    public void onCreate() {
    }

    @Test
    public void test_resetbutton(){
        onView(withId(R.id.reset_mail_editText)).perform(typeText("testuser849"),closeSoftKeyboard());
        onView(withId(R.id.SubmitButton)).perform(click());

    }
}