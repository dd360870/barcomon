package com.example.barcomon;

/**
 * Created by BoAn on 2017/3/1.
 */

public class User{
    private String UserName;
    private String PassWord;
    private int  BarCoEnergy;


    public User(){}
    public User(String username,String password,int barcoenergy){
        UserName=username;
        PassWord=password;
        BarCoEnergy=barcoenergy;

    }

    public void setUserName(String username){
        UserName=username;
    }
    public void setPassWord(String password){
        PassWord=password;
    }
    public void setBarCoEnergy(int barcoenergy){
        BarCoEnergy=barcoenergy;
    }

    public String getUserName(){
        return UserName;
    }
    public String getPassWord(){
        return PassWord;
    }
    public int getBarCoEnergy(){
        return BarCoEnergy;
    }

}
