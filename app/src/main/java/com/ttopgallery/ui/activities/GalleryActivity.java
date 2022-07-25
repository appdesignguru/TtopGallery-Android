package com.ttopgallery.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.ttopgallery.R;
import com.ttopgallery.ui.fragments.GalleryFragment;
import dagger.hilt.android.AndroidEntryPoint;

/** Gallery Activity. */
@AndroidEntryPoint
public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.gallery_activity_fragment_container_view);

        if (fragment == null) {
            fragment = new GalleryFragment();
            fm.beginTransaction().add(R.id.gallery_activity_fragment_container_view, fragment).commit();
        }
    }
}
