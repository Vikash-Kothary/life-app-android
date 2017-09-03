package com.vikashkothary.life.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

@AutoValue
public abstract class Reminder implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Reminder.Builder();
    }

    public static TypeAdapter<Reminder> typeAdapter(Gson gson) {
        return new AutoValue_Reminder.GsonTypeAdapter(gson);
    }

    public abstract long id();

    public abstract String message();

    public abstract Date datetime();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);

        public abstract Builder setMessage(String message);

        public abstract Builder setDatetime(Date datetime);

        public abstract Reminder build();
    }
}
