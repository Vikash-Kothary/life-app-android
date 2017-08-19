package com.vikashkothary.life.injection.component;

import com.vikashkothary.life.injection.PerActivity;
import com.vikashkothary.life.injection.module.ActivityModule;
import com.vikashkothary.life.ui.LauncherActivity;
import com.vikashkothary.life.ui.main.MainActivity;
import com.vikashkothary.life.ui.ribot.RibotFragment;
import com.vikashkothary.life.ui.settings.SettingsActivity;
import com.vikashkothary.life.ui.settings.SettingsFragment;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    // Activities
    void inject(MainActivity mainActivity);

    void inject(LauncherActivity launcherActivity);

    void inject(SettingsActivity settingsActivity);

    // Fragments
    void inject(RibotFragment ribotFragment);

    void inject(SettingsFragment settingsFragment);

}
