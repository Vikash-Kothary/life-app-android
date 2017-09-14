package com.vikashkothary.life.injection.component;

import com.vikashkothary.life.injection.PerActivity;
import com.vikashkothary.life.injection.module.ActivityModule;
import com.vikashkothary.life.ui.LauncherActivity;
import com.vikashkothary.life.ui.about.AboutActivity;
import com.vikashkothary.life.ui.about.AboutFragment;
import com.vikashkothary.life.ui.creation.CreationFragment;
import com.vikashkothary.life.ui.creation.CreationActivity;
import com.vikashkothary.life.ui.login.LoginActivity;
import com.vikashkothary.life.ui.login.LoginFragment;
import com.vikashkothary.life.ui.login.RegisterFragment;
import com.vikashkothary.life.ui.main.MainActivity;
import com.vikashkothary.life.ui.ribot.RibotFragment;
import com.vikashkothary.life.ui.settings.SettingsActivity;
import com.vikashkothary.life.ui.settings.SettingsFragment;
import com.vikashkothary.life.ui.stream.StreamFragment;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    // Activities
    void inject(LauncherActivity launcherActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(CreationActivity creationActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(AboutActivity aboutActivity);

    // Fragments
    void inject(RibotFragment ribotFragment);

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(StreamFragment streamFragment);

    void inject(CreationFragment creationFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(AboutFragment aboutFragment);
}
