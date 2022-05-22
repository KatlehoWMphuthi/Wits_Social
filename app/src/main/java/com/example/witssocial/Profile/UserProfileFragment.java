package com.example.witssocial.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witssocial.Model.Post;
import com.example.witssocial.R;
import com.example.witssocial.Utils.PostAdapter;
import com.example.witssocial.Utils.PostRecyclerViewInterface;
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


public class UserProfileFragment extends Fragment implements PostRecyclerViewInterface{

    private TextView mPosts , mFollowers, mFollowing, mDisplayName, mUsername, mBio;
    private Button mEditProfile;
    private ProgressBar mProgressBar;
    private CircleImageView mProfilePhoto;
    private Toolbar toolbar;
    private Chip mWebsite;

    RecyclerView recyclerView;
    PostAdapter postAdapter;
    ArrayList<Post> list;

    DatabaseReference postsRef,userRef;

    //for follow
    FirebaseUser firebaseUser;
    String profileid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");

        mDisplayName = (TextView) view.findViewById(R.id.display_name);
        //mUsername = (TextView) view.findViewById(R.id.username);
        mBio = (TextView) view.findViewById(R.id.description);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_image);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_profileProgressBar);
        mWebsite = (Chip) view.findViewById(R.id.chip_1);
        mFollowers = (TextView) view.findViewById(R.id.tvFollowers);
        mFollowing = (TextView) view.findViewById(R.id.tvFollowing);
        mPosts = (TextView) view.findViewById(R.id.tvPosts) ;

        getFollowers();

        //Handel the ProgressBar
        mProgressBar = view.findViewById(R.id.pb_profileProgressBar);
        mProgressBar.setVisibility(View.GONE);

        //set Up edit button to go to edit profile fragment
       mEditProfile = view.findViewById(R.id.btn_edit_profile);

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.user_profile_container, new EditProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String userid = CurrentUser.getUid();

        postsRef = database.getReference("Posts");
        userRef = database.getReference("Users");

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

        //get fullname
        getInfo("fullname",userRef.child(userid),mDisplayName);
        getInfo("bio",userRef.child(userid),mBio);
        getInfo("website",userRef.child(userid).child("socials"),mWebsite);

        //TODO -- Set Profile Photo using picasso
       // String url = "https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=20&m=1223671392&s=612x612&w=0&h=lGpj2vWAI3WUT1JeJWm1PRoHT3V15_1pdcTn2szdwQ0=";
       // Picasso.get().load(url).resize(100,100).centerCrop().into(mProfilePhoto);



        /*
        Display posts on users profile
         */

        recyclerView = view.findViewById(R.id.RecyclerView_user_profile);
        postsRef = FirebaseDatabase.getInstance().getReference("Posts");
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), list, this);
        recyclerView.setAdapter(postAdapter);

        DatabaseReference currentUser = userRef.child(userid);
        currentUser.child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue(String.class);
                postsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            // TODO -- Filter the Posts to show one for the user only ,
                            //  The same code  can be used when clicking for
                            //  XML the scrolling is still buggy please fix it as well

                            Post post = dataSnapshot.getValue(Post.class);

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

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }

    /*
     Define on click action to go to a users profile
     */
    @Override
    public void onUsernameClick(int position) {


        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("Username", list.get(position).getUsername());

        startActivity(intent);

    }

    public void getInfo(String info,DatabaseReference infoRef, TextView infoTextView){
        DatabaseReference get_info = infoRef.child(info);
        get_info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String info_needed = snapshot.getValue(String.class);
                infoTextView.setText(info_needed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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