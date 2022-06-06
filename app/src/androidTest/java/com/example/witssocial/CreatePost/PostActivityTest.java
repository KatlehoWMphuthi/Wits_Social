package com.example.witssocial.CreatePost;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Home.HomeActivity;
import com.example.witssocial.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PostActivityTest {

    @Rule
    public ActivityScenarioRule<PostActivity> postActivityActivityScenarioRule =
            new ActivityScenarioRule<PostActivity>(PostActivity.class);

    @Test
    public void onCreate() {
    }




    @Test
    public void getusername() {
        ActivityScenario<PostActivity> postActivityActivityScenario =
                postActivityActivityScenarioRule.getScenario();
        postActivityActivityScenario.onActivity(activity -> {
            assertNull(activity.getusername("EIXQIQxbEmNm7lvqalB19fu14jz1"));
        });

    }

    @Test
    public void uploadimage(){
        ActivityScenario<SignInActivity> scenario = ActivityScenario.launch(SignInActivity.class);
        Intents.init();
        onView(withId(R.id.stu_mail_login)).perform(typeText("1355854"),closeSoftKeyboard());
        onView(withId(R.id.EditText_login_Password)).perform(typeText("123abc"),closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
        Intent intent = new Intent(getApplicationContext(),SignInActivity.class);

        ActivityScenario<SignInActivity> scenario1 = ActivityScenario.launch(intent);
        Intents.release();
        ActivityScenario<HomeActivity>scenario2 = ActivityScenario.launch(HomeActivity.class);

        onView(withId(R.id.nav_add)).perform(click());
        onView(withId(R.id.caption)).perform(typeText("This is a test"));
        onView(withId(R.id.btnUpload)).perform(click());
    }


}