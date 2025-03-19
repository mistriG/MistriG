package com.example.mistrig.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.mistrig.BottomSheets.ContactBottomSheet
import com.example.mistrig.R

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_help, container, false)


        val showBottomSheetButton: Button = view.findViewById(R.id.chat_support_button)
        showBottomSheetButton.setOnClickListener {
            val bottomSheet = ContactBottomSheet()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }

        return view
    }

}