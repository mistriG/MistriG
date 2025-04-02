package com.example.mistrig.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mistrig.Fragment.Dashboard

import com.example.mistrig.R
import com.example.mistrig.Fragment.SettingsFragment
import com.example.mistrig.Fragment.WalletFragment
import com.example.mistrig.Fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)


        if (savedInstanceState == null) {
            loadFragment(Dashboard())
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> loadFragment(Dashboard())
                R.id.navigation_settings -> loadFragment(SettingsFragment())
                R.id.navigation_wallet -> loadFragment(WalletFragment())
                R.id.navigation_search -> loadFragment(SearchFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
