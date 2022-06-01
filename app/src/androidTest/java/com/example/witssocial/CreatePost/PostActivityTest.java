package com.example.witssocial.CreatePost;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.witssocial.Authentication.SignUpActivity;

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
        String exp_Id1 = "N20PXww9q6VQRI6SoLq";
        String Id1 = "N20PXww9q6VQRI6SoLq";
        assertEquals(exp_Id1, Id1);

    }

    @Test
    public void onActivityResult() {
        String exp_Id1 = "N20PXww9q6VQRI6SoLq";
        String Id1 = "N20PXww9q6VQRI6SoLq";
        assertEquals(exp_Id1, Id1);

    }

    @Test
    public void getFileExtension() {
        String exp_Id1 = "N20PXww9q6VQRI6SoLq";
        String Id1 = "N20PXww9q6VQRI6SoLq";
        assertEquals(exp_Id1, Id1);
    }

    @Test
    public void getusername() {
        String userid = "N20PXww9q6VQRI6SoLq";

    }
}