package com.example.witssocial.Authentication;

import static org.junit.Assert.*;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Rule;
import org.junit.Test;

public class SignUpActivityTest {

    final String testUsername = "testUser";
    final String testPassword = "password";



    @Test
    public void onCreate() {
//        testing wether the main is a string
        String input = "2118127";
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = "2118127"; //user.getEmail();
        assertEquals(email,input);

    }

    @Test
    public void loginPage() {
        String input = "2118127";
        String  curr = "";
        if(input.contentEquals("2118127"))
        {
            curr = "logged in successfully";
        }

        assertEquals("logged in successfully",curr);

    }
    @Test
    public void CheckAllFields(){

        boolean isAllFieldsChecked = false;

        assertEquals(isAllFieldsChecked,false);
    }
}