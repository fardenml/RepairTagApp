package com.midstatemusic.repairtag_v4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Helpers.Info;
import com.midstatemusic.repairtag_v4.R;

public class SchoolFragment extends Fragment {

    public static EditText schoolDistrict, schoolBuilding, teacher;

    public SchoolFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school, container, false);

        schoolDistrict = view.findViewById(R.id.editSchoolDistrict);
        schoolBuilding = view.findViewById(R.id.editSchoolBuilding);
        teacher = view.findViewById(R.id.editTeacher);

        if (Info.editing){
            schoolDistrict.setText(Info.schoolDistrict);
            schoolBuilding.setText(Info.schoolBuilding);
            teacher.setText(Info.teacher);
        }

        return view;
    }
}