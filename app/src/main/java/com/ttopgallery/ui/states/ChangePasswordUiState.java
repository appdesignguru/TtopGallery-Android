package com.ttopgallery.ui.states;

/** UI state for change password screen. */
public class ChangePasswordUiState extends AuthenticationUiState{

    /** Constructs a new instance. */
    public ChangePasswordUiState(AuthenticationUiStatus authenticationUiStatus,
                                 String errorMessage) {
        super(authenticationUiStatus, errorMessage);
    }

}
