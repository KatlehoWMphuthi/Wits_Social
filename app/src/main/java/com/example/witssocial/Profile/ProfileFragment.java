package com.example.witssocial.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.witssocial.Model.User;
import com.example.witssocial.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class ProfileFragment extends Fragment {
    TextView profilename,biography,fullName;
    ImageView profilepic,picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        profilepic = view.findViewById(R.id.image_profile);
        profilename = view.findViewById(R.id.username);
        biography = view.findViewById(R.id.bio);
        fullName = view.findViewById(R.id.fullname);
        picture = view.findViewById(R.id.imageView4);

        //Do something from here

        return view;
    }

}