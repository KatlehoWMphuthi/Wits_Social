package com.example.witssocial.Profile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserProfileActivityTest {
    @Rule
    public ActivityScenarioRule<UserProfileActivity> userProfileActivityActivityScenarioRule=
            new ActivityScenarioRule<UserProfileActivity>(UserProfileActivity.class);



    @Test
    public void onCreate() {
        userProfileActivityActivityScenarioRule.getScenario().
    onActivity(activity -> {Runnable runnable = new Runnable() {
        @Override
        public void run() {
            FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
            UserProfileFragment userProfileFragment = new UserProfileFragment();
            transaction.add(userProfileFragment,"userProfileFragment");
            transaction.commit();
        }
    };
    });
        // Then use Espresso to test the Fragment
        onView(withId(R.id.bar)).check(matches(isDisplayed()));
    }


}

