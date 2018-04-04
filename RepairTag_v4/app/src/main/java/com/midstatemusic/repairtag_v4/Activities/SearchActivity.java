package com.midstatemusic.repairtag_v4.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.sql.ResultSet;

public class SearchActivity extends AppCompatActivity {

    public Button buttonSubmit;
    public EditText tagID;
    public TextView status;

    Boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSubmit = findViewById(R.id.buttonSearchSubmit);
        tagID = findViewById(R.id.editSearchID);
        status = findViewById(R.id.searchInfo);
    }

    public void onClick (View v){
        switch (v.getId()) {
            case R.id.buttonSearchSubmit:
                // Hides keyboard when button is pressed
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Info.id = tagID.getText().toString();
                connectionStatus = DatabaseConnections.dbConnect();
                try {
                    String columns = "first_name, last_name, address, city, state, zip, phone, email, school_district, school_building, teacher, instrument, brand, serial_number, mouth_piece, description, due_date, price, mp_coverage, status";
                    String query = "select " + columns + " from records where id = " + Info.id;
                    String counter = "select count(*) from records where id = " + Info.id;

                    Info.editing = false;

                    ResultSet rsCount = DatabaseConnections.stmt.executeQuery(counter);
                    rsCount.next();
                    int count = Integer.parseInt(rsCount.getString("count(*)"));
                    Log.d("ID COUNT", String.valueOf(count));

                    if (count > 0){
                        ResultSet rs = DatabaseConnections.stmt.executeQuery(query);
                        while (rs.next()) {
                            Info.firstName = rs.getString("first_name");
                            Info.lastName = rs.getString("last_name");
                            Info.address = rs.getString("address");
                            Info.city = rs.getString("city");
                            Info.state = rs.getString("state");
                            Info.zip = rs.getString("zip");
                            Info.phone = rs.getString("phone");
                            Info.email = rs.getString("email");
                            Info.schoolDistrict = rs.getString("school_district");
                            Info.schoolBuilding = rs.getString("school_building");
                            Info.teacher = rs.getString("teacher");
                            Info.instrument = rs.getString("instrument");
                            Info.brand = rs.getString("brand");
                            Info.serialNumber = rs.getString("serial_number");
                            Info.mouthPiece = rs.getBoolean("mouth_piece");
                            Info.description = rs.getString("description");
                            Info.dueDate = rs.getString("due_date");
                            Info.price = rs.getString("price");
                            Info.mpCoverage = rs.getBoolean("mp_coverage");
                            Info.status = rs.getString("status");
                        }
                        Info.editing = true;
                        if (Info.printing) {
                            startActivity(new Intent(this, PrintActivity.class));
                        } else {
                            startActivity(new Intent(this, InfoActivity.class));
                        }
                    } else {
                        status.setText("ID not found. Please try again!");
                    }
                    DatabaseConnections.con.close();

                } catch(Exception e) {
                    Log.d("SQL ERROR", e.toString());
                }

                break;

            default:
                break;
        }
    }
}
