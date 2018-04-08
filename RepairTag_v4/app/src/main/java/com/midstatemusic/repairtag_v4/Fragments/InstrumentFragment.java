package com.midstatemusic.repairtag_v4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

public class InstrumentFragment extends Fragment {

    public static EditText instrument, brand, serialNumber;
    public static CheckBox mouthPiece;

    public InstrumentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instrument, container, false);

        instrument = view.findViewById(R.id.editInstrument);
        brand = view.findViewById(R.id.editBrand);
        serialNumber = view.findViewById(R.id.editSerialNumber);
        mouthPiece = view.findViewById(R.id.checkBoxMouthpiece);

        if (Info.editing){
            instrument.setText(Info.instrument);
            brand.setText(Info.brand);
            serialNumber.setText(Info.serialNumber);
            mouthPiece.setChecked(Info.mouthPiece);
        }

        Info.requiredField(instrument);
        Info.requiredField(brand);

        return view;
    }
}