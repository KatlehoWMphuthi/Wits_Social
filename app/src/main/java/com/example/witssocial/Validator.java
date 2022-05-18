package com.example.witssocial;

public class Validator {
    String a;
    public Validator(String b){
        a = b;
    }

    public boolean isinputString(){
        if(a instanceof String){
            return true;
        }else{
            return false;
        }
    }

    public int add(){
        return 2;
    }

}
