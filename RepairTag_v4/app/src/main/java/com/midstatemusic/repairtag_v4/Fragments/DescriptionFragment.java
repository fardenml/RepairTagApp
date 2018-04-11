package com.midstatemusic.repairtag_v4.Fragments;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.midstatemusic.repairtag_v4.Activities.PrintActivity;
import com.midstatemusic.repairtag_v4.Helpers.DatabaseConnections;
import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

import java.util.Random;

public class DescriptionFragment extends Fragment implements View.OnClickListener{
    public static Button submit, dueDate;
    public Spinner status;
    public static EditText description, price;
    public static CheckBox mpCoverage;

    Boolean connectionStatus;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        submit = view.findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(this);

        dueDate = view.findViewById(R.id.buttonDueDate);
        dueDate.setOnClickListener(this);

        description = view.findViewById(R.id.editDescription);
        price = view.findViewById(R.id.editPrice);
        mpCoverage = view.findViewById(R.id.checkBoxMPCoverage);

        status = view.findViewById(R.id.spinnerStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        String compareValue = Info.status;
        String date = "Due: " + Info.dueDate;
        if (Info.editing) {
            description.setText(Info.description);
            dueDate.setText(date);
            price.setText(Info.price);
            mpCoverage.setChecked(Info.mpCoverage);
            int spinnerPosition = adapter.getPosition(compareValue);
            status.setSelection(spinnerPosition);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit:
                if (Info.checkFields(CustomerFragment.first, CustomerFragment.last, CustomerFragment.address, CustomerFragment.city, CustomerFragment.state, CustomerFragment.zip, CustomerFragment.phone, SchoolFragment.schoolDistrict, SchoolFragment.schoolBuilding, InstrumentFragment.instrument, InstrumentFragment.brand, InstrumentFragment.serialNumber, description, price)){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Submit Repair Tag")
                            .setMessage("Are you sure you want to submit this repair tag?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                    StrictMode.setThreadPolicy(policy);

                                    try {
                                        Info.firstName = CustomerFragment.first.getText().toString();
                                        Info.lastName = CustomerFragment.last.getText().toString();
                                        Info.address = CustomerFragment.address.getText().toString();
                                        Info.city = CustomerFragment.city.getText().toString();
                                        Info.state = CustomerFragment.state.getText().toString();
                                        Info.zip = CustomerFragment.zip.getText().toString();
                                        Info.phone = PhoneNumberUtils.formatNumber(CustomerFragment.phone.getText().toString());
                                        Info.email = CustomerFragment.email.getText().toString();

                                        Info.schoolDistrict = SchoolFragment.schoolDistrict.getText().toString();
                                        Info.schoolBuilding = SchoolFragment.schoolBuilding.getText().toString();
                                        Info.teacher = SchoolFragment.teacher.getText().toString();

                                        Info.instrument = InstrumentFragment.instrument.getText().toString();
                                        Info.brand = InstrumentFragment.brand.getText().toString();
                                        Info.serialNumber = InstrumentFragment.serialNumber.getText().toString();
                                        Info.mouthPiece = InstrumentFragment.mouthPiece.isChecked();

                                        Info.description = description.getText().toString();
                                        Info.price = price.getText().toString();
                                        Info.mpCoverage = mpCoverage.isChecked();

                                        Info.status = status.getSelectedItem().toString();

                                        connectionStatus = DatabaseConnections.dbConnect();

                                        if (Info.editing) {
                                            String value = "first_name = \"" + Info.firstName + "\", last_name = \"" + Info.lastName + "\", address = \"" + Info.address + "\", city = \"" + Info.city + "\", state = \"" + Info.state + "\", zip = \"" + Info.zip + "\", phone = \"" + Info.phone + "\", email = \"" + Info.email + "\", school_district = \"" + Info.schoolDistrict + "\", school_building = \"" + Info.schoolBuilding + "\", teacher = \"" + Info.teacher + "\", instrument = \"" + Info.instrument + "\", brand = \"" + Info.brand + "\", serial_number = \"" + Info.serialNumber + "\", mouth_piece = " + Info.mouthPiece + ", description = \"" + Info.description + "\", due_date = \"" + Info.dueDate + "\", price = \"" + Info.price + "\", mp_coverage = " + Info.mpCoverage + ", status = \"" + Info.status + "\"";
                                            String command = "update records set " + value + " where id = \"" + Info.id + "\"";
                                            DatabaseConnections.stmt.executeUpdate(command);
                                        } else {
                                            Random rand = new Random();
                                            int id = rand.nextInt(5000) + 2;
                                            Info.id = String.valueOf(id);
                                            String value = id + ", \'" + Info.type + "\', \"" + Info.firstName + "\", \"" + Info.lastName + "\", \"" + Info.address + "\", \"" + Info.city + "\", \"" + Info.state + "\", " + Info.zip + ", \"" + Info.phone + "\", \"" + Info.email + "\", \"" + Info.schoolDistrict + "\", \"" + Info.schoolBuilding + "\", \"" + Info.teacher + "\", \"" + Info.instrument + "\", \"" + Info.brand + "\", \"" + Info.serialNumber + "\", " + Info.mouthPiece + ", \"" + Info.description + "\", \"" + Info.dueDate + "\", " + Info.price + ", " + Info.mpCoverage + ", \"" + Info.status + "\", \"" + Info.sentDate + "\", \"" + Info.receiveDate + "\", " + Info.employeeID;
                                            DatabaseConnections.stmt.executeUpdate("insert into records values (" + value + ")");
                                        }

                                        DatabaseConnections.con.close();

                                        Info.editing = false;

                                        Intent intent = new Intent(getActivity(), PrintActivity.class);
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        Log.d("SUBMIT ERROR", e.toString());
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Submit Repair Tag")
                            .setMessage("The following fields are required:" + Info.missingFields)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                break;

            case R.id.buttonDueDate:
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "datePicker");
                break;

            default:
                break;
        }
    }
}