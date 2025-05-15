package com.example.mistrig.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mistrig.Activity.AppActivity;
import com.example.mistrig.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificationScreen extends Fragment {

    private EditText[] otpInputs = new EditText[6];
    private Button btnContinue;
    private TextView txtResend;
    private String verificationId;

    public VerificationScreen() {

    }
    private void openActivity() {
        Intent intent = new Intent(requireContext(), AppActivity.class);
        startActivity(intent);
    }
    private void openRoleSelectionFragment() {
        RoleSelectionFragment roleSelectionFragment = new RoleSelectionFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, roleSelectionFragment);
        transaction.addToBackStack(null); // Allows back navigation
        transaction.commit();
    }

    public static VerificationScreen newInstance(String verificationId) {
        VerificationScreen fragment = new VerificationScreen();
        Bundle args = new Bundle();
        args.putString("verificationId", verificationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            verificationId = getArguments().getString("verificationId");
        }
        getActivity().finishAffinity();
        System.exit(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_screen, container, false);

        // Reference EditTexts
        otpInputs[0] = view.findViewById(R.id.otp1);
        otpInputs[1] = view.findViewById(R.id.otp2);
        otpInputs[2] = view.findViewById(R.id.otp3);
        otpInputs[3] = view.findViewById(R.id.otp4);
        otpInputs[4] = view.findViewById(R.id.otp5);
        otpInputs[5] = view.findViewById(R.id.otp6);

        btnContinue = view.findViewById(R.id.btn_continue);
        txtResend = view.findViewById(R.id.txt_resend);


        for (int i = 0; i < otpInputs.length; i++) {
            final int index = i;
            otpInputs[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < otpInputs.length - 1) {
                        otpInputs[index + 1].requestFocus();
                    } else if (s.length() == 0 && index > 0) {
                        otpInputs[index - 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }


        btnContinue.setOnClickListener(v -> {
            StringBuilder otpCode = new StringBuilder();
            for (EditText otpInput : otpInputs) {
                if (otpInput.getText().toString().trim().isEmpty()) {
                    otpInput.setError("Enter OTP");
                    return;
                }
                otpCode.append(otpInput.getText().toString().trim());
            }
            verifyCode(otpCode.toString());
        });


        txtResend.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Resending OTP...", Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    private void verifyCode(String code) {
        if (verificationId == null) {
            Toast.makeText(getContext(), "Verification ID is null", Toast.LENGTH_SHORT).show();
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Verification Successful!", Toast.LENGTH_SHORT).show();

//                    openActivity();
                    // method tp level pe bana hai to open activity instead of homefragment
                    openRoleSelectionFragment();
                } else {
                    Toast.makeText(getContext(), "Verification Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}