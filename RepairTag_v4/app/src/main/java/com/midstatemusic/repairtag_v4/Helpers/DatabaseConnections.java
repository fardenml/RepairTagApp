package com.midstatemusic.repairtag_v4.Helpers;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnections {
    public static Connection con;
    public static Statement stmt;

    private static String user = "midstatedb";
    private static String password = "Midstate2003!";
    private static String ip = "midstatedb.c50i3meqerif.us-east-2.rds.amazonaws.com";
    private static String port = "3306";
    private static String database = "midstate";

    public static Boolean dbConnect(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database, user, password);
            stmt = con.createStatement();
            return true;
        } catch (Exception e) {
            Log.d("CONNECTION ERROR", e.toString());
            return false;
        }
    }

    public static void setUser(String newUser){
        user = newUser;
    }

    public static void setPassword(String newPassword){
        password = newPassword;
    }

    public static void setIP(String newIP){
        ip = newIP;
    }

    public static void setPort(String newPort) {
        port = newPort;
    }

    public static void setDatabase(String newDatabase){
        database = newDatabase;
    }

    public static String getUser(){
        return user;
    }

    public static String getPassword(){
        return password;
    }

    public static String getIP(){
        return ip;
    }

    public static String getPort(){
        return port;
    }

    public static String getDatabase(){
        return database;
    }
}
