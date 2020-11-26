package com.example.HSUAppProject;

public class UserHelperClass {
    String firstName, lastName, password;
    int ID;

    public UserHelperClass(){
    }

    public UserHelperClass(String ID, String firstName, String lastName, String password){
        this.ID = Integer.parseInt(ID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
