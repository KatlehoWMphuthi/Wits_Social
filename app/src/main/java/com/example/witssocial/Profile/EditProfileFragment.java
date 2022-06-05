package com.example.witssocial.Profile;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.witssocial.R;
import com.example.witssocial.databinding.FragmentEditProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileFragment extends Fragment {

    private static final String LOG_TAG = EditProfileFragment.class.getSimpleName();

    TextView mChangePhoto;
    EditText name,bio,website, facebook,twitter,instagram,linkedin;
    CircleImageView mcircleImageView;
    Button mSaveChanges;
    private  Uri imageUri;
    private ArrayList<String> social_links;

    DatabaseReference link,  postsRef,userRef;

    //set up view binding
    private FragmentEditProfileBinding binding;

    ActivityResultLauncher<String> mGetContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        binding = FragmentEditProfileBinding.bind(view);

        //Get views
        mChangePhoto = view.findViewById(R.id.tv_change_profile_photo);
        mcircleImageView = view.findViewById(R.id.imageview_edit_profile_photo);
        mSaveChanges = view.findViewById(R.id.btn_edit_profile_save);
        name  = view.findViewById(R.id.name);
        bio = view.findViewById(R.id.biography);
        website = view.findViewById(R.id.website);
        facebook =view.findViewById(R.id.facebook);
        twitter = view.findViewById(R.id.twitter);
        instagram = view.findViewById(R.id.instagram);
        linkedin =view.findViewById(R.id.linkedin);

        //Set current user profile picture
        setCurrentProfilePicture(mcircleImageView);
        showAllUserData();


        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageUri = result;
                mcircleImageView.setImageURI(result);
            }
        });


        //Click to open gallery
        mChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });


        //Save Changes
        mSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(LOG_TAG, "Saving Data");
                // TODO -- Push to firebase : imageURI
                //get the current user
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                // get the firebase database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //get the storage instance
                FirebaseStorage storage = FirebaseStorage.getInstance();

                if(imageUri != null){
                    // get the reference to where the profile pictures will be stored
                    StorageReference users_info = storage.getReference();
                    StorageReference profile_picture = users_info.child("UserProfile/Profile_Pic/" + imageUri.getLastPathSegment());


                    //Create an upload task to push to the storage on Firebase
                    UploadTask upload_profile_picture = profile_picture.putFile(imageUri);

                    // get the id of the current user
                    String id = firebaseUser.getUid();
                    upload_profile_picture.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //do something when upload is successful
                            Toast.makeText(getActivity(),"Profile updated", Toast.LENGTH_LONG).show();

                            //Get a download url that will stored in the database
                            profile_picture.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    //gets the url
                                    String profile_picURL = task.getResult().toString();
                                    // use database reference to save or update user information
                                    DatabaseReference userRef = database.getReference("Users").child(id);

                                    //update  the name
                                    DatabaseReference nameRef = userRef.child("fullname");
                                    String full_name = name.getText().toString();

                                    if(!full_name.isEmpty()){
                                        nameRef.setValue(full_name);
                                    }
                                    //update the bio
                                    DatabaseReference bioRef  = userRef.child("bio");
                                    String biography = bio.getText().toString();
                                    if(!biography.isEmpty()){
                                        bioRef.setValue(biography);
                                    }
                                    //add the social links
                                    DatabaseReference socials = userRef.child("socials");
                                    social_links =new ArrayList<>();
                                    social_links.add(website.getText().toString());
                                    social_links.add(facebook.getText().toString());
                                    social_links.add(twitter.getText().toString());
                                    social_links.add(instagram.getText().toString());
                                    social_links.add(linkedin.getText().toString());

                                    if(!social_links.isEmpty()){


                                        if(!(website.getText().toString()).isEmpty()){
                                            addToDatabase(link,"website",socials,website.getText().toString());
                                        }
                                        if(!(facebook.getText().toString()).isEmpty()){
                                            addToDatabase(link,"facebook",socials,facebook.getText().toString());
                                        }
                                        if(!twitter.getText().toString().isEmpty()){
                                            addToDatabase(link,"twitter",socials,twitter.getText().toString());
                                        }
                                        if(!instagram.getText().toString().isEmpty()){
                                            addToDatabase(link,"instagram",socials,instagram.getText().toString());
                                        }if (!linkedin.getText().toString().isEmpty()){
                                            addToDatabase(link,"linkedin",socials,linkedin.getText().toString());
                                        }
                                    }

                                    //update the user's profile picture
                                    DatabaseReference profilepic = userRef.child("imageurl");

                                    profilepic.setValue(profile_picURL);

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //do something when upload is not successful
                                    Toast.makeText(getActivity(),"Failed to Upload", Toast.LENGTH_LONG).show();
                                }
                            });
                }else if(imageUri == null){
                    Toast.makeText(getActivity(),"Profile updated", Toast.LENGTH_LONG).show();
                    // use database reference to save or update user information
                    if(firebaseUser != null ){
                        DatabaseReference userRef = database.getReference("Users").child(firebaseUser.getUid());

                        //update  the name
                        DatabaseReference nameRef = userRef.child("fullname");
                        String full_name = name.getText().toString();

                        if(!full_name.isEmpty()){
                            nameRef.setValue(full_name);
                        }
                        //update the bio
                        DatabaseReference bioRef  = userRef.child("bio");
                        String biography = bio.getText().toString();
                        if(!biography.isEmpty()){
                            bioRef.setValue(biography);
                        }
                        //add the social links
                        DatabaseReference socials = userRef.child("socials");
                        social_links =new ArrayList<>();
                        social_links.add(website.getText().toString());
                        social_links.add(facebook.getText().toString());
                        social_links.add(twitter.getText().toString());
                        social_links.add(instagram.getText().toString());
                        social_links.add(linkedin.getText().toString());

                        if(!social_links.isEmpty()){


                            if(!(website.getText().toString()).isEmpty()){
                                addToDatabase(link,"website",socials,website.getText().toString());
                            }
                            if(!(facebook.getText().toString()).isEmpty()){
                                addToDatabase(link,"facebook",socials,facebook.getText().toString());
                            }
                            if(!twitter.getText().toString().isEmpty()){
                                addToDatabase(link,"twitter",socials,twitter.getText().toString());
                            }
                            if(!instagram.getText().toString().isEmpty()){
                                addToDatabase(link,"instagram",socials,instagram.getText().toString());
                            }if (!linkedin.getText().toString().isEmpty()){
                                addToDatabase(link,"linkedin",socials,linkedin.getText().toString());
                            }
                        }
                    }

                    UserProfileFragment userProfileFragment = new UserProfileFragment();

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, userProfileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Failed To Update Profile", Toast.LENGTH_LONG).show();
                }



            }



        });

        return view;
    }

    private void showAllUserData() {
        //Get data from a bundle and display

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //handle back button
        binding.editProfileToolbar.setNavigationIcon(R.drawable.ic_back);
        binding.editProfileToolbar.setTitle("Edit Profile");
        binding.editProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setCurrentProfilePicture(CircleImageView mcircleImageView) {

            //Get and set profile picture on the tool bar
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(CurrentUser != null){
                String userid = CurrentUser.getUid();

                postsRef = database.getReference("Posts");
                userRef = database.getReference("Users");

                DatabaseReference getProfilePicture = userRef.child(userid).child("imageurl");

                getProfilePicture.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String url = snapshot.getValue(String.class);
                        Picasso.get().load(url).resize(100,100).centerCrop().into((ImageView) mcircleImageView);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


    }

    //Add to database

    public void addToDatabase(DatabaseReference link1,String a,DatabaseReference link2,String b){
        link1 = link2.child(a);
        link1.setValue(b);
    }

}