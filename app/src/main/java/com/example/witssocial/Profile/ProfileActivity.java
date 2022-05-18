package com.example.witssocial.Profile;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView profilename, showBio, showUsername;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    UploadTask uploadTask;
    FirebaseDatabase Database;
    DatabaseReference myRef,users;
    private final int PICK_IMAGE_REQUEST = 71;
    SharedPreferences sp;
    // fetcing an image
    String imageUrl;
    FirebaseUser user;
    String  userCaption,postkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Get Data from a post
        ///String username = getIntent().getStringExtra("Username");

        // showing the back button in action bar
        //Add username as title
        Toolbar myToolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        showUsername = (TextView) findViewById(R.id.showusername);
        showUsername.setText("/@"+getIntent().getStringExtra("Username"));

        showBio = (TextView) findViewById(R.id.showbio);
        showBio.setText("Hey there! This is "+getIntent().getStringExtra("Username") + "'s bio.");

        Database = FirebaseDatabase.getInstance();
        myRef = Database.getReference("Posts");
        users = Database.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        sp = getSharedPreferences("User_info", MODE_PRIVATE);
        String name = sp.getString("name", "null");


        //TODO:
        // ADD User Details : Bio, Names, DOB:
        // Add User Posts  and any other relevant info


    }
}