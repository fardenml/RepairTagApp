package com.midstatemusic.repairtag_v4.Helpers;

import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

public class DatabaseConnections {
    public static Connection con;
    public static Statement stmt;

    private final static String user = "music";
    private final static String password = "clarkson2018";
    private final static String ip = "jdbc:mysql://192.168.1.2:3306/Midstate";


    public static Boolean dbConnect(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(ip, user, password);
            stmt = con.createStatement();
            return true;
        } catch (Exception e) {
            Log.d("CONNECTION ERROR", e.toString());
            return false;
        }
    }




}
