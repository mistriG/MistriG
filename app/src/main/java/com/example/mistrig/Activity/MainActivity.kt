package com.example.mistrig.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mistrig.R

class MainActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Redirect to Login activity after a delay
        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, Login::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}