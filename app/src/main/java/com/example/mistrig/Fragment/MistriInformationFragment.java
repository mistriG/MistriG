package com.example.mistrig.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mistrig.Activity.AppActivity;
import com.example.mistrig.R;

public class MistriInformationFragment extends Fragment {

    public MistriInformationFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mistri_information, container, false);

        Button btnContinue = view.findViewById(R.id.btn_continue_labour_info);
        btnContinue.setOnClickListener(v -> navigateToAppActivity());

        return view;
    }

    private void navigateToAppActivity() {
        Intent intent = new Intent(requireContext(), AppActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
