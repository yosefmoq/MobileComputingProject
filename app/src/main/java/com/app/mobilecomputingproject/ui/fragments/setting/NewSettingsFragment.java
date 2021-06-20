package com.app.mobilecomputingproject.ui.fragments.setting;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.mobilecomputingproject.R;
import com.app.mobilecomputingproject.helper.sharedPre.LocalSave;

import java.util.Locale;

public class NewSettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "NewSettingsFragment";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setLocale(requireActivity(),LocalSave.getInstance(requireContext()).getLang());
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case "language":
                String language = getPreferenceManager().getSharedPreferences().getString(key, "");
                LocalSave.getInstance(requireContext()).setLang(language);
                changeLanguage(language);
                break;
            case "theme":
                String theme = getPreferenceManager().getSharedPreferences().getString(key, "");
                changeTheme(theme);
                break;
            case "font_size":
                int fontSize = getPreferenceManager().getSharedPreferences().getInt(key, 14);
                changeFontSize(fontSize);
                break;
        }
    }

    private void changeLanguage(String language) {
        Log.d(TAG, "changeLanguage: " + language);
        setLocale(requireActivity(), language);
    }

    private void changeTheme(String theme) {
        Log.d(TAG, "changeTheme: " + theme);
        if (theme.equalsIgnoreCase("auto")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (theme.equalsIgnoreCase("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void changeFontSize(int fontSize) {
        Log.d(TAG, "changeFontSize: " + fontSize);
        adjustFontScale(getResources().getConfiguration(), fontSize);
    }

    public void setLocale(Activity activity, String languageCode) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(new Locale(languageCode));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            activity.getApplicationContext().createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    public void adjustFontScale(Configuration configuration, float scale) {
        configuration.fontScale = scale;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) requireActivity().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        requireActivity().getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }


    @Override
    public void onResume() {
        setLocale(requireActivity(),LocalSave.getInstance(requireContext()).getLang());
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}