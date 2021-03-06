package com.example.witssocial.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witssocial.Model.Post;
import com.example.witssocial.Profile.ProfileFragment;
import com.example.witssocial.Profile.UserProfileFragment;
import com.example.witssocial.R;
import com.example.witssocial.Utils.PostAdapter;
import com.example.witssocial.Utils.PostRecyclerViewInterface;
import com.facebook.shimmer.ShimmerFrameLayout;
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


public class HomeFragment extends Fragment implements PostRecyclerViewInterface {
    private static final String TAG = "HomeFragmentActivity";

    DatabaseReference database, postsRef,userRef,getProfilePicture;
    ImageView profilePicture;
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmerContainer;
    PostAdapter postAdapter;
    ArrayList<Post> list;
    String userid,currentUserName;


//    public HomeFragment() {
//        // Required empty public constructor
//    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();

        profilePicture = view.findViewById(R.id.iv_home_profile_picture);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileFragment userProfileFragment = new UserProfileFragment();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, userProfileFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

       //Set profile picture on the tool bar
        setProfilePicture(profilePicture);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.GONE);
        database = FirebaseDatabase.getInstance().getReference("Posts");
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), list, this);
        recyclerView.setAdapter(postAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shimmerContainer.stopShimmer();
                shimmerContainer.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Post post = dataSnapshot.getValue(Post.class);
                    String postid = dataSnapshot.getKey();
                    String image  = dataSnapshot.child("image").getValue(String.class);
                    String caption  = dataSnapshot.child("Caption").getValue(String.class);
                    String username  = dataSnapshot.child("username").getValue(String.class);



                    Post post = new Post(postid,image,caption,username);

                    /*TODO : @Michael this is line to get the actual post
                    You can implement the like button here.type java.lang.String to long
                  */

                    if(dataSnapshot.child("time").getValue() != null){
                        if(dataSnapshot.child("time").getValue() instanceof Long){
                            long timeINT = dataSnapshot.child("time").getValue(long.class);//Long.parseLong(timestamp);
                            //String time = getDate(timeINT);
                            post.setTime(timeINT);
                        }
                        else{
                            String timestamp = dataSnapshot.child("time").getValue(String.class);
                            long timeINT =Long.parseLong(timestamp);
                            //String time = getDate(timeINT);
                            post.setTime(timeINT);
                            }
                        }
                    list.add(post);
                }

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }

    private void setProfilePicture(View ProfilePhoto) {

        //Get and set profile picture on the tool bar
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        postsRef = database.getReference("Posts");
        userRef = database.getReference("Users");

        // getting the user's unique id in Database
        if (CurrentUser != null){
            userid = CurrentUser.getUid();

            getProfilePicture = userRef.child(userid).child("imageurl");

            getProfilePicture.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String url = snapshot.getValue(String.class);
                    Picasso.get().load(url).resize(100,100).centerCrop().into((ImageView) ProfilePhoto);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }



    /*
     Define on click action to go to a users profile
     */
    @Override
    public void onUsernameClick(int position) {
        //send username to profile fragment
        //Set up a bundle to carry
        Bundle bundle = new Bundle();
        String username = list.get(position).getUsername();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("username")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            currentUserName = snapshot.getValue(String.class);
                            if(username.equals(currentUserName)){
                                UserProfileFragment userProfileFragment = new UserProfileFragment();

                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, userProfileFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                            else{
                                bundle.putString("username", username);

                                //send data
                                ProfileFragment profileFragment = new ProfileFragment();
                                profileFragment.setArguments(bundle);

                                //Open ProfileFragement
                                //FragmentManager fragmentManager = getParentFragmentManager();
                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, profileFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}