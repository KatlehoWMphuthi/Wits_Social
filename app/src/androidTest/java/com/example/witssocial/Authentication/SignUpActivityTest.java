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
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;


import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.R;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)

public class SignUpActivityTest {

    final String testUsername = randomUsername();
    final String testPassword = "password";

    @Rule
    public ActivityScenarioRule<SignUpActivity> signUpActivityActivityScenarioRule =
            new ActivityScenarioRule<SignUpActivity>(SignUpActivity.class);

    @Before
    public void setUp()  {
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