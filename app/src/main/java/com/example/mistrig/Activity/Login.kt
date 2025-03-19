package com.example.mistrig.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mistrig.Fragment.SignInFragment
import com.example.mistrig.Fragment.SignUpFragment
import com.example.mistrig.Fragment.VerificationScreen
import com.example.mistrig.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadFragment(SignInFragment()) // Load SignInFragment on startup
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Enables back navigation
            .commit()
    }

    fun moveToVerificationScreen() {
        loadFragment(VerificationScreen()) // Call this from SignInFragment when needed
    }
    fun moveToSignUpScreen() {
        loadFragment(SignUpFragment()) // Load SignUpFragment when btn_email is clicked
    }
}
