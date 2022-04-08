package com.example.witssocial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void loginPage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void registerOTP(View view) {
        Intent intent = new Intent(this, RegisterOTP.class);
        startActivity(intent);
    }
}