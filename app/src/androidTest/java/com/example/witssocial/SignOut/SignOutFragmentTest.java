package com.example.witssocial.SignOut;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.More.MoreOptionsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignOutFragmentTest {

    @Rule
    public ActivityScenarioRule<MoreOptionsActivity> moreOptionsActivityActivityScenarioRule =
            new ActivityScenarioRule<MoreOptionsActivity>(MoreOptionsActivity.class);

    @Before
    public void getFragment(){
        moreOptionsActivityActivityScenarioRule.getScenario()
                .onActivity(activity -> {

                });
    }

}