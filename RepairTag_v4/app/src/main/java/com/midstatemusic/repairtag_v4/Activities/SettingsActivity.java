package com.midstatemusic.repairtag_v4.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.R;

public class SettingsActivity extends AppCompatActivity {

    public EditText editUser, editPassword, editIP, editPort, editDatabase;

    Boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editUser = findViewById(R.id.editSettingsUser);
        editPassword = findViewById(R.id.editSettingsPassword);
        editIP = findViewById(R.id.editSettingsIP);
        editPort = findViewById(R.id.editSettingsPort);
        editDatabase = findViewById(R.id.editSettingsDatabase);

        editUser.setHint(DatabaseConnections.getUser());
        editPassword.setHint(DatabaseConnections.getPassword());
        editIP.setHint(DatabaseConnections.getIP());
        editPort.setHint(DatabaseConnections.getPort());
        editDatabase.setHint(DatabaseConnections.getDatabase());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettingsSubmit:

                // Hides keyboard when button is pressed
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                connectionStatus = DatabaseConnections.dbConnect();

                try {
                    if (editUser.getText().toString().length() != 0)
                        DatabaseConnections.setUser(editUser.getText().toString());
                    if (editPassword.getText().toString().length() != 0)
                        DatabaseConnections.setPassword(editPassword.getText().toString());
                    if (editIP.getText().toString().length() != 0)
                        DatabaseConnections.setIP(editIP.getText().toString());
                    if (editPort.getText().toString().length() != 0)
                        DatabaseConnections.setPort(editPort.getText().toString());
                    if (editDatabase.getText().toString().length() != 0)
                        DatabaseConnections.setDatabase(editDatabase.getText().toString());

                    editUser.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
                    editPassword.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
                    editIP.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
                    editPort.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
                    editDatabase.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));

                    DatabaseConnections.con.close();
                } catch(Exception e){
                    Log.d("SQL ERROR", e.toString());
                    editUser.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                    editPassword.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                    editIP.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                    editPort.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                    editDatabase.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorRed, null));
                }

                break;
            default:
                break;
        }
    }
}
