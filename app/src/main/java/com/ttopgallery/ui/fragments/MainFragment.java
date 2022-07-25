package com.ttopgallery.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.ttopgallery.R;
import com.ttopgallery.ui.activities.AuthenticationActivity;
import com.ttopgallery.ui.activities.GalleryActivity;
import com.ttopgallery.ui.viewmodels.fakes.FakeMainViewModel;
import com.ttopgallery.ui.viewmodels.interfaces.MainViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/** Main Fragment. Used for determining authentication state */
@AndroidEntryPoint
public class MainFragment extends Fragment {

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = view.findViewById(R.id.main_fragment_progressBar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(FakeMainViewModel.class);
        mainViewModel.getMainUiState().observe(
                getViewLifecycleOwner(), result -> {
                    if (result.isFetchingAuthenticationState()){
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        updateUI(result.hasActiveLoginToken());
                    }
                }
        );
    }

    private void updateUI(boolean hasActiveLoginToken) {
        if (hasActiveLoginToken){
            // Navigate to Gallery activity
            startActivity(new Intent(requireActivity(), GalleryActivity.class));
        }
        else {
            // Navigate to Authentication activity
            startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
        }
    }
}
