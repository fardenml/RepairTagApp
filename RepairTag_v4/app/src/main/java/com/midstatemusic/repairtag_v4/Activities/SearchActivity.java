package com.midstatemusic.repairtag_v4.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchActivity extends AppCompatActivity {

    public Button buttonSubmit;
    public EditText tagID;
    public TextView status, textRecent;

    public Button buttonRecent1, buttonRecent2, buttonRecent3, buttonRecent4, buttonRecent5;

    Boolean connectionStatus;

    String recentID1, recentID2, recentID3, recentID4, recentID5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSubmit = findViewById(R.id.buttonSearchSubmit);
        tagID = findViewById(R.id.editSearchID);
        status = findViewById(R.id.searchInfo);

        buttonRecent1 = findViewById(R.id.recent1);
        buttonRecent2 = findViewById(R.id.recent2);
        buttonRecent3 = findViewById(R.id.recent3);
        buttonRecent4 = findViewById(R.id.recent4);
        buttonRecent5 = findViewById(R.id.recent5);

        textRecent = findViewById(R.id.textMostRecent);

        getRecentIDs();

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
                    String counter = "select count(*) from records where id = " + Info.id;

                    Info.editing = false;

                    ResultSet rsCount = DatabaseConnections.stmt.executeQuery(counter);
                    rsCount.next();
                    int count = Integer.parseInt(rsCount.getString("count(*)"));
                    Log.d("ID COUNT", String.valueOf(count));

                    if (count > 0){
                        getFields(Info.id);
                    } else {
                        status.setText("ID not found. Please try again!");
                    }
                    DatabaseConnections.con.close();
                } catch(Exception e) {
                    Log.d("SQL ERROR", e.toString());
                }
                break;

            case R.id.recent1:
                getFields(recentID1);
                break;

            case R.id.recent2:
                getFields(recentID2);
                break;

            case R.id.recent3:
                getFields(recentID3);
                break;

            case R.id.recent4:
                getFields(recentID4);
                break;

            case R.id.recent5:
                getFields(recentID5);
                break;

            default:
                break;
        }
    }

    public void getRecentIDs () {
        connectionStatus = DatabaseConnections.dbConnect();
        if (connectionStatus) { // Has connection to db
            String first, last, fDate;
            Date date;

            SimpleDateFormat fmt;

            String top5 = "select * from records order by id DESC limit 5";

            int i = 0;

            try {
                ResultSet rs = DatabaseConnections.stmt.executeQuery(top5);
                while (rs.next()) {
                    i++;
                    switch(i) {
                        case 1:
                            recentID1 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            buttonRecent1.setText("ID: " + recentID1 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 2:
                            recentID2 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            buttonRecent2.setText("ID: " + recentID2 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 3:
                            recentID3 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            buttonRecent3.setText("ID: " + recentID3 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 4:
                            recentID4 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            buttonRecent4.setText("ID: " + recentID4 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 5:
                            recentID5 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);;

                            buttonRecent5.setText("ID: " + recentID5 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        default:
                            i = 0;
                            break;
                    }
                }
                DatabaseConnections.con.close();
            } catch (Exception e) {
                Log.d("SQL Top 5 Error", e.toString());
            }



        } else {
            // Doesn't have connection to db

            buttonRecent1.setVisibility(View.INVISIBLE);
            buttonRecent2.setVisibility(View.INVISIBLE);
            buttonRecent3.setVisibility(View.INVISIBLE);
            buttonRecent4.setVisibility(View.INVISIBLE);
            buttonRecent5.setVisibility(View.INVISIBLE);
            textRecent.setVisibility(View.INVISIBLE);
        }


    }

    public void getFields(String id){
        Info.id = id;

        connectionStatus = DatabaseConnections.dbConnect();

        String columns = "first_name, last_name, address, city, state, zip, phone, email, school_district, school_building, teacher, instrument, brand, serial_number, mouth_piece, description, due_date, price, mp_coverage, status";
        String query = "select " + columns + " from records where id = " + Info.id;
        try {
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
            DatabaseConnections.con.close();
            if (Info.printing) {
                startActivity(new Intent(this, PrintActivity.class));
            } else {
                startActivity(new Intent(this, InfoActivity.class));
            }
        } catch (Exception e) {
            Log.d("SQL ERROR", e.toString());
        }
    }

}
