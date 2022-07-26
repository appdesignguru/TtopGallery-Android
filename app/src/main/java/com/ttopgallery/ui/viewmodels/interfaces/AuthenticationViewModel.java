package com.ttopgallery.ui.viewmodels.interfaces;

import androidx.lifecycle.MutableLiveData;
import com.ttopgallery.localdatasources.entities.OtpType;
import com.ttopgallery.ui.states.ChangePasswordUiState;
import com.ttopgallery.ui.states.GenerateOtpUiState;
import com.ttopgallery.ui.states.LoginUiState;
import com.ttopgallery.ui.states.RegistrationUiState;
import com.ttopgallery.ui.states.ResetPasswordUiState;

/** State holder interface for the authentication UI screens. */
public interface AuthenticationViewModel {

    /** Returns LoginUiState. */
    MutableLiveData<LoginUiState> getLoginUiState();

    /** Returns GenerateOtpUiState. */
    MutableLiveData<GenerateOtpUiState> getGenerateOtpUiState();

    /** Returns GenerateUiState. */
    MutableLiveData<RegistrationUiState> getRegistrationUiState();

    /** Returns ResetPasswordUiState. */
    MutableLiveData<ResetPasswordUiState> getResetPasswordUiState();

    /** Returns ChangePasswordUiState. */
    MutableLiveData<ChangePasswordUiState> getChangePasswordUiState();

    /** Attempts to login user. */
    void login(String email, String password);

    /** Attempts to generate one-time-password. */
    void generateOtp(String email);

    /** Attempts to register user. */
    void register(String password, String confirmPassword, String otpCode);

    /** Attempts to reset password. */
    void resetPassword(String newPassword, String confirmNewPassword, String otpCode);

    /** Attempts to change password */
    void changePassword(String email, String currentPassword, String newPassword, String confirmNewPassword);

    /** Sets otpType. */
    void setOtpType(OtpType otpType);
}
