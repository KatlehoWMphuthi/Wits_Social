package com.example.witssocial.Profile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Home.HomeActivity;
import com.example.witssocial.Home.HomeFragment;
import com.example.witssocial.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProfileFragmentTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void test_ProfileInView(){
        homeActivityActivityScenarioRule.getScenario()
                .onActivity(activity -> {

                    FragmentTransaction transaction= activity
                            .getSupportFragmentManager()
                            .beginTransaction();
                    goToFragment(transaction);
                });
        onView(withId(R.id.btn_follow)).perform(click());
        //onView(withId(R.id.btn_follow)).check(matches(isDisplayed()));

    }




    public void goToFragment(FragmentTransaction transaction){

        ProfileFragment profileFragment = new ProfileFragment();
        transaction.replace(R.id.fragment_container,profileFragment);
        transaction.commit();
    }

    public void gotoHome(FragmentTransaction transaction){
        HomeFragment homeFragment = new HomeFragment();
        transaction.add(homeFragment,"homeFragment");
        transaction.commit();
    }

}
