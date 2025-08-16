package com.example.welcomeapp.model;

public class UserData {
    private String name;
    private int age;

    // Constructor
    public UserData(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Default constructor (required for Gson)
    public UserData() {
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }
}

