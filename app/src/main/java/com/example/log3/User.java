package com.example.log3;

public class User {
    private int id;
    private String name;
    private String dob;
    private String email;
    private byte[] image;

    public User(int id, String name, String dob, String email, byte[] image) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getImage() {
        return image;
    }
}
