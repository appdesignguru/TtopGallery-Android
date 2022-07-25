package com.ttopgallery.ui.states;

/** UI state for login screen. */
public class LoginUiState extends AuthenticationUiState{

    /** Constructs a new instance. */
    public LoginUiState(AuthenticationUiStatus authenticationUiStatus,
                        String inputValidationErrorMessage) {
        super(authenticationUiStatus, inputValidationErrorMessage);
    }

}
