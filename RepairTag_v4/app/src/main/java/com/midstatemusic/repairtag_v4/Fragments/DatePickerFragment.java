package com.midstatemusic.repairtag_v4.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.midstatemusic.repairtag_v4.Helpers.Info;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    Button dueDateButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Info.dueDate = Integer.toString(month + 1) +  "/" + Integer.toString(day) + "/" + Integer.toString(year);

        dueDateButton = DescriptionFragment.dueDate;

        String date = "Due: " + Info.dueDate;
        dueDateButton.setText(date);
    }
}