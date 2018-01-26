package com.app.marinagoupiou.myhealthtech.view.Patient.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.marinagoupiou.myhealthtech.R;
import com.app.marinagoupiou.myhealthtech.model.core.Notification;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class NotificationsRecyclerViewAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewAdapter.NotificationViewHolder> {

    private List<Notification> notifications = new LinkedList<>();
    private Context context;

    public NotificationsRecyclerViewAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView message;
        private TextView dateReceived;
        private TextView timeReceived;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            message = itemView.findViewById(R.id.message);
            dateReceived = itemView.findViewById(R.id.dateReceived);
            timeReceived = itemView.findViewById(R.id.timeReceived);
        }
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder notificationViewHolder, int i) {
        notificationViewHolder.message.setText(notifications.get(i).message);
        notificationViewHolder.dateReceived.setText(notifications.get(i).dateReceived);
        notificationViewHolder.timeReceived.setText(notifications.get(i).timeReceived);

    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row_notification, viewGroup, false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
