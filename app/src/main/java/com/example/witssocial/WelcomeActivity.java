package com.example.witssocial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Authentication.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        
    }
    public void goToSignUpActivity(View view) {

        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void goToSignInActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
    }
}