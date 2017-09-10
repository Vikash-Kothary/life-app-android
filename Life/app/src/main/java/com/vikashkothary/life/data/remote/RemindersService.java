package com.vikashkothary.life.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.util.MyGsonTypeAdapterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface RemindersService {

    String ENDPOINT = "https://api.vikashkothary.com/";

    @GET("reminders")
    Observable<List<Reminder>> getReminders();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RemindersService newRemindersService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RemindersService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RemindersService.class);
        }
    }
}
