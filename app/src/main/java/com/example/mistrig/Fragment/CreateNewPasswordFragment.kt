package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.mistrig.Activity.Login
import com.example.mistrig.R

class CreateNewPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_create_new_password, container, false)

        val submit_button: LinearLayout=view.findViewById(R.id.btn_submit)
        submit_button.setOnClickListener{
            val intent = Intent(requireActivity(), Login::class.java)
            startActivity(intent)
            requireActivity().finish()
            Toast.makeText(requireContext(),"Password Resetted",Toast.LENGTH_SHORT).show()
        }

        return view
    }

}