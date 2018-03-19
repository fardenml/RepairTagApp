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
    public TextView printStatus, showID, showName, showInstrument, showBrand, showSerial, showDueDate, showPrice;

    String tagID, name, phone, district, building, teacher, instrument, brand, serial, dueDate, price;

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
        showDueDate = findViewById(R.id.textShowDueDate);
        showPrice = findViewById(R.id.textShowPrice);

        tagID = "Tag ID: " + Info.id;
        showID.setText(tagID);

        name = "Name: " + Info.firstName + " " + Info.lastName;
        showName.setText(name);

        phone = "Phone: " + Info.phone;

        district = "School District: " + Info.schoolDistrict;

        building = "School Building: " + Info.schoolBuilding;

        teacher = "Teacher: " + Info.teacher;

        instrument = "Instrument: " + Info.instrument;
        showInstrument.setText(instrument);

        brand = "Brand: " + Info.brand;
        showBrand.setText(brand);

        serial = "Serial Number: " + Info.serialNumber;
        showSerial.setText(serial);

        dueDate = "Due Date: " + Info.dueDate;
        showDueDate.setText(dueDate);

        price = "Price: $" + Info.price;
        showPrice.setText(price);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPrint:
                printStatus.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, null));
                printStatus.setText("Print Job Sent!");

                String textToPrint = "<IMAGE>http://i44.photobucket.com/albums/f41/MidstateMusic/midstate_header_200x200_zpsv8bolg7k.png<BR><BIG>" + tagID + "<BR>" + name + "<BR>" + phone + "<BR>" + district + "<BR>" + building + "<BR>" + teacher + "<BR>" + instrument + "<BR>" + brand + "<BR>" + serial + "<BR>" + dueDate + "<BR>" + price;
                Intent intent = new Intent("pe.diegoveloper.printing");
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT,textToPrint);
                startActivity(intent);
                break;
            case R.id.buttonHome:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}
