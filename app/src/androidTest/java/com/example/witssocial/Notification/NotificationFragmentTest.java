package com.example.witssocial.Notification;

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
public class NotificationFragmentTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);
    @Test
    public void onCreate(){
        homeActivityActivityScenarioRule.getScenario()
        .onActivity(activity -> {
            FragmentTransaction transaction= activity
                    .getSupportFragmentManager()
                    .beginTransaction();
            goToFragment(transaction);
        });
    }

    public void goToFragment(FragmentTransaction transaction){

        NotificationFragment notificationFragment = new NotificationFragment();
        transaction.replace(R.id.fragment_container,notificationFragment);
        transaction.commit();
    }
}