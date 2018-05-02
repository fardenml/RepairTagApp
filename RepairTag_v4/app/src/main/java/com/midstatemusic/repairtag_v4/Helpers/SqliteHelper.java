package com.midstatemusic.repairtag_v4.Helpers;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.*;

public class SqliteHelper {
    SQLiteDatabase db;
    static Boolean connectionStatus;

    public static void updateEmployees() {
        String removeTuples = "DELETE FROM employees";
        String selectTuples = "SELECT * FROM employees";
        String addTuples;

        String firstName, lastName, id;

        db.execSQL(removeTuples);

        connectionStatus = DatabaseConnections.dbConnect();

        try {
            ResultSet rs = DatabaseConnections.stmt.executeQuery(selectTuples);
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                id = rs.getString("id");

                addTuples = "INSERT INTO employees VALUES (" + firstName + ", " + lastName + ", " + id + ")";

                db.execSQL(addTuples);
            }

            DatabaseConnections.con.close();
        } catch(Exception e) {
            Log.d("GET EMPLOYEES ERROR", e.toString());
        }
    }

    public static Boolean offlineLogin(String id) {
        try {
            String selection = "id = ?";
            String[] selectionArgs = { id };

            Cursor cursor = db.query(
                    "employees",   	// The table to query
                    null,           // The array of columns to return (pass null to get all)
                    selection,      // The columns for the WHERE clause
                    selectionArgs, 	// The values for the WHERE clause
                    null,           // don't group the rows
                    null,           // don't filter by row groups
                    null       		// The sort order
            );

            if (cursor.moveToNext()) {
                //id exists
                Info.EmployeeFirstName = cursor.getString(getColumnIndex("first_name"));
                Info.EmployeeLastName = cursor.getString(getColumnIndex("lastName_name"));
                return true;
            } else {
                //id not found
                return false;
            }
        } catch (Exception e) {
            log.d("OFFLINE LOGIN ERROR", e.toString());
            return false;
        }
        return false;
    }
}
