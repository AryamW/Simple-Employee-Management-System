package com.sfb;

public class User {
    private int userId;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private long tel;
    private int role;

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(int userId, String fname, String lname, String email, String password, long tel, int role) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
