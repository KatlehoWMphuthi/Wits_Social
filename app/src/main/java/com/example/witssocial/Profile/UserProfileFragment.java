package com.example.witssocial.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends Fragment implements PostRecyclerViewInterface{

    private TextView mPosts , mFollowers, mFollowing, mDisplayName, mUsername, mBio;
    private Button mEditProfile;
    private ProgressBar mProgressBar;
    private CircleImageView mProfilePhoto;
    private Toolbar toolbar;

    DatabaseReference database;
    ImageView profilePicture;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    ArrayList<Post> list;




    //Firebase

   // FirebaseDatabase database;
   // DatabaseReference myRef,users;
    FirebaseUser user, userID;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        mDisplayName = (TextView) view.findViewById(R.id.display_name);
        mUsername = (TextView) view.findViewById(R.id.username);
        mBio = (TextView) view.findViewById(R.id.tv_bio);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_image);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_profileProgressBar);


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
                transaction.commit();
            }
        });

        //Firebase
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Posts");
        //users = database.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        //User id
       String userID = user.getUid();



        //Get username and profile picture info
       // String username = user.getDisplayName();
        //Uri profilePhoto = user.getPhotoUrl();

       // mDisplayName.setText(username);



        //TODO -- Set Profile Photo using picasso
       // String url = "https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=20&m=1223671392&s=612x612&w=0&h=lGpj2vWAI3WUT1JeJWm1PRoHT3V15_1pdcTn2szdwQ0=";
       // Picasso.get().load(url).resize(100,100).centerCrop().into(mProfilePhoto);



        /*
        Display posts on users profile
         */

        recyclerView = view.findViewById(R.id.RecyclerView_user_profile);
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
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    // TODO -- Filter the Posts to show one for the user only ,
                    //  The same code  can be used when clicking for
                    //  XML the scrolling is still buggy please fix it as well

                    Post post = dataSnapshot.getValue(Post.class);
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

    /*
     Define on click action to go to a users profile
     */
    @Override
    public void onUsernameClick(int position) {


        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("Username", list.get(position).getUsername());

        startActivity(intent);

    }
}