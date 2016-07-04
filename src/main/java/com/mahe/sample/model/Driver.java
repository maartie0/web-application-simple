package com.mahe.sample.model;

import com.mahe.sample.ServletLogIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by maha on 29/06/16.
 */
public class Driver {

    private static final Logger log= Logger.getLogger( ServletLogIn.class.getName() );
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
                log.log(Level.FINE,"Password for username:"+username+"has been returned");
                return myRs.getString("password");
            }else{
                log.log(Level.INFO,"SQL database did not return password, likely cause: profile does not exist");
                return "";
            }
        } catch (SQLException e){
            e.printStackTrace();
            log.log(Level.SEVERE,"Password can't be found, likely cause: Profile with username " + username + " doesn't exist, database refuses to give password");
            return "";
        }
    }

    public boolean setProfile(String username,String password){
        try{
            Statement myStatement = myConn.createStatement();
            if(!findProfile(username,password)){
                myStatement.executeUpdate("INSERT INTO database_login.login_details  (username,password) VALUES ('" + username + "','" + password + "');");
                log.log(Level.FINE,"new profile created , username:" + username);
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            log.log(Level.SEVERE,"ERROR SQLException: profile couldn't be added with username:" +username+ " likely cause: profile already exists");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProfile(String username,String password){
        try {
            Statement myStatement = myConn.createStatement();
            myStatement.executeUpdate("DELETE FROM database_login.login_details WHERE username ='" + username + "';");
            log.log(Level.FINE,"Profile with username:" + username + " has been deleted");
            return true;
        } catch (SQLException e) {
            log.log(Level.SEVERE,"ERROR Profile could not be deleted, username:" + username + "like cause: profile doesn't exist");
            e.printStackTrace();
            return false;
        }
    }

    public boolean findProfile(String username,String password){
        try {
            Statement myStatement = myConn.createStatement();
            ResultSet myRs = myStatement.executeQuery("SELECT username,password FROM database_login.login_details WHERE username = '" + username + "';");
            if(myRs.next() && myRs.getString("username").equals(username) && myRs.getString("password").equals(password)){
                log.log(Level.FINE,"Profile with username:"+username+"has been found");
                return true;
            }else{
                log.log(Level.INFO,"Profile with username:"+username+" has not been found, likely cause: profile does NOT exist, or password is wrong");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE,"Profile with username:\"+username+\" has not been found, likely cause: profile dos NOT exist");
            return false;
        }
    }

}
