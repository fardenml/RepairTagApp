package com.midstatemusic.repairtag_v4.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.*;

public class SqliteHelper extends SQLiteOpenHelper{
    private static final String databaseName = "Midstate.db";

    private final String employeesTableName = "employees";
    private final String employeesColumnID = "id";
    private final String employeesColumnFirst = "first_name";
    private final String employeesColumnLast = "last_name";
    private final String employeesColumnActive = "active";

    public SqliteHelper (Context context){
        super (context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table employees(id integer primary key, first_name text, last_name text, active integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(db);
    }

    public void updateLocalEmployees(){
        String id, firstName, lastName, insertEmployees;
        Boolean active;
        SQLiteDatabase db = this.getWritableDatabase();

        String selectEmployees = "SELECT * FROM employees";
        Boolean connectionStatus = DatabaseConnections.dbConnect();
        try {
            ResultSet rsEmployees = DatabaseConnections.stmt.executeQuery(selectEmployees);
            while (rsEmployees.next()){
            //rsEmployees.next();
                id = rsEmployees.getString("id");
                firstName =rsEmployees.getString("first_name");
                lastName = rsEmployees.getString("last_name");
                active = rsEmployees.getBoolean("active");
                int activeInt = active ? 1 : 0;
                db.execSQL("DROP TABLE IF EXISTS employees");
                db.execSQL("CREATE TABLE IF NOT EXISTS employees(id integer primary key, first_name text, last_name text, active integer)");
                /*ContentValues contentValues = new ContentValues();
                contentValues.put("id", id);
                contentValues.put("first_name", firstName);
                contentValues.put("last_name", lastName);
                contentValues.put("active", active);*/
                Log.d("EMPLOYEE DATA", id + ", " + firstName);
                insertEmployees = String.format("INSERT INTO employees VALUES ('%s', '%s', '%s', %d)", id, firstName, lastName, activeInt);
                db.execSQL(insertEmployees);
                //db.insert("employees", null, contentValues);
            }
        } catch (Exception e){
            Log.d("SQL ERROR", e.toString());
        }
    }

    public void checkEmployee(String employeeID){

    }

}
