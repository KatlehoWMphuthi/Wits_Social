package com.example.witssocial.Model;

public class Post {

    private String image;
    private String Caption;
    private String username;
    private String postid;
    private String timeStamp;


    public Post(String postid, String image, String caption, String username) {
        this.image = image;
        this.Caption = caption;
        this.username = username;
        this.postid = postid;

    }

    public Post(){}



    public String getImage() {
        return image;
    }



    public String getCaption() {
        return Caption;
    }



    public String getUsername() {
        return username;
    }

    public String getPostid() {
        return postid;
    }


    public void setTime(String time){this.timeStamp = time;}

    public String getTime() {
        return this.timeStamp;
    }

}

