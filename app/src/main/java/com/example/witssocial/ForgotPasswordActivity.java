package com.example.witssocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText student_number;  // student number
    private TextView reset_passwordB_btn;
    private ProgressBar progressBar;
    private TextInputLayout email_layout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        student_number = findViewById(R.id.reset_mail_editText);
        reset_passwordB_btn =  findViewById(R.id.SubmitButton);
         email_layout = findViewById(R.id.Email_reset_layout);

        auth = FirebaseAuth.getInstance();
        reset_passwordB_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword()
    {
        String  stu_mail = student_number.getText().toString().trim()+"@students.wits.ac.za";
        if(stu_mail.isEmpty())
        {
            email_layout.setError("Name required");
            student_number.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(stu_mail).matches())
        {
            email_layout.setError("Please provide valid mail");
            student_number.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(stu_mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(ForgotPasswordActivity.this,check_mail.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Try again!", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}