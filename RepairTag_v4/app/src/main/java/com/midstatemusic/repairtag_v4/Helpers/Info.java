package com.midstatemusic.repairtag_v4.Helpers;

import android.widget.EditText;

public class Info {
    public static String id;
    public static String firstName, lastName, address, city, state, phone, email, zip;
    public static String schoolDistrict, schoolBuilding, teacher;
    public static String instrument, brand, serialNumber;
    public static String description, dueDate, price, status;

    public static Boolean mouthPiece, mpCoverage;

    public static String employeeID;
    public static String type = "R";
    public static String sentDate = "sent date";
    public static String receiveDate = "receive date";

    public static Boolean editing = false;
    public static Boolean printing = false;
    public static Boolean offline = false;

    public static String employeeFirstName, employeeLastName;

    public static String adminID;

    public static String missingFields = " ";

    public static Boolean checkFields(EditText first, EditText last, EditText address, EditText city, EditText state, EditText zip, EditText phone, EditText schoolDistrict, EditText schoolBuilding, EditText instrument, EditText brand, EditText serial, EditText description, EditText price){
        if (first.getText().toString().isEmpty() || last.getText().toString().isEmpty() || address.getText().toString().isEmpty() || city.getText().toString().isEmpty() || state.getText().toString().isEmpty() || zip.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || schoolDistrict.getText().toString().isEmpty() || schoolBuilding.getText().toString().isEmpty() || instrument.getText().toString().isEmpty() || brand.getText().toString().isEmpty() || serial.getText().toString().isEmpty() || description.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
            missingFields = " ";
            if (first.getText().toString().isEmpty())
                missingFields += "First Name, ";
            if (last.getText().toString().isEmpty())
                missingFields += "Last Name, ";
            if (address.getText().toString().isEmpty())
                missingFields += "Address, ";
            if (city.getText().toString().isEmpty())
                missingFields += "City, ";
            if (state.getText().toString().isEmpty())
                missingFields += "State, ";
            if (zip.getText().toString().isEmpty())
                missingFields += "Zip, ";
            if (phone.getText().toString().isEmpty())
                missingFields += "Phone Number, ";
            if (schoolDistrict.getText().toString().isEmpty())
                missingFields += "School District, ";
            if (schoolBuilding.getText().toString().isEmpty())
                missingFields += "School Building, ";
            if (instrument.getText().toString().isEmpty())
                missingFields += "Instrument, ";
            if (brand.getText().toString().isEmpty())
                missingFields += "Brand, ";
            if (serial.getText().toString().isEmpty())
                missingFields += "Serial Number, ";
            if (description.getText().toString().isEmpty())
                missingFields += "Description, ";
            if (price.getText().toString().isEmpty())
                missingFields += "Price";

            if (missingFields.charAt(missingFields.length() - 2) == ',') {
                missingFields = missingFields.substring(0, missingFields.length() - 2);
            }
            return false;
        } else {
            missingFields = " ";
            return true;
        }
    }
}
