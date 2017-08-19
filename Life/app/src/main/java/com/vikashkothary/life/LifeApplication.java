package com.vikashkothary.life;

import android.app.Application;
import android.content.Context;

import com.vikashkothary.life.injection.component.ApplicationComponent;
import com.vikashkothary.life.injection.component.DaggerApplicationComponent;
import com.vikashkothary.life.injection.module.ApplicationModule;

import timber.log.Timber;

public class LifeApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static LifeApplication get(Context context) {
        return (LifeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
//            Fabric.with(this, new Crashlytics());
        }
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
