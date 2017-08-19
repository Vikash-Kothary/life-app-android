package com.vikashkothary.life.ui.settings;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;

/**
 * The Settings Fragment which shows the Preferences as a List and handles the Dialogs for the
 * Preferences.
 *
 * @author Vikash Kothary
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.app_preference);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisplayPreferenceDialog(Preference pref) {

        if (pref instanceof ListPreference) {
            ListPreference listPref = (ListPreference) pref;
            pref.setSummary(listPref.getEntry());
            return;
        }

        if (pref instanceof EditTextPreference){
            EditTextPreference editPref =  (EditTextPreference) pref;
            editPref.setSummary(editPref.getText());
            return;
        }

//        if (pref instanceof RingtonePreference) {
//            Uri ringtoneUri = Uri.parse(RemindMe.getRingtone());
//            Ringtone ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
//            if (ringtone != null) pref.setSummary(ringtone.getTitle(this));
//        }

//        // Try if the preference is one of our custom Preferences
//        DialogFragment dialogFragment = null;
//        if (preference instanceof TimePreference) {
//            // Create a new instance of TimePreferenceDialogFragment with the key of the related
//            // Preference
//            dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey());
//        }
//
//
//        if (dialogFragment != null) {
//            // The dialog was created (it was one of our custom Preferences), show the dialog for it
//            dialogFragment.setTargetFragment(this, 0);
//            dialogFragment.show(this.getFragmentManager(), "android.support.v7.preference" +
//                    ".PreferenceFragment.DIALOG");
//        } else {
//            // Dialog creation could not be handled here. Try with the super method.
//            super.onDisplayPreferenceDialog(preference);
//        }

    }
}