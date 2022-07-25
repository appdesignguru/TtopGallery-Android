package com.ttopgallery.ui.states;

/** UI state for registration screen. */
public class RegistrationUiState extends AuthenticationUiState{

    /** Constructs a new instance. */
    public RegistrationUiState(AuthenticationUiStatus authenticationUiStatus,
                               String inputValidationErrorMessage) {
        super(authenticationUiStatus, inputValidationErrorMessage);
    }

}
