package com.midstatemusic.repairtag_v4.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.sql.ResultSet;

public class SettingsActivity extends AppCompatActivity {

    public EditText editAdminId;

    Boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editAdminId = findViewById(R.id.editAdminId);

        editAdminId.setHint(Info.adminID);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonChangeId:

                // Hides keyboard when button is pressed
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                connectionStatus = DatabaseConnections.dbConnect();

                try {
                    String id = editAdminId.getText().toString();
                    String command = "UPDATE employees SET id = " + id + " WHERE first_name = \"Admin\"";
                    DatabaseConnections.stmt.executeUpdate(command);

                    Info.adminID = id;
                    Info.employeeID = id;
                    editAdminId.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));

                    DatabaseConnections.con.close();
                } catch(Exception e){
                    Log.d("SQL ERROR", e.toString());
                    editAdminId.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                }

                break;
            default:
                break;
        }
    }
}
