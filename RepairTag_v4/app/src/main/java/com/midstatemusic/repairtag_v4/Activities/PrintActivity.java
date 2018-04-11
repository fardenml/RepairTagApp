package com.midstatemusic.repairtag_v4.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;


public class PrintActivity extends AppCompatActivity {

    public Button print, home;
    public TextView printStatus, showInfo;

    String tagID, name, address, phone, district, building, teacher, instrument, brand, serial, dueDate, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        print = findViewById(R.id.buttonPrint);
        home = findViewById(R.id.buttonHome);

        printStatus = findViewById(R.id.textPrintStatus);
        showInfo = findViewById(R.id.textShowInfo);

        tagID = "Tag ID: " + Info.id;
        name = "Name: " + Info.firstName + " " + Info.lastName;
        address = "Address: " + Info.address + ", " + Info.city + ", " + Info.state + " " + Info.zip;
        phone = "Phone: " + Info.phone;
        district = "School District: " + Info.schoolDistrict;
        building = "School Building: " + Info.schoolBuilding;
        teacher = "Teacher: " + Info.teacher;
        instrument = "Instrument: " + Info.instrument;
        brand = "Brand: " + Info.brand;
        serial = "Serial Number: " + Info.serialNumber;
        dueDate = "Due Date: " + Info.dueDate;
        price = "Price: $" + Info.price;

        String formattedTextInfo = "<body><h6>" + tagID + "<br>" + name + "<br>" + address + "<br>" + phone + "<br>" + district + "<br>" + building + "<br>" + teacher + "<br>" + instrument + "<br>" +  brand +
                "<br>" + serial + "<br>" + dueDate + "<br>" + price + "</h6></body>";

        showInfo.setText(Html.fromHtml(formattedTextInfo));
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
