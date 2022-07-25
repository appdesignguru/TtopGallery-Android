package com.ttopgallery.ui.viewmodels.interfaces;

import androidx.lifecycle.MutableLiveData;
import com.ttopgallery.ui.states.MainUiState;

/** State holder interface for the main UI screen. */
public interface MainViewModel {

    /** Returns MainUiState. */
    MutableLiveData<MainUiState> getMainUiState();

}
