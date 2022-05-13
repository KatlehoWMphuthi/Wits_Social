package com.example.witssocial;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witssocial.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragmentActivity";

    FirebaseDatabase database;
    DatabaseReference referenceToUser,users,reference1;
    FirebaseUser user;

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postLists;

    private  List<String> allUsers;


    private FragmentHomeBinding viewBinding;

    String username;
    //private static ArrayList<String> images;

    private FirebaseAuth mFirebaseAuth;
    private static Context mContext;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    //private FragmentHomeBinding viewBinding;
    //private FirebaseAuth mFirebaseAuth;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //viewBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postLists = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(),postLists);

       // readPosts();
        return view;

    }

/*
    public View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup containerr, @Nullable Bundle savedInstanceState) {
//        viewBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        View view =  inflater.inflate(R.layout.fragment_home, containerr, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        mContext = getContext();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // username = "Michael";//user.getUid();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // fetchImages();
            }
        });

        return  view;

    }

*/

/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    /*
        viewBinding.toolbar.inflateMenu(R.menu.home_fragment_menu);

        viewBinding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.logout_item:
                    FragmentTransaction fragmentTransaction1 = getParentFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fragment_container, new SignOutFragment());
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.commit();
                    return true;
                default:
                    return false;
            }
        });



    }
*/

    public void getRootNodes(){
        reference1 = FirebaseDatabase.getInstance().getReference();
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String rootNodeChild = snapshot1.getKey();
                    if(rootNodeChild.length() > 11){
                        referenceToUser = FirebaseDatabase.getInstance().getReference(rootNodeChild);
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void readPosts(){
        //Toast.makeText(getActivity(), "reading Posts",
        //        Toast.LENGTH_SHORT).show();

        referenceToUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




    public  void fetchImages()
    {



//            rImage = findViewById(R.id.imageView);
//        username = findViewById(R.id.textView);

        // we will get the default FirebaseDatabase instance
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // we will get a DatabaseReference for the database root node
        DatabaseReference databaseReference = firebaseDatabase.getReference("User_post1614");
        DatabaseReference getImage = databaseReference.child("image");

        // this listener will triggered once
        // with the value of the data at the location

        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // getting a DataSnapshot for the location at the specified
                // relative path and getting in the link variable
                String photos = dataSnapshot.getValue(String.class);

                //String link = photos.getImage();

                // loading that data into rImage
                // variable which is ImageViewll;
                // images.add(photos);
                /*HelperAdapter helperAdapter = new HelperAdapter(mContext,images,username);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(helperAdapter);*/
//                Picasso.get().load(photos).into(rImage);

            }

            // this will called when any problem
            // occurs in getting data
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // we are showing that error message in toast
//                Toast.makeText(ShowActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        DatabaseReference getName = databaseReference.child("username");

        getName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
//                username.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}