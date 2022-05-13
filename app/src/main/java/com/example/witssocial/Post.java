package com.example.witssocial;

public class Post {
    private String image;
    private String caption;
    private String username;

    public Post(String image, String caption, String username) {
        this.image = image;
        this.caption = caption;
        this.username = username;
    }

    public Post(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

