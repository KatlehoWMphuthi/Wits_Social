package com.example.witssocial;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Authentication.SignUpActivity;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Fetches elements from XML
        ConstraintLayout Background = (ConstraintLayout)findViewById(R.id.background);
        ImageView Logo = findViewById(R.id.imageView);
        MaterialButton WelcomeSignIn = findViewById(R.id.sign_in_text_button);
        MaterialButton WelcomeSignUp = findViewById(R.id.sign_up_text_button);


        //Control what happens when DarkMode is turned on
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                Background.setBackgroundColor(Color.BLACK);
                WelcomeSignIn.setBackgroundColor(Color.WHITE);
                WelcomeSignIn.setTextColor(Color.BLACK);
                WelcomeSignUp.setTextColor(Color.WHITE);
                WelcomeSignUp.setStrokeColorResource(R.color.white);
                break;
        }


    }

    public void goToSignUpActivity(View view) {

        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void goToSignInActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
    }

}