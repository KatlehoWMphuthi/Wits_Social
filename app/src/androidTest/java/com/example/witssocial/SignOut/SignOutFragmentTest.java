package com.example.witssocial.SignOut;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.More.MoreOptionsActivity;
import com.example.witssocial.R;
import com.example.witssocial.Search.SearchFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignOutFragmentTest {

    @Rule
    public ActivityScenarioRule<MoreOptionsActivity> moreOptionsActivityActivityScenarioRule =
            new ActivityScenarioRule<MoreOptionsActivity>(MoreOptionsActivity.class);

    @Before
    public void clickdialog(){
        moreOptionsActivityActivityScenarioRule.getScenario()
                .onActivity(activity -> {

                    activity.openDialog();
                });
        onView(withText("Yes")).perform(click());

    }

    @Test
    public void test_isButtoninView(){

        //onView(withId(R.id.tvSigingout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public void goToFragment(FragmentTransaction transaction){

        SignOutFragment signOutFragment = new SignOutFragment();
        transaction.replace(R.id.more_options_container,signOutFragment);
        transaction.commit();
    }

}