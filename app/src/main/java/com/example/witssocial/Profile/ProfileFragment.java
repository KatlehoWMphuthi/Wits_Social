package com.example.witssocial.Profile;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;

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

import java.util.ArrayList;

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
    /*TextView profilename,biography,fullName;
    ImageView profilepic,picture;
*/

    //for follow
    FirebaseUser firebaseUser;
    String profileid;
    private TextView mFollowers, mFollowing;
    Button follow_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        viewBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = viewBinding.getRoot();

        //mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_image);

        //follow additions
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");

        mFollowers = view.findViewById(R.id.tvFollowers);
        mFollowing = view.findViewById(R.id.tvFollowing);
        follow_btn = view.findViewById(R.id.btn_follows);
        mPosts = view.findViewById(R.id.tvPosts);

        getFollowers();
        //checkFollow();

        if (profileid.equals(firebaseUser.getUid())){
            follow_btn.setText("Edit Profile");
        } else {
            checkFollow();
        }

        //Hide progree bar
        viewBinding.pbProfileProgressBar.setVisibility(GONE);


        //ActionBar actionBar = getSupportActionBar();
        // assert actionBar != null;
        //actionBar.setTitle("");   //PLEASE LEAVE IT EMPTY!!! WE ALREADY GOT THE USERNAME!!!
        //actionBar.setDisplayHomeAsUpEnabled(true);


        //Collect Data from Parent activity
        Bundle bundle = this.getArguments();
        username = bundle.getString("username");

        //Bind data to views
       mDisplayName = viewBinding.displayName;
       mBio = viewBinding.bio;
       mWebsite = viewBinding.chip1;
       mProfilePhoto  = viewBinding.profileImage;


       mDisplayName.setText(username);

        //viewBinding.displayName.setText(username);
/*
        profilepic = view.findViewById(R.id.image_profile);
        profilename = view.findViewById(R.id.username);
        //biography = view.findViewById(R.id.bio);
        fullName = view.findViewById(R.id.fullname);
        picture = view.findViewById(R.id.imageView4);
*/
        //Do something from here

        /*
        Getting data from the db and displaying it on users profile page
         */

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String userid = CurrentUser.getUid();

        //follow additions
        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btn = follow_btn.getText().toString();

                if (btn.equals("Edit Profile")) {

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new EditProfileFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();


                    DatabaseReference getProfilePicture = userRef.child(userid).child("imageurl");

                    getProfilePicture.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String url = snapshot.getValue(String.class);
                            Picasso.get().load(url).resize(100,100).centerCrop().into(mProfilePhoto);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else if (btn.equals("follow")){

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



        postsRef = database.getReference("Posts");
        userRef = database.getReference("Users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageurl = "";
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String user_name = bundle.getString("username");
                    //check if user profile clicked
                     if(user_name.equals(dataSnapshot.child("username").getValue(String.class))){


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




                        // Toast.makeText(getActivity(), mDisplayName.toString(), Toast.LENGTH_LONG);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //TODO -- Set Profile Photo using picasso
        // String url = "https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=20&m=1223671392&s=612x612&w=0&h=lGpj2vWAI3WUT1JeJWm1PRoHT3V15_1pdcTn2szdwQ0=";
        // Picasso.get().load(url).resize(100,100).centerCrop().into(mProfilePhoto);



        /*
        Display posts on users profile
         */

        recyclerView = viewBinding.RecyclerViewUserProfile;
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
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            // TODO -- Filter the Posts to show one for the user only ,
                            //  The same code  can be used when clicking for
                            //  XML the scrolling is still buggy please fix it as well

                            Post post = dataSnapshot.getValue(Post.class);

                            if(post.getUsername() != null) {
                                if (post.getUsername().equals(username)) {
                                    list.add(post);
                                }
                            }

                            mPosts.setText(Integer.toString(list.size()));

                        }

                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public void onUsernameClick(int position) {

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