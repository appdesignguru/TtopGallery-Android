package com.ttopgallery.ui.states;

/** UI state for main screen. */
public class MainUiState {

    private final boolean fetchingAuthenticationState;
    private final boolean activeLoginToken;

    /** Constructs a new instance. */
    public MainUiState(boolean fetchingAuthenticationState, boolean activeLoginToken) {
        this.fetchingAuthenticationState = fetchingAuthenticationState;
        this.activeLoginToken = activeLoginToken;
    }

    /** Checks whether authentication is being currently fetched. */
    public boolean isFetchingAuthenticationState() {
        return fetchingAuthenticationState;
    }

    /** Checks whether active login token exists. */
    public boolean hasActiveLoginToken() {
        return activeLoginToken;
    }
}
