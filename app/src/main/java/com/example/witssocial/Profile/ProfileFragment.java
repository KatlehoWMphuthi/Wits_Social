package com.example.witssocial.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witssocial.Home.HomeFragment;
import com.example.witssocial.Model.Post;
import com.example.witssocial.Model.Social;
import com.example.witssocial.Model.User;
import com.example.witssocial.R;
import com.example.witssocial.Utils.PostAdapter;
import com.example.witssocial.Utils.PostRecyclerViewInterface;
import com.example.witssocial.databinding.FragmentProfileBinding;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements PostRecyclerViewInterface {
    private FragmentProfileBinding viewBinding;
    private String username;
    DatabaseReference postsRef,userRef;
    private CircleImageView mProfilePhoto;

    RecyclerView recyclerView;
    PostAdapter postAdapter;
    ArrayList<Post> list;
    private Chip mWebsite;
    private TextView mPosts , mDisplayName, mUsername, mBio;

    //for follow
    FirebaseUser firebaseUser;
    String profileid,home_profileid;
    private TextView mFollowers, mFollowing;
    Button follow_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        viewBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = viewBinding.getRoot();


        //follow additions
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");

        mFollowers = view.findViewById(R.id.tvFollowers);
        mFollowing = view.findViewById(R.id.tvFollowing);
        follow_btn = view.findViewById(R.id.btn_follow);
        mPosts = view.findViewById(R.id.tvPosts);


        //Collect Data from Parent activity
        Bundle bundle = this.getArguments();
        if(bundle != null){
            username = bundle.getString("username");
            profileid = bundle.getString("profileid");

        }

        //Bind data to views
       mDisplayName = viewBinding.displayName;
       mBio = viewBinding.bio;
       mWebsite = viewBinding.chip1;
       mProfilePhoto  = viewBinding.profileImage;

       mDisplayName.setText(username);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //follow additions

        postsRef = database.getReference("Posts");
        userRef = database.getReference("Users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageurl = "";
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(bundle != null){
                        String user_name = bundle.getString("username");
                        //check if user profile clicked
                        if(dataSnapshot.child("username").getValue(String.class) != null){
                            if (dataSnapshot.child("username").getValue() instanceof String){
                                if(user_name.equals(dataSnapshot.child("username").getValue(String.class))){
                                    checkFollow(dataSnapshot.getKey());
                                    getFollowers(dataSnapshot.getKey());

                                    User info = dataSnapshot.getValue(User.class);

                                    imageurl = info.getImageurl();
                                    if(imageurl != null){
                                        Picasso.get().load(imageurl).resize(100,100).centerCrop().into(mProfilePhoto);
                                    }

                                    String fullName = info.getFullname();

                                    if(fullName != null){
                                        mDisplayName.setText(fullName);
                                    }
                                    String bio = info.getBio();
                                    if(fullName != null){
                                        mBio.setText(bio);
                                    }

                                    DataSnapshot socials = dataSnapshot.child("socials");
                                    Social userSocials = socials.getValue(Social.class);

                                    if(userSocials != null){
                                        if(userSocials.getFacebook() != null){
                                            mWebsite.setText(userSocials.getFacebook());
                                        }
                                        else if(userSocials.getWebsite() != null){
                                            Context context = mWebsite.getContext();
                                            mWebsite.setText(userSocials.getWebsite());
                                        }else if(userSocials.getInstagram() != null){
                                            mWebsite.setText(userSocials.getInstagram());
                                        }else if(userSocials.getLinkedin() != null){
                                            mWebsite.setText(userSocials.getLinkedin());
                                        }else if(userSocials.getTwitter() != null){
                                            mWebsite.setText(userSocials.getTwitter());
                                        }
                                        else{
                                            mWebsite.setText("My links");
                                        }
                                    }
                                     home_profileid = dataSnapshot.getKey();

                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        /*
        Display posts on users profile
         */

        recyclerView = viewBinding.RecyclerViewUserProfile;
        recyclerView.setVisibility(View.GONE);
        postsRef = FirebaseDatabase.getInstance().getReference("Posts");
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), list, this);
        recyclerView.setAdapter(postAdapter);

        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        recyclerView.setVisibility(View.VISIBLE);
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            // TODO -- Filter the Posts to show one for the user only ,
                            //  The same code  can be used when clicking for
                            //  XML the scrolling is still buggy please fix it as well

                            //Post post = dataSnapshot.getValue(Post.class);

                            String postid = dataSnapshot.getKey();
                            String image  = dataSnapshot.child("image").getValue(String.class);
                            String caption  = dataSnapshot.child("Caption").getValue(String.class);
                            String username1  = dataSnapshot.child("username").getValue(String.class);



                            Post post = new Post(postid,image,caption,username1);

                            if(dataSnapshot.child("time").getValue() != null){
                                if(dataSnapshot.child("time").getValue() instanceof Long){
                                    long timeINT = dataSnapshot.child("time").getValue(long.class);//Long.parseLong(timestamp);

                                    post.setTime(timeINT);
                                }
                                else{
                                    String timestamp = dataSnapshot.child("time").getValue(String.class);
                                    long timeINT =Long.parseLong(timestamp);
                                    post.setTime(timeINT);
                                }
                            }

                            if(post.getUsername() != null){
                                if(post.getUsername().equals(username)){
                                    list.add(post);
                                }
                            }
                            mPosts.setText(Integer.toString(list.size()));
                        }

                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        // follow button function logic
        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btn = follow_btn.getText().toString();

                if (btn.equals("follow")){
                    followHelper(home_profileid);
                    follow_btn.setText("following");
                    // addNotification();
                }

                if (btn.equals("following")){
                    unfollowHelper(home_profileid);
                    follow_btn.setText("follow");
                }
            }
        });



        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Toast.makeText(getActivity(),"hi", Toast.LENGTH_LONG);
      //  Toolbar toolbar = view.findViewById(R.id.view_profile_toolbar);

        //bind views and set back navigation icon
        viewBinding.viewProfileToolbar.setNavigationIcon(R.drawable.ic_back);
        viewBinding.viewProfileToolbar.setTitle(username);
        viewBinding.viewProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to home fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new HomeFragment());
                transaction.commit();
            }
        });

    }


    @Override
    public void onUsernameClick(int position) { }

    private void checkFollow(String profileid){
        DatabaseReference followRef = FirebaseDatabase.getInstance().getReference("Follow");
        DatabaseReference FollowUser = followRef.child(profileid);
        DatabaseReference followers = FollowUser.child("followers");
        followers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    if(snapshot.child(firebaseUser.getUid()).exists()){
                        follow_btn.setText("following");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getFollowers(String profileid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Follow").child(profileid).child("followers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mFollowers.setText(Long.toString(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Follow").child(profileid).child("following");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mFollowing.setText(Long.toString(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void followHelper(String profileid){
        FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                .child("following").child(profileid).setValue(true);
        FirebaseDatabase.getInstance().getReference().child("Follow").child(profileid)
                .child("followers").child(firebaseUser.getUid()).setValue(true);
    }

    private void unfollowHelper(String profileid){
        FirebaseDatabase.getInstance().getReference().child("Follow").child(firebaseUser.getUid())
                .child("following").child(profileid).removeValue();
        FirebaseDatabase.getInstance().getReference().child("Follow").child(profileid)
                .child("followers").child(firebaseUser.getUid()).removeValue();
    }

}