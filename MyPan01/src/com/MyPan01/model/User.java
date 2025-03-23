package com.MyPan01.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String pwd;

    public User() {
    }

    public User(int id, String username, String email, String pwd) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pwd = pwd;
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pwd = null;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
