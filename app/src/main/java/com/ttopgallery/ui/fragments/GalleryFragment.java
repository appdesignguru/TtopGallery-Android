package com.ttopgallery.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.ttopgallery.R;
import dagger.hilt.android.AndroidEntryPoint;

/** Fragment for gallery home screen. */
@AndroidEntryPoint
public class GalleryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button imagesButton = view.findViewById(R.id.gallery_fragment_imagesButton);
        Button videosButton = view.findViewById(R.id.gallery_fragment_videosButton);
        imagesButton.setOnClickListener(buttonView -> navigateToImageListFragment());
        videosButton.setOnClickListener(buttonView -> navigateToVideoListFragment());

        return view;
    }

    private void navigateToImageListFragment(){

    }

    private void navigateToVideoListFragment(){

    }
}
