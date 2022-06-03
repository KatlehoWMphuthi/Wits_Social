package com.example.witssocial.More.MoreOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.witssocial.R;

public class AppSettingsActivity extends AppCompatActivity {
    private UiModeManager uiModeManager;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);

//        Button darkmodeON = (Button) findViewById(R.id.button);
//        Button darkmodeOFF = (Button) findViewById(R.id.button2);
//
//        darkmodeON.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NightModeON(v);
//            }
//        });
//
//        darkmodeOFF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NightModeOFF(v);
//            }
//        });

        aSwitch =findViewById(R.id.Switch);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


    }

//    public void NightModeON(View view){
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//    }
//
//    public void NightModeOFF(View view){
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//    }
}