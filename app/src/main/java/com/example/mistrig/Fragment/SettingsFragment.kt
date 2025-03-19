package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import com.example.mistrig.Activity.Login
import com.example.mistrig.R

class SettingsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val profileButton: RelativeLayout = view.findViewById(R.id.profile_btn)
        profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_profileFragment)
        }

        val helpbutton: RelativeLayout=view.findViewById(R.id.btn_help_and_support)
        helpbutton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_settings_to_helpFragment)
        }

        val legalpolicyButton: RelativeLayout=view.findViewById(R.id.btn_legal_policy)
        legalpolicyButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_settings_to_legalPolicyFragment)
        }

        val aboutButton: RelativeLayout=view.findViewById(R.id.btn_about)
        aboutButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_settings_to_aboutFragment)
        }

        val notification_preference_Button:RelativeLayout=view.findViewById(R.id.btn_notification_preference)
        notification_preference_Button.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_settings_to_notificationPreferenceFragment)
        }

        val resetPassworButton: RelativeLayout=view.findViewById(R.id.btn_reset_password)
        resetPassworButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_settings_to_resetPasswordFragment)
        }

        val logoutButton: RelativeLayout=view.findViewById(R.id.btn_logout)
        logoutButton.setOnClickListener{
            val intent = Intent(requireActivity(), Login::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

}