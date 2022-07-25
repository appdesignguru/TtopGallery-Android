package com.ttopgallery.ui.viewmodels.fakes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ttopgallery.ui.states.MainUiState;
import com.ttopgallery.ui.viewmodels.interfaces.MainViewModel;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

/** Fake MainViewModel implementation class. */
@HiltViewModel
public class FakeMainViewModel extends ViewModel implements MainViewModel {

    /** Constructs a new instance. */
    @Inject
    public FakeMainViewModel(){

    }

    @Override
    public MutableLiveData<MainUiState> getMainUiState() {
        MutableLiveData<MainUiState> mainUiState = new MutableLiveData<>();
        mainUiState.setValue(new MainUiState(false, false));
        return mainUiState;
    }
}
