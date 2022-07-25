package com.ttopgallery.ui.states;

/** Base UI state class for Authentication UI screens. */
public class AuthenticationUiState {

    private final AuthenticationUiStatus authenticationUiStatus;
    private final String errorMessage;

    /** Constructs a new instance. */
    public AuthenticationUiState(AuthenticationUiStatus authenticationUiStatus,
                        String errorMessage) {
        this.authenticationUiStatus = authenticationUiStatus;
        this.errorMessage = errorMessage;
    }

    /** Returns authentication ui status. */
    public AuthenticationUiStatus getAuthenticationUiStatus() {
        return authenticationUiStatus;
    }

    /** Returns input validation error message. */
    public String getErrorMessage() {
        return errorMessage;
    }

}
