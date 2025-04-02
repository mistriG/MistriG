package com.example.mistrig.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mistrig.R

class MainActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val animation: android.view.animation.Animation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.translate_up)
        val image = findViewById<ImageView>(R.id.activity_main_logo)
        image.startAnimation(animation)
        // Redirect to Login activity after a delay
        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, Login::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}