package com.mahe.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by maha on 29/06/16.
 */
public class Driver {
    private static Driver _instance = null;
    Connection myConn;

    private Driver () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_login","root","Wunstorf1!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            _instance = null;
        }
    }

    private synchronized static void createInstance () {
        if (_instance == null) _instance = new Driver ();
    }

    public static Driver getInstance () {
        if (_instance == null) createInstance ();
        return _instance;
    }

    public String getPassword(String username) {
        try{
            Statement myStatement = myConn.createStatement();
            ResultSet myRs = myStatement.executeQuery("SELECT * FROM database_login.login_details WHERE username" + " = '" + username +"';");
            if(myRs.next()){
                return myRs.getString("password");
            }else{
                return "";
            }
        } catch (SQLException e){
            e.printStackTrace();
            //print that login was unsuccessfull to screen
            System.out.println("login unsuccessfull");
            return "";
        }
    }

    public boolean setProfile(String username,String password){
        try{
            Statement myStatement = myConn.createStatement();
            if(!findProfile(username,password)){
                myStatement.executeUpdate("INSERT INTO database_login.login_details  (username,password) VALUES ('" + username + "','" + password + "');");
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProfile(String username,String password){

        try {
            Statement myStatement = myConn.createStatement();
            myStatement.executeUpdate("DELETE FROM database_login.login_details WHERE username ='" + username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean findProfile(String username,String password){
        try {
            Statement myStatement = myConn.createStatement();
            ResultSet myRs = myStatement.executeQuery("SELECT username,password FROM database_login.login_details WHERE username = '" + username + "';");
            if(myRs.next()){
                if(myRs.getString("username").equals(username) && myRs.getString("password").equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
