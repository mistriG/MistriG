package com.example.mistrig.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.example.mistrig.R

class ResetPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_reset_password, container, false)

        val send_otp_Button: LinearLayout=view.findViewById(R.id.btn_send_otp)
        send_otp_Button.setOnClickListener{
            findNavController().navigate(R.id.action_resetPasswordFragment_to_createNewPasswordFragment)
        }

        return view
    }

}