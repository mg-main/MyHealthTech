package com.app.marinagoupiou.myhealthtech.view.Employee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.marinagoupiou.myhealthtech.BuildConfig;
import com.app.marinagoupiou.myhealthtech.MainActivity;
import com.app.marinagoupiou.myhealthtech.R;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class EmployeeFragment extends Fragment {

    public static final String FRAGMENT_TAG =
            BuildConfig.APPLICATION_ID + ".DEBUG_EMPLOYEE_FRAGMENT";

    public EmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_employee, container, false);

        Toolbar mToolbar = rootView.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(mToolbar);

        return rootView;
    }


}
