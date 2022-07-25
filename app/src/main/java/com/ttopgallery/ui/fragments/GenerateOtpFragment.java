package com.ttopgallery.ui.fragments;

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
import com.ttopgallery.ui.states.AuthenticationUiStatus;
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel;
import com.ttopgallery.ui.viewmodels.interfaces.AuthenticationViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/** Fragment for generating otp. */
@AndroidEntryPoint
public class GenerateOtpFragment extends Fragment {

    private AuthenticationViewModel authenticationViewModel;
    private EditText emailEditText;
    private Button submitButton;
    private ProgressBar progressBar;
    private TextView errorTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_generate_otp, container, false);

        emailEditText = view.findViewById(R.id.generate_otp_fragment_EditTextTextEmailAddress);
        submitButton = view.findViewById(R.id.generate_otp_fragment_submitButton);
        progressBar = view.findViewById(R.id.generate_otp_fragment_progressBar);
        errorTextView = view.findViewById(R.id.generate_otp_fragment_error_textView);

        submitButton.setOnClickListener(buttonView ->
                authenticationViewModel.generateOtp(emailEditText.getText().toString())
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticationViewModel = new ViewModelProvider(requireActivity()).get(FakeAuthenticationViewModel.class);
        authenticationViewModel.getGenerateOtpUiState().observe(
                getViewLifecycleOwner(), result ->
                        updateUI(result.getAuthenticationUiStatus(), result.getOtpType(),
                                result.getErrorMessage())
        );
    }

    private void updateUI(AuthenticationUiStatus authenticationUiStatus, OtpType otpType,
                          String errorMessage){
        switch (authenticationUiStatus){
            case GeneratingOtp:
                progressBar.setVisibility(View.VISIBLE);
                submitButton.setEnabled(false);
                break;
            case OtpGeneratedSuccessfully:
                navigateToAppropriateFragment(otpType);
                break;
            case InvalidInput:
                errorTextView.setText(errorMessage);
                progressBar.setVisibility(View.GONE);
                submitButton.setEnabled(true);
                break;
            default:
                progressBar.setVisibility(View.GONE);
                progressBar.setEnabled(true);
                break;
        }
    }

    private void navigateToAppropriateFragment(OtpType otpType){
        if (otpType == OtpType.Registration){
            navigateToRegistrationFragment();
        }
        else if (otpType == OtpType.ResetPassword){
            navigateToResetPasswordFragment();
        }
    }

    private void navigateToRegistrationFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.authentication_activity_fragment_container_view,
                        RegistrationFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null)  // name can be null
                .commit();
    }

    private void navigateToResetPasswordFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.authentication_activity_fragment_container_view,
                        ResetPasswordFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null)  // name can be null
                .commit();
    }
}