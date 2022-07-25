package com.ttopgallery.ui.states;

/** UI state for reset password screen. */
public class ResetPasswordUiState extends AuthenticationUiState{


    /** Constructs a new instance. */
    public ResetPasswordUiState(AuthenticationUiStatus authenticationUiStatus,
                                String inputValidationErrorMessage) {
        super(authenticationUiStatus, inputValidationErrorMessage);
    }

}
