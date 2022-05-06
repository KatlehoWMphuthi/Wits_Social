package com.example.witssocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }


    public void goToSignUpActivity(View view) {

        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void goToSignInActivity(View view) {
        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
    }
}