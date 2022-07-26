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

/** Fragment for resetting user password. */
@AndroidEntryPoint
public class ResetPasswordFragment extends Fragment {

    private AuthenticationViewModel authenticationViewModel;
    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;
    private EditText otpCodeEditText;
    private TextView errorTextView;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        newPasswordEditText = view.findViewById(R.id.reset_password_fragment_EditTextTextPassword);
        confirmNewPasswordEditText = view.findViewById(R.id.reset_password_fragment_confirm_new_EditTextTextPassword);
        otpCodeEditText = view.findViewById(R.id.reset_password_fragment_otp_editTextNumber);
        errorTextView = view.findViewById(R.id.reset_password_fragment_error_textView);
        resetPasswordButton = view.findViewById(R.id.reset_password_fragment_resetPasswordButton);
        progressBar = view.findViewById(R.id.reset_password_fragment_progressBar);

        resetPasswordButton.setOnClickListener(buttonView ->
            authenticationViewModel.resetPassword(newPasswordEditText.getText().toString(),
                    confirmNewPasswordEditText.getText().toString(), otpCodeEditText.getText().toString())
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticationViewModel = new ViewModelProvider(requireActivity()).get(FakeAuthenticationViewModel.class);
        authenticationViewModel.getResetPasswordUiState().observe(getViewLifecycleOwner(),
                result -> updateUI(result.getAuthenticationUiStatus(), result.getErrorMessage())
        );
    }

    private void updateUI(AuthenticationUiStatus authenticationUiStatus, String errorMessage){
        switch (authenticationUiStatus){
            case ResettingPassword:
            case LoggingIn:
                progressBar.setVisibility(View.VISIBLE);
                resetPasswordButton.setEnabled(false);
                break;
            case LoginSuccessful:
                startActivity(new Intent(requireActivity(), GalleryActivity.class));
                break;
            case InvalidInput:
            case Failure:
                errorTextView.setText(errorMessage);
                progressBar.setVisibility(View.GONE);
                resetPasswordButton.setEnabled(true);
                break;
            default:
                progressBar.setVisibility(View.GONE);
                progressBar.setEnabled(true);
                break;
        }
    }
}
