package com.example.witssocial.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.witssocial.R;
import com.example.witssocial.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up app to display screen endge to edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // implement splash screen
        Thread displayLogo = new Thread ()
        {
            public void run() {
                try {
                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally
                {
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                }
            }
        };
        displayLogo.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}