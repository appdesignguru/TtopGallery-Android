package com.ttopgallery.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.ttopgallery.R;
import com.ttopgallery.ui.fragments.LoginFragment;
import dagger.hilt.android.AndroidEntryPoint;

/** Authentication activity. */
@AndroidEntryPoint
public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.authentication_activity_fragment_container_view);

        if (fragment == null) {
            fragment = new LoginFragment();
            fm.beginTransaction().add(R.id.authentication_activity_fragment_container_view, fragment).commit();
        }
    }
}
