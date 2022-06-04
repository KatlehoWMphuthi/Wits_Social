package com.example.witssocial.More.MoreOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.witssocial.Authentication.ForgotPasswordActivity;
import com.example.witssocial.Profile.UserProfileActivity;
import com.example.witssocial.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        TextView profilepage  = (TextView) findViewById(R.id.profileActivity);
        TextView updatePassword  = (TextView) findViewById(R.id.forgotPassActivity);

        profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, UserProfileActivity.class));
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(AccountActivity.this, ForgotPasswordActivity.class));
              
            }
        });
    }
}