package com.example.witssocial.Profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
    Button btnEditProfile;
    ProgressBar mProgressBar;
    FirebaseUser user;
    String user_name;
    ArrayList<Integer> cheats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Navigate to UserProfileFragment
        init();



/*
        // showing the back button in action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);


        profilepic = (ImageView) findViewById(R.id.profile_image);
        //profilename = (TextView) findViewById(R.id.tv_username);
        //biography = (TextView) findViewById(R.id.tv_bio);

        String url = "https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=20&m=1223671392&s=612x612&w=0&h=lGpj2vWAI3WUT1JeJWm1PRoHT3V15_1pdcTn2szdwQ0=";

        Picasso.get().load(url).resize(100,100).centerCrop().into(profilepic);

        //Handel the ProgressBar
        mProgressBar = findViewById(R.id.pb_profileProgressBar);
        mProgressBar.setVisibility(View.GONE);

        //set Up edit button to go to edit profile fragment
        btnEditProfile = findViewById(R.id.btn_edit_profile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.user_profile_container, new EditProfileFragment());
                transaction.commit();
            }
        });
*/
    }

    private void init(){

        UserProfileFragment fragment = new UserProfileFragment();
        FragmentTransaction transaction = UserProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.user_profile_container, fragment);
        transaction.addToBackStack("UserProfileFragment");
        transaction.commit();
    }
}