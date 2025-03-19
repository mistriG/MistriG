package com.example.mistrig.BottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mistrig.databinding.FragmentContactBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContactBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentContactBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}