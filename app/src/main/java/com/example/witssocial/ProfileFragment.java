package com.example.witssocial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class ProfileFragment extends Fragment {
    TextView profilename,biography,fullName;
    ImageView profilepic,picture;
    FirebaseDatabase database;
    DatabaseReference reference,users,reference1;
    FirebaseUser user;
    String user_name;
    ArrayList<Integer> cheats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cheats = new ArrayList<Integer>();
        cheats.add(4908);
        cheats.add(4218);
        cheats.add(2461);
        cheats.add(5001);
        cheats.add(2354);
        cheats.add(3347);
        cheats.add(3244);
        cheats.add(3642);
        cheats.add(4443);
        cheats.add(5831);
        int number = randomPicker(cheats);



        profilepic = view.findViewById(R.id.image_profile);
        profilename = view.findViewById(R.id.username);
        biography = view.findViewById(R.id.bio);
        fullName = view.findViewById(R.id.fullname);
        picture = view.findViewById(R.id.imageView4);
        database = FirebaseDatabase.getInstance();
        fullName.setText("This is my name");
        reference = database.getReference("User_post"+Integer.toString(number));
        users = database.getReference("Users");
        DatabaseReference getProPic = reference.child("image");
        getProPic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Glide.with(getContext()).load(link).into(profilepic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User users = snapshot.getValue(User.class);
                profilename.setText(users.getUsername());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference getBio= reference.child("Caption");
        getBio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String hey = snapshot.getValue(String.class);
                biography.setText(hey);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference getPicture = reference.child("image");

        getPicture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);

                Glide.with(getContext()).load(link).into(picture);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        return view;
    }
    public String getusername(String userid){
        users.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                user_name = user1.getUsername();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return user_name;
    }

    public int randomPicker(ArrayList<Integer> list){
        int number;

        Random random = new Random();
        int index = random.nextInt(list.size());
        number = list.get(index);

        return number;
    }
}