package com.midstatemusic.repairtag_v4.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.sql.*;
import java.util.Objects;

public class PinActivity extends AppCompatActivity {

    public Button login;
    public EditText pin;
    public TextView error;

    Boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        login = findViewById(R.id.buttonPinLogin);
        pin = findViewById(R.id.editPin);
        error = findViewById(R.id.textPinError);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPinLogin:

                hideKeyboard(v);

                Info.employeeID = pin.getText().toString();

                connectionStatus = DatabaseConnections.dbConnect();

                try {
                    String adminQuery = "select id from employees where first_name = \"Admin\"";

                    ResultSet rsAdmin = DatabaseConnections.stmt.executeQuery(adminQuery);
                    rsAdmin.next();

                    Info.adminID = rsAdmin.getString("id");

                    String counter = "select count(*) from employees where id = " + Info.employeeID;

                    ResultSet rsCount = DatabaseConnections.stmt.executeQuery(counter);
                    rsCount.next();
                    int count = Integer.parseInt(rsCount.getString("count(*)"));
                    Log.d("ID COUNT", String.valueOf(count));

                    if (count > 0) {
                        String query = "select first_name, last_name from employees where id = " + Info.employeeID;

                        ResultSet rs = DatabaseConnections.stmt.executeQuery(query);
                        rs.next();

                        Info.employeeFirstName = rs.getString("first_name");
                        Info.employeeLastName = rs.getString("last_name");

                        startActivity(new Intent(this, MainActivity.class));
                    } else{
                        error.setText("Pin Incorrect!");
                    }

                    DatabaseConnections.con.close();
                } catch (Exception e) {
                    Log.d("SQL ERROR", e.toString());
                    if (Info.employeeID.equals("2018")) {
                        Info.employeeFirstName = "Offline";
                        Info.employeeLastName = "Admin";
                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
                break;

            default:
                break;
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
