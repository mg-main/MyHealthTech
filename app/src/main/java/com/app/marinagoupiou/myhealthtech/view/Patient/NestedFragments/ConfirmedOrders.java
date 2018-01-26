package com.app.marinagoupiou.myhealthtech.view.Patient.NestedFragments;

import android.os.Bundle;
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
import com.app.marinagoupiou.myhealthtech.model.core.Notification;
import com.app.marinagoupiou.myhealthtech.view.Patient.Adapters.NotificationsRecyclerViewAdapter;
import com.app.marinagoupiou.myhealthtech.view.Patient.Adapters.PatientMedSummaryRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class ConfirmedOrders extends Fragment {

    private RecyclerView recyclerView;
    private List<Notification> notifications;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_patient_confirmed, container, false);

        MyHealthTechApplication application = (MyHealthTechApplication)getActivity().getApplication();
        if(application.getNotificationModel().notifications.size() == 0) {
            application.getNotificationModel().notifications.add(new Notification("Delivery slot confirmed for 21-01-2018 04:30 pm", "20-01-2018", "12:31 PM"));
            application.getNotificationModel().notifications.add(new Notification("Delivery slot confirmed for 22-03-2018 11:30 am", "20-01-2018", "01:12 PM"));
            application.getNotificationModel().notifications.add(new Notification("Delivery slot confirmed for 10-02-2018 01:00 pm", "18-12-2017", "04:05 PM"));
            application.getNotificationModel().notifications.add(new Notification("Delivery slot confirmed for 09-01-2018 05:00 pm", "17-12-2017", "11:47 AM"));
        }

        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.confirmed_recyclerview);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // this is data for recycler view
        notifications = application.getNotificationModel().notifications;

        // 3. create an adapter
        NotificationsRecyclerViewAdapter mAdapter = new NotificationsRecyclerViewAdapter(notifications, getActivity());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}