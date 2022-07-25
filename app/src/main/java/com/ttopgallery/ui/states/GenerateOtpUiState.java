package com.ttopgallery.ui.states;

import com.ttopgallery.localdatasources.entities.OtpType;

/** UI state for generate otp screen. */
public class GenerateOtpUiState extends AuthenticationUiState{

    private final OtpType otpType;

    /**  Constructs a new instance. */
    public GenerateOtpUiState(AuthenticationUiStatus authenticationUiStatus,
                              String inputValidationErrorMessage,
                              OtpType otpType) {
        super(authenticationUiStatus, inputValidationErrorMessage);
        this.otpType = otpType;
    }

    /** Returns OtpType. */
    public OtpType getOtpType() {
        return otpType;
    }
}
