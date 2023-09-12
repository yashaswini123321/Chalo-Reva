package com.example.buss;

public class Individual_user {

    String name,srn,email;

    public Individual_user(){}

    public String getName() {
        return name;
    }

    public Individual_user(String name, String srn, String email) {
        this.name = name;
        this.srn = srn;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
