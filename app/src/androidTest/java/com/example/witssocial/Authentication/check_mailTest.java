package com.example.witssocial.Authentication;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.example.witssocial.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;
@RunWith(AndroidJUnit4.class)
public class check_mailTest {

    private int AnrCount = 0;
    //`RootViewWithoutFocusException` class is private, need to match the message (instead of using type matching).
    private String rootViewWithoutFocusExceptionMsg = java.lang.String.format(
            Locale.ROOT,
            "Waited for the root of the view hierarchy to have "
                    + "window focus and not request layout for 10 seconds. If you specified a non "
                    + "default root matcher, it may be picking a root that never takes focus. "
                    + "Root:");
    @Rule
    public ActivityScenarioRule<check_mail> check_mailActivityScenarioRule =
            new ActivityScenarioRule<check_mail>(check_mail.class);

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

        waitButton = device.findObject(new UiSelector().textContains("ok"));
        if (waitButton.exists()) {
            waitButton.click();
        }

        waitButton = device.findObject(new UiSelector().textContains("Please Wait"));
        if(waitButton.exists()){
            device.pressBack();
        }

    }

    @Test
    public void onCreate() {
        try {
            pressBack();
            dismissANRSystemDialog();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

}