package com.example.mistrig.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mistrig.R
import kotlin.system.exitProcess

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_home, container, false)
        activity?.finishAffinity()
        exitProcess(0)

        val plumberButton:LinearLayout=view.findViewById(R.id.btn_plumber_service)
        plumberButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_plumberServiceFragment)
        }

        val electricianButton:LinearLayout=view.findViewById(R.id.btn_electrician_services)
        electricianButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_electricianServiceFragment)
        }

        val CarpentryButton:LinearLayout=view.findViewById(R.id.btn_carpentry_service)
        CarpentryButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_carpentryServiceFragment)
        }

        val paintingButton:LinearLayout=view.findViewById(R.id.btn_painting_service)
        paintingButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_paintingServiceFragment)
        }

        val electronicButton:LinearLayout=view.findViewById(R.id.btn_electronic_service)
        electronicButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_electronicRepairServiceFragment)
        }

        val laundryButton:LinearLayout=view.findViewById(R.id.btn_laundry_service)
        laundryButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_laundryServiceFragment)
        }

        val welderButton:LinearLayout=view.findViewById(R.id.btn_welder)
        welderButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_welderServiceFragment)
        }

        return view
    }
}
