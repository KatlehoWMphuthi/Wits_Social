package com.example.witssocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // implent splash screen
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