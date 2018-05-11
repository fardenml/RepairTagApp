package com.midstatemusic.repairtag_v4.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;

import static com.midstatemusic.repairtag_v4.Helpers.Info.address;
import static com.midstatemusic.repairtag_v4.Helpers.Info.phone;

public class SearchActivity extends AppCompatActivity {

    public Button buttonSubmit;
    public EditText tagID;
    public TextView status, textRecent;

    ListView listView;

    Boolean connectionStatus;

    String recentID1, recentID2, recentID3, recentID4, recentID5;

    String recentID1Txt, recentID2Txt, recentID3Txt, recentID4Txt, recentID5Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSubmit = findViewById(R.id.buttonSearchSubmit);
        tagID = findViewById(R.id.editSearchID);
        status = findViewById(R.id.searchInfo);

        textRecent = findViewById(R.id.textMostRecent);

        getRecentIDs();

        if (connectionStatus) {
            listView = findViewById(R.id.mostRecentList);
            String[] values = new String[]{recentID1Txt, recentID2Txt, recentID3Txt, recentID4Txt, recentID5Txt};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, android.R.id.text1, values);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String itemValue = (String) listView.getItemAtPosition(position);

//                Toast.makeText(getApplicationContext(),
//                        "Position: "+ position + "  ListItem: " + itemValue , Toast.LENGTH_LONG)
//                        .show();

                    switch (position) {
                        case 0:
                            getFields(recentID1);
                            break;
                        case 1:
                            getFields(recentID2);
                            break;
                        case 2:
                            getFields(recentID3);
                            break;
                        case 3:
                            getFields(recentID4);
                            break;
                        case 4:
                            getFields(recentID5);
                            break;

                        default:
                            break;
                    }
                }
            });
        }
    }

    public void onClick (View v){
        switch (v.getId()) {
            case R.id.buttonSearchSubmit:
                // Hides keyboard when button is pressed
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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

            default:
                break;
        }
    }

    public void getRecentIDs () {
        connectionStatus = DatabaseConnections.dbConnect();
        if (connectionStatus) { // Has connection to db
            String first, last, fDate;
            Date date;

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

                            recentID1Txt = ("ID: " + recentID1 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 2:
                            recentID2 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            recentID2Txt = ("ID: " + recentID2 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 3:
                            recentID3 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            recentID3Txt = ("ID: " + recentID3 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 4:
                            recentID4 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);

                            recentID4Txt = ("ID: " + recentID4 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
                            break;

                        case 5:
                            recentID5 = rs.getString("id");
                            first = rs.getString("first_name");
                            last = rs.getString("last_name");
                            date = rs.getDate("date_received");

                            fDate = Info.mySQlToAndroidTime(date);;

                            recentID5Txt = ("ID: " + recentID5 + " \nName: " + last + ", " + first + " \nDate: " + fDate);
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
                address = rs.getString("address");
                Info.city = rs.getString("city");
                Info.state = rs.getString("state");
                Info.zip = rs.getString("zip");
                phone = rs.getString("phone");
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
