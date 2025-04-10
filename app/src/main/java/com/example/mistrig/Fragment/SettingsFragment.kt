package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mistrig.Activity.Login
import com.example.mistrig.R
import com.google.android.material.imageview.ShapeableImageView

class SettingsFragment : Fragment() {

    private lateinit var profileImage: ShapeableImageView
    private lateinit var userName: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var addHomeBtn: TextView
    private lateinit var homeImage: ImageView
    private lateinit var addWorkBtn: TextView
    private lateinit var workImage: ImageView
    private lateinit var privacyBtn: TextView
    private lateinit var securityBtn: TextView
    private lateinit var signOutBtn: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

//        val profileButton: RelativeLayout = view.findViewById(R.id.profile_btn)
//        profileButton.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_settings_to_profileFragment)
//        }
//
//        val helpbutton: RelativeLayout=view.findViewById(R.id.btn_help_and_support)
//        helpbutton.setOnClickListener{
//            findNavController().navigate(R.id.action_navigation_settings_to_helpFragment)
//        }
//
//        val legalpolicyButton: RelativeLayout=view.findViewById(R.id.btn_legal_policy)
//        legalpolicyButton.setOnClickListener{
//            findNavController().navigate(R.id.action_navigation_settings_to_legalPolicyFragment)
//        }
//
//        val aboutButton: RelativeLayout=view.findViewById(R.id.btn_about)
//        aboutButton.setOnClickListener{
//            findNavController().navigate(R.id.action_navigation_settings_to_aboutFragment)
//        }
//
//        val notification_preference_Button:RelativeLayout=view.findViewById(R.id.btn_notification_preference)
//        notification_preference_Button.setOnClickListener{
//            findNavController().navigate(R.id.action_navigation_settings_to_notificationPreferenceFragment)
//        }
//
//        val resetPassworButton: RelativeLayout=view.findViewById(R.id.btn_reset_password)
//        resetPassworButton.setOnClickListener{
//            findNavController().navigate(R.id.action_navigation_settings_to_resetPasswordFragment)
//        }
//
//        val logoutButton: RelativeLayout=view.findViewById(R.id.btn_logout)
//        logoutButton.setOnClickListener{
//            val intent = Intent(requireActivity(), Login::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.profileImage = view.findViewById(R.id.profileImage)
        this.userName = view.findViewById(R.id.userName)
        this.phoneNumber = view.findViewById(R.id.phoneNumber)
        this.addHomeBtn = view.findViewById(R.id.addHomeBtn)
        this.homeImage = view.findViewById(R.id.homeImage)
        this.addWorkBtn = view.findViewById(R.id.addWorkBtn)
        this.workImage = view.findViewById(R.id.workImage)
        this.privacyBtn = view.findViewById(R.id.privacyBtn)
        this.securityBtn = view.findViewById(R.id.securityBtn)
        this.signOutBtn = view.findViewById(R.id.signOutBtn)

        this.registerClickHandlers()

    }

    private fun registerClickHandlers() {
        this.profileImage.setOnClickListener {
            loadFragment(ProfileFragment())
        }
        this.userName.setOnClickListener {
            loadFragment(ProfileFragment())
        }
        this.phoneNumber.setOnClickListener {
            // Not sure what to do here
        }
        this.addHomeBtn.setOnClickListener {
            // Not implemented yet
        }
        this.homeImage.setOnClickListener {
            // Not implemented yet
        }
        this.addWorkBtn.setOnClickListener {
            // Not implemented yet
        }
        this.workImage.setOnClickListener {
            // Not implemented yet
        }
        this.privacyBtn.setOnClickListener {
            // Not implemented yet
        }
        this.securityBtn.setOnClickListener {
            // Not implemented yet
        }
        this.signOutBtn.setOnClickListener {
            val intent = Intent(requireActivity(), Login::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        this.parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}