package com.vikashkothary.life.injection.component;

import dagger.Subcomponent;
import com.vikashkothary.life.injection.PerActivity;
import com.vikashkothary.life.injection.module.ActivityModule;
import com.vikashkothary.life.ui.LauncherActivity;
import com.vikashkothary.life.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(LauncherActivity launcherActivity);

}
