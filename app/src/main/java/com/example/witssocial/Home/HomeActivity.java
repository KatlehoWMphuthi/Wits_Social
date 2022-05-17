package com.example.witssocial.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.witssocial.CreatePost.PostActivity;
import com.example.witssocial.More.MoreOptionsActivity;
import com.example.witssocial.Notification.NotificationFragment;
import com.example.witssocial.R;
import com.example.witssocial.Search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_home :
                        selectorFragment = new HomeFragment();
                        break;

                    case R.id.nav_search :
                        selectorFragment = new SearchFragment();
                        break;

                    case R.id.nav_add :
                        selectorFragment = null;
                        startActivity(new Intent(HomeActivity.this , PostActivity.class));
                        break;

                    /*case R.id.nav_add :
                        selectorFragment = null;
                        startActivity(new Intent(MainActivity.this , PostActivity.class));
                        break;*/

                    case R.id.nav_notifications:
                        selectorFragment = new NotificationFragment();
                        break;

                    case R.id.nav_more:
                        //selectorFragment = new ProfileFragment();
                        selectorFragment = null;
                        startActivity(new Intent(HomeActivity.this , MoreOptionsActivity.class));
                        break;
                }

                if (selectorFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectorFragment).commit();
                }

                return  true;

            }
        });

        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            String profileId = intent.getString("publisherId");

            getSharedPreferences("PROFILE", MODE_PRIVATE).edit().putString("profileId", profileId).apply();

           // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_more);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment()).commit();
        }
    }
}