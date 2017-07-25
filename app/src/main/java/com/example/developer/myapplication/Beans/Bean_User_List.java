package com.example.developer.myapplication.Beans;

/**
 * Created by Developer on 27-09-2016.
 */

public class Bean_User_List {


    String UserName;
    String FirstName;
    String MidName;
    String LastName;
    String Mob1;
    String Email;
    String image;
    String USer_ID;

    public String getUSer_ID() {
        return USer_ID;
    }

    public void setUSer_ID(String USer_ID) {
        this.USer_ID = USer_ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMidName() {
        return MidName;
    }

    public void setMidName(String midName) {
        MidName = midName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMob1() {
        return Mob1;
    }

    public void setMob1(String mob1) {
        Mob1 = mob1;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bean_User_List() {
        UserName = null;
        FirstName = null;
        MidName = null;
        LastName = null;
        Mob1 = null;
        Email = null;
        this.image = null;
        USer_ID = null;
    }

    public Bean_User_List(String userName, String firstName, String midName, String lastName, String mob1, String email, String image, String userid) {
        UserName = userName;
        FirstName = firstName;
        MidName = midName;
        LastName = lastName;
        Mob1 = mob1;
        Email = email;
        USer_ID = userid;
        this.image = image;
    }
}
