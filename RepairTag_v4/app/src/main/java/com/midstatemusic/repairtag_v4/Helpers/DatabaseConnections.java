package com.midstatemusic.repairtag_v4.Helpers;

import android.content.res.AssetManager;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnections {
    public static Connection con;
    public static Statement stmt;

    private static String user;
    private static String password;
    private static String ip;
    private static String port;
    private static String database;

    public static void loadCredentials(AssetManager assets) {
        String json = null;
        try {
            InputStream is = assets.open("credentials.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            JSONObject obj = new JSONObject(json);

            user = obj.getString("username");
            password = obj.getString("password");
            ip = obj.getString("ip");
            port = obj.getString("port");
            database = obj.getString("database");

        } catch(JSONException e){
            e.printStackTrace();
        }



    }


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
