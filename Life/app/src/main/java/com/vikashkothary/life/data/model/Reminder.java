package com.vikashkothary.life.data.model;

import android.app.Notification;
import android.content.Context;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

import static com.vikashkothary.life.util.NotificationFactory.createSimpleNotification;

@AutoValue
public abstract class Reminder implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Reminder.Builder();
    }

    public static TypeAdapter<Reminder> typeAdapter(Gson gson) {
        return new AutoValue_Reminder.GsonTypeAdapter(gson);
    }

    public abstract int id();

    public abstract String title();

    public abstract String text();

    public abstract Date datetime();

    public Notification notification(Context context) {
        return createSimpleNotification(context, title(), text());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);

        public abstract Builder setTitle(String title);

        public abstract Builder setText(String text);

        public abstract Builder setDatetime(Date datetime);

        public abstract Reminder build();
    }
}
