package com.example.witssocial.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.witssocial.Home.HomeActivity;
import com.example.witssocial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {


    TextInputEditText email_SignIn;
    TextInputEditText password_signIn;
    TextInputLayout Password_layout,email_layout_s;
    Button SignIn;
    TextView forgot_password;
    private static final String TAG = "FragmentActivity";


    CheckBox mCheckboxRememberme;
    SharedPreferences mPrefs;
    static final  String PREFS_NAME = "";
    private FirebaseAuth mAuth;

    ProgressDialog loadBar;
    public static  String logged_in =" " ;
    //Intent intent = new Intent(this, PostFragment.class);
    //intent.putExtra("EMAIL_ID", email);
    //startActivity(intent);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_sign_in);

        email_SignIn = findViewById(R.id.stu_mail_login);
        password_signIn = findViewById(R.id.EditText_login_Password);
        Password_layout = findViewById(R.id.Password_layout);
        email_layout_s = findViewById(R.id.UserMail_layout_login);
        mAuth = FirebaseAuth.getInstance();
        SignIn = findViewById(R.id.LoginButton);
        forgot_password = findViewById(R.id.forgot_pass);
        ConstraintLayout Background = (ConstraintLayout)findViewById(R.id.sign_in_background);


        //Control what happens when DarkMode is turned on
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                //do stuff();
                Background.setBackgroundColor(Color.BLACK);
                SignIn.setTextColor(Color.BLACK);
                SignIn.setBackgroundColor(Color.WHITE);
                break;
        }

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String  email = email_SignIn.getText().toString() + "@students.wits.ac.za";
              String  password = password_signIn.getText().toString();
                goToHomeActivity(email,password);
            }
        });
    }
    public void goToHomeActivity( String  email,String password) {

        signInUser(email,password);
    }

    private void signInUser(String email, String password) {

        if (email.isEmpty()) {
            email_layout_s.setError("Email required");

        }
        if (password.isEmpty()) {
            Password_layout.setError("Password required");

        } else
        {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                SignInActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);

                                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);


                                    }
                                });

                            }
                            else {
                                SignInActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(SignInActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                });

                            }

                        }

                    });
        }
    }
    public  void updateUI(FirebaseUser account){

        if(account != null){
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,HomeActivity.class));

        }else {
            //Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }

    public void registerPage(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }
}