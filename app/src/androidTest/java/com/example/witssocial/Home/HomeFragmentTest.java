package com.example.witssocial.Home;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Profile.UserProfileFragment;
import com.example.witssocial.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Before
    public void setup(){
        ActivityScenario<SignInActivity> scenario = ActivityScenario.launch(SignInActivity.class);
        Intents.init();
        onView(withId(R.id.stu_mail_login)).perform(typeText("1355854"),closeSoftKeyboard());
        onView(withId(R.id.EditText_login_Password)).perform(typeText("123abc"),closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
        Intent intent = new Intent(getApplicationContext(),SignInActivity.class);

        ActivityScenario<SignInActivity> scenario1 = ActivityScenario.launch(intent);

        ActivityScenario<HomeActivity>scenario2 = ActivityScenario.launch(HomeActivity.class);
        Intents.release();
    }


    @Test
    public void onCreateView() {
        homeActivityActivityScenarioRule.getScenario().
                onActivity(activity -> {Runnable runnable = () -> {
                    FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.add(homeFragment,"homeFragment");
                    transaction.commit();
                };
                });

        onView(withId(R.id.iv_home_profile_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void test1_clickonprofile(){
        homeActivityActivityScenarioRule.getScenario().
                onActivity(activity -> {Runnable runnable = () -> {
                    FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.add(homeFragment,"homeFragment");
                    transaction.commit();
                };
                });

        onView(withId(R.id.iv_home_profile_picture)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_home_profile_picture)).perform(click());
    }
}

