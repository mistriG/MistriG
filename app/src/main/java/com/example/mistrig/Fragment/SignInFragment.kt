package com.example.mistrig.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mistrig.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views
        val etMobileNumber: EditText = view.findViewById(R.id.edt_mobile)
        val btnContinue: Button = view.findViewById(R.id.btn_continue)

        // Set a click listener for the Continue button
        btnContinue.setOnClickListener {
            val mobileNumber = etMobileNumber.text.toString().trim()

            if (mobileNumber.isEmpty() || mobileNumber.length != 10) {
                Toast.makeText(requireContext(), "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            } else {
                sendOtp("+91$mobileNumber") // Add country code (e.g., +91 for India)
            }
        }
    }

    private fun sendOtp(mobileNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(mobileNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
            .setActivity(requireActivity()) // Activity for callback binding
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // Auto-retrieval or instant verification
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // Handle verification failure
            Toast.makeText(requireContext(), "Verification failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            // Save the verification ID and navigate to the VerificationScreen fragment
            this@SignInFragment.verificationId = verificationId

            // Pass the verificationId to the next fragment
            val bundle = Bundle()
            bundle.putString("verificationId", verificationId)

            val verificationScreen = VerificationScreen()
            verificationScreen.arguments = bundle

            // Navigate to VerificationScreen
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, verificationScreen)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // OTP verification successful
                    Toast.makeText(requireContext(), "Verification successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // OTP verification failed
                    Toast.makeText(requireContext(), "Verification failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}