package com.app.mobilecomputingproject.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.app.mobilecomputingproject.R;
import com.app.mobilecomputingproject.databinding.ActivityMainBinding;
import com.app.mobilecomputingproject.helper.sharedPre.LocalSave;
import com.app.mobilecomputingproject.ui.fragments.history.HistoryFragment;
import com.app.mobilecomputingproject.ui.fragments.home.HomeFragment;
import com.app.mobilecomputingproject.ui.fragments.movies.MoviesFragment;
import com.app.mobilecomputingproject.ui.fragments.setting.NewSettingsFragment;
import com.app.mobilecomputingproject.ui.fragments.setting.SettingFragment;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    BottomNavigationAdapter bottomNavigationAdapter;
    ActivityMainBinding activityMainBinding;
    private MenuItem prevMenuItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLocale(MainActivity.this, LocalSave.getInstance(MainActivity.this).getLang());
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this), null, false);

        setContentView(activityMainBinding.getRoot());
        bottomNavigationAdapter = new BottomNavigationAdapter(MainActivity.this);
        activityMainBinding.vp2.setAdapter(bottomNavigationAdapter);

        activityMainBinding.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setCheckable(false);
                } else {
                    activityMainBinding.bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                activityMainBinding.bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = activityMainBinding.bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.news:
                    activityMainBinding.vp2.setCurrentItem(0);
                    break;
                case R.id.short_movies:
                    activityMainBinding.vp2.setCurrentItem(1);
                    break;
                case R.id.Historical_events:
                    activityMainBinding.vp2.setCurrentItem(2);
                    break;
                case R.id.settings:
                    activityMainBinding.vp2.setCurrentItem(3);


            }
            return false;
        });
    }

    public static Intent getMainIntent(Activity activity) {
        return new Intent(activity, MainActivity.class);

    }

    public class BottomNavigationAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> fragments = new ArrayList<>();

        public BottomNavigationAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            fragments.add(HomeFragment.newInstance());
            fragments.add(MoviesFragment.newInstance());
            fragments.add(HistoryFragment.newInstance());
            fragments.add(new NewSettingsFragment());

        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

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

}