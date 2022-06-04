package com.example.witssocial.Home;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Profile.UserProfileFragment;
import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

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
}

