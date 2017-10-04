package com.vikashkothary.life.ui.main.stream;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vikashkothary.life.R;
import com.vikashkothary.life.data.model.Reminder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> mReminders;

    @Inject
    public ReminderAdapter() {
        mReminders = new ArrayList<>();
    }

    public void setReminders(List<Reminder> reminders) {
        mReminders = reminders;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReminderViewHolder holder, int position) {
        Reminder reminder = mReminders.get(position);
        holder.hexColorView.setBackgroundColor(reminder.id());
        holder.titleTextView.setText(reminder.title());
        holder.textTextView.setText(reminder.text());
        holder.datetimeTextView.setText(reminder.datetime().toString());
    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_hex_color)
        View hexColorView;
        @BindView(R.id.text_view_title)
        TextView titleTextView;
        @BindView(R.id.text_view_text)
        TextView textTextView;
        @BindView(R.id.text_view_datetime)
        TextView datetimeTextView;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
