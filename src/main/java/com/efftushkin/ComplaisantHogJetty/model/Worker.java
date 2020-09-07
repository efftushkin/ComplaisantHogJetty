package com.efftushkin.ComplaisantHogJetty.model;

public class Worker {
    private String id;
    private String firstName;
    private String lastName;

    //Default constructor for deserialise from Object value with jackson
    public Worker() {
    }

    public Worker(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
