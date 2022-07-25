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
import com.ttopgallery.localdatasources.entities.OtpType;
import com.ttopgallery.ui.activities.GalleryActivity;
import com.ttopgallery.ui.states.AuthenticationUiStatus;
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel;
import com.ttopgallery.ui.viewmodels.interfaces.AuthenticationViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/** Fragment for user log in. */
@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private AuthenticationViewModel authenticationViewModel;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView errorTextView;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailEditText = view.findViewById(R.id.login_fragment_EditTextTextEmailAddress);
        passwordEditText = view.findViewById(R.id.login_fragment_EditTextTextPassword);
        errorTextView = view.findViewById(R.id.login_fragment_error_textView);
        progressBar = view.findViewById(R.id.login_fragment_progressBar);
        loginButton = view.findViewById(R.id.login_fragment_loginButton);
        TextView registerLinkTextView = view.findViewById(R.id.login_fragment_register_link_textView);
        TextView forgotPasswordLinkTextView = view.findViewById(R.id.login_fragment_forgot_password_textView);

        loginButton.setOnClickListener(buttonView ->
                authenticationViewModel.login(emailEditText.getText().toString(),
                                        passwordEditText.getText().toString())
        );

        registerLinkTextView.setOnClickListener(textView ->
                navigateToGenerateOtpFragment(OtpType.Registration)
        );

        forgotPasswordLinkTextView.setOnClickListener(textView ->
                navigateToGenerateOtpFragment(OtpType.ResetPassword)
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticationViewModel = new ViewModelProvider(requireActivity()).get(FakeAuthenticationViewModel.class);
        authenticationViewModel.getLoginUiState().observe(
                getViewLifecycleOwner(), result ->
                        updateUI(result.getAuthenticationUiStatus(), result.getErrorMessage())
        );
    }

    private void updateUI(AuthenticationUiStatus authenticationUiStatus, String inputValidationErrorMessage){
        switch (authenticationUiStatus){
            case LoggingIn:
                progressBar.setVisibility(View.VISIBLE);
                loginButton.setEnabled(false);
                break;
            case LoginSuccessful:
                startActivity(new Intent(requireActivity(), GalleryActivity.class));
                break;
            case InvalidInput:
                errorTextView.setText(inputValidationErrorMessage);
                progressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);
                break;
            default:
                progressBar.setVisibility(View.GONE);
                progressBar.setEnabled(true);
                break;
        }
    }

    private void navigateToGenerateOtpFragment(OtpType otpType){
        if (authenticationViewModel != null){
            authenticationViewModel.setOtpType(otpType);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.authentication_activity_fragment_container_view,
                            GenerateOtpFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)  // name can be null
                    .commit();
        }
    }
}