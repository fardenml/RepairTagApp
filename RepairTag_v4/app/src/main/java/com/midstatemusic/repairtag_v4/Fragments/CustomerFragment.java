package com.midstatemusic.repairtag_v4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

public class CustomerFragment extends Fragment {

    public static EditText first, last, address, city, state, zip, phone, email;

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);

        first = view.findViewById(R.id.editFirstName);
        last = view.findViewById(R.id.editLastName);
        address = view.findViewById(R.id.editAddress);
        city = view.findViewById(R.id.editCity);
        state = view.findViewById(R.id.editState);
        zip = view.findViewById(R.id.editZip);
        phone = view.findViewById(R.id.editPhone);
        email = view.findViewById(R.id.editEmail);

        if (Info.editing) {
            first.setText(Info.firstName);
            last.setText(Info.lastName);
            address.setText(Info.address);
            city.setText(Info.city);
            state.setText(Info.state);
            zip.setText(Info.zip);
            phone.setText(Info.normalizePhoneNumber(Info.phone));
            email.setText(Info.email);
        }

        return view;
    }
}