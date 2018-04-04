package com.midstatemusic.repairtag_v4.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
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

public class PinActivity extends AppCompatActivity {

    public Button login;
    public EditText pin;
    public TextView error;

    ProgressDialog nDialog;

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

                nDialog = new ProgressDialog(this);
                nDialog.setMessage("Please Wait...");
                nDialog.setTitle("Logging In");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();

                Info.employeeID = pin.getText().toString();

                connectionStatus = DatabaseConnections.dbConnect();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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

                                startActivity(new Intent(PinActivity.this, MainActivity.class));
                            } else{
                                nDialog.dismiss();
                                error.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                                error.setText("Pin Not Found! Please Try Again");
                            }

                            DatabaseConnections.con.close();
                        } catch (Exception e) {
                            nDialog.dismiss();
                            if (Info.employeeID.equals("2018")) {
                                Info.offline = true;
                                Info.employeeFirstName = "Offline";
                                Info.employeeLastName = "Admin";
                                error.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                                error.setText("Entering Offline Mode");
                                startActivity(new Intent(PinActivity.this, MainActivity.class));
                            }
                            else {
                                error.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorOrange, null));
                                error.setText("Connection Error! Please Try Again");
                            }
                            Log.d("SQL ERROR", e.toString());
                        }
                    }
                }, 2000);
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
