package net.onyxmueller.android.fruity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceManager;

import net.onyxmueller.android.fruity.reminders.AlarmReceiver;

public class SettingsFragment extends PreferenceFragment {

    private SharedPreferences prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if(key.equals("key_reminder") || key.equals("key_alarm")) {
                    AlarmReceiver.scheduleAlarm(getActivity());
                }
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    public void onPause() {
        super.onPause();

        prefs.unregisterOnSharedPreferenceChangeListener(prefListener);
    }
}
