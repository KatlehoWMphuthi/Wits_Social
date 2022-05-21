package com.example.witssocial.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.witssocial.Home.HomeFragment;
import com.example.witssocial.R;
import com.example.witssocial.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding viewBinding;
    private String username;
    /*TextView profilename,biography,fullName;
    ImageView profilepic,picture;
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        viewBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = viewBinding.getRoot();

        //Collect Data from Parent activity
        Bundle bundle = this.getArguments();
        username = bundle.getString("username");


/*
        profilepic = view.findViewById(R.id.image_profile);
        profilename = view.findViewById(R.id.username);
        //biography = view.findViewById(R.id.bio);
        fullName = view.findViewById(R.id.fullname);
        picture = view.findViewById(R.id.imageView4);
*/
        //Do something from here


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}