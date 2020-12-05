package com.example.HSUAppProject;

public class AppointHelperClass {
    String name, description, date, appID;
    int studentID, staffID = 0;

    public AppointHelperClass(){
    }

    public AppointHelperClass(String ID, String studentName, String disc, String date){
        this.studentID = Integer.parseInt(ID);
        this.name = studentName;
        this.description = disc;
        this.date = date;
    }

    public AppointHelperClass(String ID, String studentName, String disc, String date, int staffID, String appID){
        this.studentID = Integer.parseInt(ID);
        this.name = studentName;
        this.description = disc;
        this.date = date;
        this.staffID = staffID;
        this.appID = appID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
