package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.util.Log;

/**
 * Created by Pablo Paletto on 24/10/2016.
 */

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        for (int i=0; i<getPreferenceScreen().getPreferenceCount(); i++){
            initSummary(getPreferenceScreen().getPreference(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("Preference chaged--->","True");
        updatePreferences(findPreference(key));
    }

    private void initSummary(Preference p){
        if (p instanceof PreferenceCategory){
            PreferenceCategory cat = (PreferenceCategory) p;
            for (int i=0; i<cat.getPreferenceCount(); i++){
                initSummary(cat.getPreference(i));
            }
        }
        else{
            updatePreferences(p);
        }
    }

    private void updatePreferences(Preference p){
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            p.setSummary(editTextPref.getText());
        }
    }
}
