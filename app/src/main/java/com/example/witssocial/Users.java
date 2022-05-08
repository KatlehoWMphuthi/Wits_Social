package com.example.witssocial;

public class Users {


    String Username, imageURL, id, status;

    public Users(String Username, String imageURL, String id, String status) {
        this.Username = Username;
        this.imageURL = imageURL;
        this.id = id;
        this.status = status;
    }

    public Users() {
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
