package com.ttopgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import com.ttopgallery.ui.fragments.MainFragment;
import dagger.hilt.android.AndroidEntryPoint;

/** Main Activity for app. */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_fragment_container_view);

        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction().add(R.id.main_activity_fragment_container_view, fragment).commit();
        }
    }
}