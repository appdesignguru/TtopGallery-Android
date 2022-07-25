package com.ttopgallery.ui.viewmodels.fakes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ttopgallery.localdatasources.entities.OtpType;
import com.ttopgallery.ui.states.AuthenticationUiStatus;
import com.ttopgallery.ui.states.ChangePasswordUiState;
import com.ttopgallery.ui.states.GenerateOtpUiState;
import com.ttopgallery.ui.states.LoginUiState;
import com.ttopgallery.ui.states.RegistrationUiState;
import com.ttopgallery.ui.states.ResetPasswordUiState;
import com.ttopgallery.ui.viewmodels.interfaces.AuthenticationViewModel;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

/** Fake AuthenticationViewModel implementation class. */
@HiltViewModel
public class FakeAuthenticationViewModel extends ViewModel implements AuthenticationViewModel {

    private final MutableLiveData<LoginUiState> loginUiState = new MutableLiveData<>();
    private final MutableLiveData<GenerateOtpUiState> generateOtpUiState = new MutableLiveData<>();
    private final MutableLiveData<RegistrationUiState> registrationUiState = new MutableLiveData<>();
    private final MutableLiveData<ResetPasswordUiState> resetPasswordUiState = new MutableLiveData<>();
    private final MutableLiveData<ChangePasswordUiState> changePasswordUiState = new MutableLiveData<>();
    private OtpType otpType;

    /** Constructs a new instance. */
    @Inject
    public FakeAuthenticationViewModel() {

    }

    @Override
    public MutableLiveData<LoginUiState> getLoginUiState() {
        loginUiState.setValue(new LoginUiState(AuthenticationUiStatus.Idle, null));
        return loginUiState;
    }

    @Override
    public MutableLiveData<GenerateOtpUiState> getGenerateOtpUiState() {
        generateOtpUiState.setValue(
                new GenerateOtpUiState(AuthenticationUiStatus.Idle, null, otpType));
        return generateOtpUiState;
    }

    @Override
    public MutableLiveData<RegistrationUiState> getRegistrationUiState() {
        registrationUiState.setValue(new RegistrationUiState(AuthenticationUiStatus.Idle, null));
        return registrationUiState;
    }

    @Override
    public MutableLiveData<ResetPasswordUiState> getResetPasswordUiState() {
        resetPasswordUiState.setValue(new ResetPasswordUiState(AuthenticationUiStatus.Idle, null));
        return resetPasswordUiState;
    }

    @Override
    public MutableLiveData<ChangePasswordUiState> getChangePasswordUiState() {
        changePasswordUiState.setValue(new ChangePasswordUiState(AuthenticationUiStatus.Idle, null));
        return changePasswordUiState;
    }

    @Override
    public void login(String email, String password) {
        //Assumes successful login
        loginUiState.setValue(new LoginUiState(AuthenticationUiStatus.LoginSuccessful, null));
    }

    @Override
    public void generateOtp(String email) {
        //Assumes successful otp generation
        generateOtpUiState.setValue(
                new GenerateOtpUiState(AuthenticationUiStatus.OtpGeneratedSuccessfully,
                        null, otpType)
        );
    }

    @Override
    public void register(String password, String confirmPassword, String otpCode) {
        //Assumes successful user registration
        registrationUiState.setValue(
                new RegistrationUiState(AuthenticationUiStatus.LoginSuccessful, null)
        );
    }

    @Override
    public void resetPassword(String newPassword, String confirmNewPassword, String otpCode) {
        //Assumes successful password reset
        resetPasswordUiState.setValue(
                new ResetPasswordUiState(AuthenticationUiStatus.LoginSuccessful, null)
        );
    }

    @Override
    public void changePassword(String email, String currentPassword, String newPassword, String confirmNewPassword) {
        //Assumes successful password change
        changePasswordUiState.setValue(
                new ChangePasswordUiState(AuthenticationUiStatus.ChangePasswordSuccessful, null)
        );
    }

    @Override
    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }
}
