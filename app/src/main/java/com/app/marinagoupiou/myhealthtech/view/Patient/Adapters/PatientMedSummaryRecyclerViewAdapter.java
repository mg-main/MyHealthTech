package com.app.marinagoupiou.myhealthtech.view.Patient.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.marinagoupiou.myhealthtech.R;
import com.app.marinagoupiou.myhealthtech.model.core.DateTimeData;
import com.app.marinagoupiou.myhealthtech.model.core.Med;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class PatientMedSummaryRecyclerViewAdapter extends RecyclerView.Adapter<PatientMedSummaryRecyclerViewAdapter.MedViewHolder> {

    private List<Med> meds;
    private Context context;
    private final DateTimeData dateTimeData = new DateTimeData();

    public PatientMedSummaryRecyclerViewAdapter(List<Med> meds, Context context) {
        this.meds = meds;
        this.context = context;
    }

    public class MedViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView med_name_text;
        private TextView med_status_text;
        private ImageView med_status_img;
        private TextView order_now_text;

        public MedViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            med_name_text = itemView.findViewById(R.id.med_name);
            med_status_text = itemView.findViewById(R.id.med_status_text);
            med_status_img = itemView.findViewById(R.id.med_status_img);
            order_now_text = itemView.findViewById(R.id.order_now_text);
        }
    }

    @Override
    public void onBindViewHolder(MedViewHolder medViewHolder, int i) {
        medViewHolder.med_name_text.setText(meds.get(i).getName());
        if (meds.get(i).getStatus() == 1) {
            medViewHolder.med_status_img.setBackground(context.getResources().getDrawable(R.drawable.red_solid_circle, null));
            medViewHolder.med_status_text.setText("No more left");
            medViewHolder.order_now_text.setVisibility(View.VISIBLE);
        } else if (meds.get(i).getStatus() == 2) {
            medViewHolder.med_status_img.setBackground(context.getResources().getDrawable(R.drawable.yellow_solid_circle, null));
            medViewHolder.med_status_text.setText("Available for 2 days");
            medViewHolder.order_now_text.setVisibility(View.VISIBLE);
        } else {
            medViewHolder.med_status_img.setBackground(context.getResources().getDrawable(R.drawable.green_solid_circle, null));
            medViewHolder.med_status_text.setText("Adequate!");
            medViewHolder.order_now_text.setVisibility(View.GONE);
        }

        medViewHolder.order_now_text.setOnClickListener(view -> {
            final View dialogView = View.inflate(context, R.layout.date_time_picker, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

            dialogView.findViewById(R.id.date_time_set).setOnClickListener(view1 -> {

                DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
                TimePicker timePicker = dialogView.findViewById(R.id.timePicker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getHour(),
                        timePicker.getMinute());

                dateTimeData.setTimeMillis(calendar.getTimeInMillis());
                alertDialog.dismiss();
                validateTimeslots();
            });
            alertDialog.setView(dialogView);
            alertDialog.show();
        });

    }

    @Override
    public MedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row_med_item, viewGroup, false);
        MedViewHolder medViewHolder = new MedViewHolder(view);
        return medViewHolder;
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }

    public void validateTimeslots() {
        showRepeatDialog();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm");
        Toast.makeText(context, "Available Date and Time selected:" + sdf.format(new Date(dateTimeData.getTimeMillis())), Toast.LENGTH_SHORT).show();
    }

    public void showRepeatDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Repeat timeslot");
        builder.setMessage("Would you like to repeat this timeslot forever?");

        builder.setPositiveButton("YES", (dialog, which) -> {
            // Do nothing for now but close the dialog
            dialog.dismiss();
        });

        builder.setNegativeButton("NO", (dialog, which) -> {
            // Do nothing
            dialog.dismiss();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}