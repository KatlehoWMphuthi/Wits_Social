package com.example.witssocial;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText name_SignUp; // Name
    private TextInputEditText student_number;  // student number
    private TextInputEditText password_SignUp; // password - 1st time
    private TextInputEditText password_SignUp_confirmation; // password - 2nd time for confirmation
    private TextInputLayout Password_layout,student_number_layout, name_layout_u,Password_layout_confirmation;

    private DatabaseReference RootDatabaseReference ;

    private static final String TAG = "FragmentActivity";

    private static ProgressDialog loadBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        RootDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

// where  you will link  up,
//        Name
        name_SignUp = findViewById(R.id.Username);
        name_layout_u = findViewById(R.id.Username_layout);

//        Password
        password_SignUp = findViewById(R.id.Password_editText);
        Password_layout = findViewById(R.id.LayoutPassword);
//        Student NO
        student_number_layout = findViewById(R.id.StudentNumber_layout);
        student_number = findViewById(R.id.StudentNumber);
        loadBar = new ProgressDialog(this);


        Button SignUp = findViewById(R.id.LoginButton);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String  studentNo= student_number.getText().toString() + "@students.wits.ac.za";
                String name = name_SignUp.getText().toString();
                String password = password_SignUp.getText().toString();

                signUpUser(studentNo,name,password);

            }
        });

    }

    private void signUpUser(final String studentNo,final String name,final String password) {



        if(name.isEmpty())
        {
            name_layout_u.setError("Name required");

        }
        if(studentNo.isEmpty())
        {
            student_number_layout.setError("Email required");

        }
        if(password.isEmpty())
        {
            Password_layout.setError("Password required");

        }
        if( password.length() <6)
        {

            Toast.makeText(this, "Password too short", Toast.LENGTH_LONG).show();

        }

        else {
            loadBar.setTitle("Please wait....");
            loadBar.show();
            loadBar.setCancelable(false);

            registerAccount(name,studentNo, password);
        }
    }

    private void registerAccount(String name, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        HashMap<String,Object> User_credential = new HashMap<>();
                        User_credential.put("email", email);
                        User_credential.put("password",  password);
                        User_credential.put("username", name);

                        User_credential.put("id", mAuth.getCurrentUser().getUid());
                        RootDatabaseReference.child(mAuth.getCurrentUser().getUid()).setValue(User_credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            loadBar.dismiss();
                                            Toast.makeText(SignUpActivity.this, "Account successfully created ", Toast.LENGTH_SHORT).show();
                                            // ensure user cannot come back to this activity
                                            Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                });


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                loadBar.dismiss();
            }
        });
    }

    public void loginPage(View view) {
        startActivity(new Intent(this,SignInActivity.class));
    }

}