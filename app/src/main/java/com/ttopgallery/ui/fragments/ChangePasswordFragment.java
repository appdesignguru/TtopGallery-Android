package com.ttopgallery.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.ttopgallery.R;
import com.ttopgallery.ui.activities.GalleryActivity;
import com.ttopgallery.ui.states.AuthenticationUiStatus;
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel;
import com.ttopgallery.ui.viewmodels.interfaces.AuthenticationViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/** Fragment for changing user password. */
@AndroidEntryPoint
public class ChangePasswordFragment extends Fragment {

    private AuthenticationViewModel authenticationViewModel;
    private EditText emailEditText;
    private EditText currentPasswordEditText;
    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;
    private TextView errorTextView;
    private Button changePasswordButton;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        emailEditText = view.findViewById(R.id.change_password_fragment_EditTextTextEmailAddress);
        currentPasswordEditText = view.findViewById(R.id.change_password_fragment_current_EditTextTextPassword);
        newPasswordEditText = view.findViewById(R.id.change_password_fragment_new_EditTextTextPassword);
        confirmNewPasswordEditText = view.findViewById(R.id.change_password_fragment_confirm_new_EditTextTextPassword);
        errorTextView = view.findViewById(R.id.change_password_fragment_error_textView);
        changePasswordButton = view.findViewById(R.id.change_password_fragment_changePasswordButton);
        progressBar = view.findViewById(R.id.reset_password_fragment_progressBar);

        changePasswordButton.setOnClickListener(buttonView -> {
            authenticationViewModel.changePassword(
                    emailEditText.getText().toString(), currentPasswordEditText.getText().toString(),
                    newPasswordEditText.getText().toString(), confirmNewPasswordEditText.getText().toString()
            );
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticationViewModel = new ViewModelProvider(requireActivity()).get(FakeAuthenticationViewModel.class);
        authenticationViewModel.getChangePasswordUiState().observe(
                getViewLifecycleOwner(), result ->
                        updateUI(result.getAuthenticationUiStatus(), result.getErrorMessage())
        );
    }

    private void updateUI(AuthenticationUiStatus authenticationUiStatus, String errorMessage){
        switch (authenticationUiStatus){
            case ChangingPassword:
            case LoggingIn:
                progressBar.setVisibility(View.VISIBLE);
                changePasswordButton.setEnabled(false);
                break;
            case LoginSuccessful:
                startActivity(new Intent(requireActivity(), GalleryActivity.class));
                break;
            case InvalidInput:
            case Failure:
                errorTextView.setText(errorMessage);
                progressBar.setVisibility(View.GONE);
                changePasswordButton.setEnabled(true);
                break;
            default:
                progressBar.setVisibility(View.GONE);
                progressBar.setEnabled(true);
                break;
        }
    }

}
