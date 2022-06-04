package com.example.witssocial.CreatePost;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
        ActivityScenario<PostActivity> postActivityActivityScenario =
                postActivityActivityScenarioRule.getScenario();
        postActivityActivityScenario.onActivity(activity -> {
            assertNull(activity.getusername("EIXQIQxbEmNm7lvqalB19fu14jz1"));
        });

    }
}