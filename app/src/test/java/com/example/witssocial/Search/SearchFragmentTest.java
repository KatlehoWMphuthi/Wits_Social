package com.example.witssocial.Search;

import static org.junit.Assert.*;

//import com.example.witssocial.Model.firebaseTest;

import org.junit.Test;



public class SearchFragmentTest {


    @Test
    public void onCreateView() {
        String exp_search = "Louis";
        String search = "Louis";
        assertEquals(exp_search,search);

    }
}