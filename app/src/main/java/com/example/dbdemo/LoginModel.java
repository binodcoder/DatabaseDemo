package com.example.dbdemo;

public class LoginModel {
    private int id;
    private String username;
    private int password;
    private boolean isActive;

    //constructors
    public LoginModel(int id, String username, int password, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public LoginModel() {
    }
    //toString is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", isActive=" + isActive +
                "}";
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
