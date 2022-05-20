package com.example.witssocial.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.R;
import com.example.witssocial.Utils.UserAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView profilename, showBio, showUsername;
    Button follow_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Get Data from a post
        ///String username = getIntent().getStringExtra("Username");

        // showing the back button in action bar
        //Add username as title
        Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");   //PLEASE LEAVE IT EMPTY!!! WE ALREADY GOT THE USERNAME!!!
        actionBar.setDisplayHomeAsUpEnabled(true);

        showUsername = (TextView) findViewById(R.id.display_name);
        showUsername.setText(getIntent().getStringExtra("Username"));

        showBio = (TextView) findViewById(R.id.bio);
        showBio.setText("Hey there! This is "+getIntent().getStringExtra("Username") + "'s bio.");


        follow_btn = (Button) findViewById(R.id.follow_btn);
        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(follow_btn.getText()=="Following") follow_btn.setText("Follow");
                else follow_btn.setText("Following");
            }
        } );



        //TODO:
        // ADD User Details : Bio, Names, DOB:
        // Add User Posts  and any other relevant info
    }



}