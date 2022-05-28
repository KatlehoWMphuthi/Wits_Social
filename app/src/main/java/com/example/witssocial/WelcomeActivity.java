package com.example.witssocial;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.witssocial.Authentication.SignInActivity;
import com.example.witssocial.Authentication.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {
//ConstraintLayout Background = (ConstraintLayout)findViewById(R.id.background);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ConstraintLayout Background = (ConstraintLayout)findViewById(R.id.background);

        //Control what happens when DarkMode is turned on
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                Background.setBackgroundColor(Color.GREEN);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                Background.setBackgroundColor(Color.YELLOW);
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