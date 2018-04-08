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


    public static void requiredField(EditText editText){
        if (editText.getText().toString().isEmpty()) {
            editText.setError(editText.getHint() + " is required");
        }
    }

    public static Boolean checkFields(EditText first, EditText last, EditText address, EditText city, EditText state, EditText zip, EditText phone, EditText schoolDistrict, EditText schoolBuilding, EditText instrument, EditText brand, EditText description, EditText price){
        if (first.getText().toString().isEmpty() || last.getText().toString().isEmpty() || address.getText().toString().isEmpty() || city.getText().toString().isEmpty() || state.getText().toString().isEmpty() || zip.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || schoolDistrict.getText().toString().isEmpty() || schoolBuilding.getText().toString().isEmpty() || instrument.getText().toString().isEmpty() || brand.getText().toString().isEmpty() || description.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
