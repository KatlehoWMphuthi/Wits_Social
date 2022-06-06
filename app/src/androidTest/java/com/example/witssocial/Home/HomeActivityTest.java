package com.example.witssocial.Home;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Authentication.SignUpActivity;
import com.example.witssocial.Authentication.check_mail;
import com.example.witssocial.R;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Locale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    //Running count of the number of Android Not Responding dialogues to prevent endless dismissal.
    private int AnrCount = 0;
    //`RootViewWithoutFocusException` class is private, need to match the message (instead of using type matching).
    private String rootViewWithoutFocusExceptionMsg = java.lang.String.format(
            Locale.ROOT,
            "Waited for the root of the view hierarchy to have "
                    + "window focus and not request layout for 10 seconds. If you specified a non "
                    + "default root matcher, it may be picking a root that never takes focus. "
                    + "Root:");

    Context context = getInstrumentation().getContext();

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivityActivityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Before
    public void dismissSystem(){
        Espresso.setFailureHandler((error, viewMatcher) -> {
            if (error.getMessage().contains(rootViewWithoutFocusExceptionMsg) && AnrCount < 3) {
                AnrCount++;
                try {
                    dismissANRSystemDialog();
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
            } else { // chain all failures down to the default espresso handler
                new DefaultFailureHandler(getInstrumentation().getTargetContext()).handle(error, viewMatcher);
            }

        });
    }

    @BeforeClass
    public static void dismissANRSystemDialog() throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        // If the device is running in English Locale
        UiObject waitButton = device.findObject(new UiSelector().textContains("wait"));
        if (waitButton.exists()) {
            waitButton.click();
        }
        // If the device is running in Japanese Locale
        waitButton = device.findObject(new UiSelector().textContains("待機"));
        if (waitButton.exists()) {
            waitButton.click();
        }
    }

    @Test
    public void onCreate(){

    }


    @Test
    public void test2_getintoapp(){

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }
    @Test
    public void test3_gotoSearch(){

        onView(withId(R.id.nav_search)).perform(click());
    }

    @Test
    public void test4_gotopost(){
        onView(withId(R.id.nav_add)).perform(click());
    }

    @Test
    public void test5_gotonotification(){

        onView(withId(R.id.nav_notifications)).perform(click());
    }

    @Test
    public void test6_gotomore(){
        onView(withId(R.id.nav_more)).perform(click());
    }

}