package com.example.mistrig.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mistrig.R

class MainActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Animate logo from bottom to center
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_up)
        val image = findViewById<ImageView>(R.id.activity_main_logo)
        image.startAnimation(logoAnimation)
        
        // Animate "Mistri" from left to center
        val leftToCenter = AnimationUtils.loadAnimation(this, R.anim.slide_left_to_center)
        val textMistri = findViewById<TextView>(R.id.text_mistri)
        textMistri.startAnimation(leftToCenter)
        
        // Animate "G" from right to center
        val rightToCenter = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_center)
        val textG = findViewById<TextView>(R.id.text_g)
        textG.startAnimation(rightToCenter)

        // Redirect to Login activity after a delay
        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, Login::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}