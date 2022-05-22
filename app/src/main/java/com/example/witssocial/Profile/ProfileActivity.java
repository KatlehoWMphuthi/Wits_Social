package com.example.witssocial.Profile;


import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.R;

import com.example.witssocial.Utils.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfileActivity extends AppCompatActivity {

    TextView profilename, showBio, showUsername;
    Button follow_btn;

    //for follow
    FirebaseUser firebaseUser;
    String profileid;
    private TextView mFollowers, mFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = this.getSharedPreferences("PREFS", MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");

        mFollowers = (TextView) findViewById(R.id.tvFollowers);
        mFollowing = (TextView) findViewById(R.id.tvFollowing);
       // follow_btn = (Button) findViewById(R.id.btn_follow);

        getFollowers();
        checkFollow();

        //Get Data from a post
        ///String username = getIntent().getStringExtra("Username");

        // showing the back button in action bar
        //Add username as title
       // Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
       // setSupportActionBar(myToolbar);



        ActionBar actionBar = getSupportActionBar();
       // assert actionBar != null;
        actionBar.setTitle("");   //PLEASE LEAVE IT EMPTY!!! WE ALREADY GOT THE USERNAME!!!
        actionBar.setDisplayHomeAsUpEnabled(true);


        showUsername = (TextView) findViewById(R.id.display_name);
        showUsername.setText(getIntent().getStringExtra("Username"));

        showBio = (TextView) findViewById(R.id.bio);
        showBio.setText("Hey there! This is "+getIntent().getStringExtra("Username") + "'s bio.");


        //follow_btn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        if(follow_btn.getText()=="Following") follow_btn.setText("Follow");
        //        else follow_btn.setText("Following");
        //    }
        //} );


        //follow_btn = (Button) findViewById(R.id.btn_follow);

        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btn = follow_btn.getText().toString();

                if (btn.equals("follow")){

                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                            .child("following").child(profileid).setValue(true);
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(profileid)
                            .child("followers").child(firebaseUser.getUid()).setValue(true);
                   // addNotification();
                } else if (btn.equals("following")){

                    FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                            .child("following").child(profileid).removeValue();
                    FirebaseDatabase.getInstance().getReference().child("Follow").child(profileid)
                            .child("followers").child(firebaseUser.getUid()).removeValue();

                }
            }
        });



        //TODO:
        // ADD User Details : Bio, Names, DOB:
        // Add User Posts  and any other relevant info


    }

    private void checkFollow(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(profileid).exists()){
                    follow_btn.setText("following");
                } else{
                    follow_btn.setText("follow");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getFollowers(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Follow").child(profileid).child("followers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mFollowers.setText(""+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Follow").child(profileid).child("following");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mFollowing.setText(""+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}