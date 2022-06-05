package com.example.witssocial.Authentication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.example.witssocial.R;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Locale;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)

public class SignUpActivityTest {

    private int AnrCount = 0;
    //`RootViewWithoutFocusException` class is private, need to match the message (instead of using type matching).
    private String rootViewWithoutFocusExceptionMsg = java.lang.String.format(
            Locale.ROOT,
            "Waited for the root of the view hierarchy to have "
                    + "window focus and not request layout for 10 seconds. If you specified a non "
                    + "default root matcher, it may be picking a root that never takes focus. "
                    + "Root:");

    final String testUsername = randomUsername();
    final String testPassword = "password";

    @Rule
    public ActivityScenarioRule<SignUpActivity> signUpActivityActivityScenarioRule =
            new ActivityScenarioRule<SignUpActivity>(SignUpActivity.class);

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


    @After
    public void tearDown() throws Exception {



    }

    @Test
    public void onCreate() {

    }


    @Test
    public void RegisterUser() {

        onView(withId(R.id.Username)).perform(typeText(testUsername));
        onView(withId(R.id.StudentNumber)).perform(typeText(testUsername), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Password_editText)).perform(typeText(testPassword), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());

    }

    @Test
    public void test_noinfoprovided(){
        onView(withId(R.id.LoginButton)).perform(click());
    }

    @Test
    public void test_nopasswordprovided(){
        onView(withId(R.id.Username)).perform(typeText(testUsername),closeSoftKeyboard());
        onView(withId(R.id.StudentNumber)).perform(typeText(testUsername), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
    }

    @Test
    public void test_noemailpovided(){
        onView(withId(R.id.Username)).perform(typeText(testUsername),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.Password_editText)).perform(typeText(testPassword), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
    }



    public String randomUsername(){
        String username = "TestUser";
        Random random = new Random();
        int id = random.nextInt(1000);
        String id_ = Integer.toString(id);
        return username + id_;
    }





}