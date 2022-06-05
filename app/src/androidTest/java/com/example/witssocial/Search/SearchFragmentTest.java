package com.example.witssocial.Search;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Home.HomeActivity;
import com.example.witssocial.Profile.ProfileFragment;
import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchFragmentTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void test_isSearchbarinView(){
        homeActivityActivityScenarioRule.getScenario()
                .onActivity(activity -> {
                    FragmentTransaction transaction= activity
                            .getSupportFragmentManager()
                            .beginTransaction();
                    goToFragment(transaction);
                });

        onView(withId(R.id.search_bar)).check(matches(isDisplayed()));

    }

    public void goToFragment(FragmentTransaction transaction){

        SearchFragment searchFragment = new SearchFragment();
        transaction.replace(R.id.fragment_container,searchFragment);
        transaction.commit();
    }

}