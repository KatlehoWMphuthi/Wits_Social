package com.example.witssocial.Authentication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.annotation.ContentView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {
    @Rule
    public ActivityScenarioRule<SignInActivity> signInActivityActivityScenarioRule =
            new ActivityScenarioRule<SignInActivity>(SignInActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void test_SignInSuccessful(){
        onView(withId(R.id.stu_mail_login)).perform(typeText("1355854"));
        onView(withId(R.id.EditText_login_Password)).perform(typeText("123abc"));
        onView(withId(R.id.stu_mail_login)).perform(click());
    }

    @Test
    public void test1_Signinunsuccessful(){
        onView(withId(R.id.stu_mail_login)).perform(typeText("TestUser001"),closeSoftKeyboard());
        onView(withId(R.id.EditText_login_Password)).perform(typeText(" "),closeSoftKeyboard());
        onView(withId(R.id.stu_mail_login)).perform(click());
    }


    @Test
    public void test2_Signinwithnoinfo(){
        onView(withId(R.id.stu_mail_login)).perform(click());
    }

    @Test
     public void test_gotoForgetpasswordActivity(){
        onView(withId(R.id.forgot_pass)).perform(click());

    }
}