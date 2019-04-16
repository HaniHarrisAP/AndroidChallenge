package com.thedeveloper.android;

public class Users
{
    int _id;
    String _username;
    String _password;

    public Users(){   }

    public Users(int id, String username, String password){
        this._id = id;
        this._username = username;
        this._password = password;
    }

    public Users(String username, String password){
        this._username = username;
        this._password = password;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getUsername(){
        return this._username;
    }

    public void setUsername(String _username){
        this._username = _username;
    }

    public String getPassword(){
        return this._password;
    }

    public void setPassword(String _password){
        this._password = _password;
    }

}