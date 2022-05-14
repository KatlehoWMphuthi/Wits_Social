package com.example.witssocial.Profile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {
    TextView profilename, biography, fullName;
    ImageView profilepic, picture;
    FirebaseDatabase database;
    DatabaseReference reference, users, reference1;
    FirebaseUser user;
    String user_name;
    ArrayList<Integer> cheats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // showing the back button in action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);


        profilepic = (ImageView) findViewById(R.id.iv_profile_picture);
        profilename = (TextView) findViewById(R.id.tv_username);
        biography = (TextView) findViewById(R.id.tv_bio);

    }
}