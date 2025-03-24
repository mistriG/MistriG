package com.example.mistrig.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mistrig.Activity.AppActivity;
import com.example.mistrig.R;

public class RoleSelectionFragment extends Fragment {

    private RadioGroup radioGroup;
    private Button btnContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_role_selection_main, container, false);

        radioGroup = view.findViewById(R.id.radio_btn_group);
        btnContinue = view.findViewById(R.id.role_btn_continue);

        btnContinue.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radio_btn_user) {
                // Navigate to AppActivity
                navigateToAppActivity();
            } else if (selectedId == R.id.radio_btn_labour) {
                // Replace fragment with MistriInformationFragment
                navigateToMistriInformationFragment();
            } else {
                Toast.makeText(getContext(), "Please select a role", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void navigateToAppActivity() {
        Intent intent = new Intent(requireContext(), AppActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void navigateToMistriInformationFragment() {
        MistriInformationFragment mistriInformationFragment = new MistriInformationFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, mistriInformationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
