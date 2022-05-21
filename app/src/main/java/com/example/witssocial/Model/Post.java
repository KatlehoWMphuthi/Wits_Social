package com.example.witssocial.Model;

public class Post {
    private String image;
    private String Caption;
    private String username;
    private String postid;
    private  String profilePicture;

    public Post(String image, String caption, String username, String profilePicture) {
        this.image = image;
        this.Caption = caption;
        this.username = username;
        this.postid = postid;
        this.profilePicture = profilePicture;
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

    public String getProfilePicture(){return profilePicture;};


}

