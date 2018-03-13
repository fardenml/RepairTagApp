package com.midstatemusic.repairtag_v4.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.R;
import com.midstatemusic.repairtag_v4.Helpers.Info;

import java.lang.reflect.Field;


public class PrintActivity extends AppCompatActivity {

    public Button print, home;
    public TextView printStatus, showID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        print = findViewById(R.id.buttonPrint);
        home = findViewById(R.id.buttonHome);

        printStatus = findViewById(R.id.textPrintStatus);
        showID = findViewById(R.id.textShowID);

        String tagID = "ID: " + Info.id;
        showID.setText(tagID);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPrint:
                printStatus.setText("Print Job Sent!");
                break;
            case R.id.buttonHome:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}
