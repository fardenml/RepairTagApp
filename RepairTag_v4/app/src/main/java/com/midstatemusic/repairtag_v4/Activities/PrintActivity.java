package com.midstatemusic.repairtag_v4.Activities;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
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
    public TextView printStatus, showID, showName, showInstrument, showBrand, showSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        print = findViewById(R.id.buttonPrint);
        home = findViewById(R.id.buttonHome);

        printStatus = findViewById(R.id.textPrintStatus);
        showID = findViewById(R.id.textShowID);
        showName = findViewById(R.id.textShowName);
        showInstrument = findViewById(R.id.textShowInstrument);
        showBrand = findViewById(R.id.textShowBrand);
        showSerial = findViewById(R.id.textShowSerial);

        String tagID = "Tag ID: " + Info.id;
        showID.setText(tagID);

        String name = "Name: " + Info.firstName + " " + Info.lastName;
        showName.setText(name);

        String instrument = "Instrument: " + Info.brand;
        showInstrument.setText(instrument);

        String brand = "Brand: " + Info.instrument;
        showBrand.setText(brand);

        String serial = "Serial Number: " + Info.serialNumber;
        showSerial.setText(serial);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPrint:
                printStatus.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
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
