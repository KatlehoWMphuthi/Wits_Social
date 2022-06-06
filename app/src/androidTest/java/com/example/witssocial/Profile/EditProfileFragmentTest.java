package com.example.witssocial.Profile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EditProfileFragmentTest {

    @Rule
    public ActivityScenarioRule<UserProfileActivity> userProfileActivityActivityScenarioRule =
            new ActivityScenarioRule<UserProfileActivity>(UserProfileActivity.class);

    @Test
    public void test_isSavebuttoninView(){
        userProfileActivityActivityScenarioRule.getScenario()
                .onActivity(activity -> {
                    FragmentTransaction transaction= activity
                            .getSupportFragmentManager()
                            .beginTransaction();
                    goToFragment(transaction);
                });

        onView(withId(R.id.name)).perform(typeText("TestUser"),closeSoftKeyboard());
        onView(withId(R.id.biography)).perform(typeText("TestUser"),closeSoftKeyboard());
        onView(withId(R.id.website)).perform(typeText("TestUser"),closeSoftKeyboard());
        onView(withId(R.id.btn_edit_profile_save)).perform(scrollTo(),click());
        //onView(withId(R.id.btn_edit_profile_save)).perform(scrollTo()).check(matches(isDisplayed()));


    }

    public void goToFragment(FragmentTransaction transaction){

        EditProfileFragment editProfileFragment = new EditProfileFragment();
        transaction.replace(R.id.user_profile_container,editProfileFragment);
        transaction.commit();
    }

}