package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mistrig.Activity.Login
import com.example.mistrig.R

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val profileName: TextView = view.findViewById(R.id.profile_name)
        val profileEmail: TextView = view.findViewById(R.id.profile_email)
        val editProfileButton: Button = view.findViewById(R.id.edit_profile_button)
        val recentService1: TextView = view.findViewById(R.id.recent_service_1)
        val coupon1: TextView = view.findViewById(R.id.coupon_1)

        // Set up data
        profileName.text = "John Doe"
        profileEmail.text = "john.doe@example.com"
        recentService1.text = "Home Cleaning"
        coupon1.text = "50% Off - XYZ123"
        // Load image using an image loading library like Glide or Picasso if needed

        // Set up edit profile button click listener

        val logoutButton: Button =view.findViewById(R.id.logout_button)
        logoutButton.setOnClickListener{
            val intent = Intent(requireActivity(), Login::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

}