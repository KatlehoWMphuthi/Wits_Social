package com.example.witssocial.Profile;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Get Data from a post
        String username = getIntent().getStringExtra("Username");

        // showing the back button in action bar
        //Add username as title
        Toolbar myToolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(username);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //TODO:
        // ADD User Details : Bio, Names, DOB:
        // Add User Posts  and any other relevant info
    }
}