package com.vikashkothary.life.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.vikashkothary.life.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "life_app_pref_file";

    private static final String PREF_KEY_SIGNED_IN = "PREF_KEY_SIGNED_IN";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putUserSignedIn(boolean signedIn) {
        mPref.edit().putBoolean(PREF_KEY_SIGNED_IN, signedIn).apply();
    }

    public boolean getUserSignedIn() {
        return mPref.getBoolean(PREF_KEY_SIGNED_IN, false);
    }
}
