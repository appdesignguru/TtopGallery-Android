package com.ttopgallery.ui.states;

import com.ttopgallery.localdatasources.entities.UserRole;

/** UI state for Gallery home screen. */
public class GalleryUiState {

    private final boolean fetchingUserRole;
    private final UserRole userRole;

    public GalleryUiState(boolean fetchingUserRole, UserRole userRole) {
        this.fetchingUserRole = fetchingUserRole;
        this.userRole = userRole;
    }

    public boolean isFetchingUserRole() {
        return fetchingUserRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
