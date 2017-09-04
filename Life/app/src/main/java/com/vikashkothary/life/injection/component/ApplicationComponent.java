package com.vikashkothary.life.injection.component;

import android.app.Application;
import android.content.Context;

import com.vikashkothary.life.data.DataManager;
import com.vikashkothary.life.data.SyncService;
import com.vikashkothary.life.data.local.DatabaseHelper;
import com.vikashkothary.life.data.local.PreferencesHelper;
import com.vikashkothary.life.data.remote.RibotsService;
import com.vikashkothary.life.injection.ApplicationContext;
import com.vikashkothary.life.injection.module.ApplicationModule;
import com.vikashkothary.life.services.OnBootReceiver;
import com.vikashkothary.life.util.RxEventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);
//    void inject(PomodoroService pomodoroService);

    void inject(OnBootReceiver myBroadcastReceiver);


    @ApplicationContext
    Context context();

    Application application();

    RibotsService ribotsService();

    PreferencesHelper preferencesHelper();

    DatabaseHelper databaseHelper();

    DataManager dataManager();

    RxEventBus eventBus();

}
