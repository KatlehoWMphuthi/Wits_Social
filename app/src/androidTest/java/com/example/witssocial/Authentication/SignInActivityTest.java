package com.example.witssocial.Authentication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import androidx.annotation.ContentView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.example.witssocial.R;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Locale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {
    //Running count of the number of Android Not Responding dialogues to prevent endless dismissal.
    private int AnrCount = 0;
    //`RootViewWithoutFocusException` class is private, need to match the message (instead of using type matching).
    private String rootViewWithoutFocusExceptionMsg = java.lang.String.format(
            Locale.ROOT,
            "Waited for the root of the view hierarchy to have "
                    + "window focus and not request layout for 10 seconds. If you specified a non "
                    + "default root matcher, it may be picking a root that never takes focus. "
                    + "Root:");
    @Rule
    public ActivityScenarioRule<SignInActivity> signInActivityActivityScenarioRule =
            new ActivityScenarioRule<SignInActivity>(SignInActivity.class);

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
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void test_SignInSuccessful(){
        onView(withId(R.id.stu_mail_login)).perform(typeText("1355854"),closeSoftKeyboard());
        onView(withId(R.id.EditText_login_Password)).perform(typeText("123abc"),closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
        pressBack();
    }

    @Test
    public void test1_Signinunsuccessful(){
        onView(withId(R.id.stu_mail_login)).perform(typeText("TestUser001"),closeSoftKeyboard());
        onView(withId(R.id.EditText_login_Password)).perform(typeText(" "),closeSoftKeyboard());
        onView(withId(R.id.stu_mail_login)).perform(click());
        pressBack();
    }


    @Test
    public void test2_Signinwithnoinfo(){
        onView(withId(R.id.LoginButton)).perform(click());
        pressBack();
    }


     public void test_gotoForgetpasswordActivity(){
        onView(allOf(withId(R.id.forgot_pass))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.forgot_pass)).perform(click());
        pressBack();

    }
}