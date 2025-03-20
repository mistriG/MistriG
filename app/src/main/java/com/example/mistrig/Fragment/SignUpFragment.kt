package com.example.mistrig.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mistrig.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val btnContinue: Button = view.findViewById(R.id.btn_continue)
        btnContinue.setOnClickListener {
            openHomeFragment()
        }

        return view
    }

    private fun openHomeFragment() {
        val homeFragment = HomeFragment()
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, homeFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
