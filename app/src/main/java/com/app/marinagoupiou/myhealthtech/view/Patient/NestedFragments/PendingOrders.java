package com.app.marinagoupiou.myhealthtech.view.Patient.NestedFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.marinagoupiou.myhealthtech.MyHealthTechApplication;
import com.app.marinagoupiou.myhealthtech.R;
import com.app.marinagoupiou.myhealthtech.model.core.Med;
import com.app.marinagoupiou.myhealthtech.view.Patient.Adapters.PatientMedSummaryRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class PendingOrders extends Fragment {

    private RecyclerView recyclerView;
    private List<Med> medList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_patient_pending, container, false);

        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.meds_recyclerview);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // this is data for recycler view
        medList = Arrays.asList(((MyHealthTechApplication)getActivity().getApplication()).getProfileModel().getUser().pendingMedication);

        // 3. create an adapter
        PatientMedSummaryRecyclerViewAdapter mAdapter = new PatientMedSummaryRecyclerViewAdapter(medList, getActivity());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }
}