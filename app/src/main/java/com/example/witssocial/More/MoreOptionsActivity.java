package com.example.witssocial.More;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.witssocial.More.MoreOptions.AboutActivity;
import com.example.witssocial.More.MoreOptions.AccountActivity;
import com.example.witssocial.More.MoreOptions.AppSettingsActivity;
import com.example.witssocial.More.MoreOptions.FriendInvitationActivity;
import com.example.witssocial.More.MoreOptions.HelpAndSupportActivity;
import com.example.witssocial.More.MoreOptions.NotificationsActivity;
import com.example.witssocial.R;
import com.example.witssocial.SignOut.SignOutFragment;
import com.example.witssocial.dialogs.SignOutDialog;

import java.util.ArrayList;
import java.util.List;

public class MoreOptionsActivity extends AppCompatActivity implements SignOutDialog.SignOutDialogListener {
    private static final String TAG = "More Actions Activity";
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);



        //Signout button
        signOutButton = (Button) findViewById(R.id.btnSignout);

        // showing the back button in action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("More");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.listviwe);

        List<String> list = new ArrayList<>();
        list.add("App Settings");
        list.add("Account");
        list.add("Notifications");
        list.add("Help and Support");
        list.add("Invite a friend");
        list.add("About");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //clicked App Settings
                    startActivity(new Intent(MoreOptionsActivity.this, AppSettingsActivity.class));

                }else if(position==1){
                    //clicked Account
                    startActivity(new Intent(MoreOptionsActivity.this, AccountActivity.class));

                }else if(position==2) {
                    //clicked Notifications
                    startActivity(new Intent(MoreOptionsActivity.this, NotificationsActivity.class));
                }
                else if(position==3) {
                    //clicked Help and Support
                    startActivity(new Intent(MoreOptionsActivity.this, HelpAndSupportActivity.class));
                }
                else if(position==4) {
                    //clicked Invite a friend
                    startActivity(new Intent(MoreOptionsActivity.this, FriendInvitationActivity.class));
                }
                else{
                    // About
                    startActivity(new Intent(MoreOptionsActivity.this, AboutActivity.class));
                }
            }
        });

        //populate list to list view

//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(arrayAdapter);


        //Sign out button
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        SignOutDialog dialog = new SignOutDialog();
        dialog.show(getSupportFragmentManager(),"example");
    }

    @Override
    public void onYesClicked() {
        //go to sign out Fragment
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.more_options_container, new SignOutFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}