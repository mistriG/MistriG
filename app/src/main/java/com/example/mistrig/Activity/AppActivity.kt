package com.example.mistrig.Activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.mistrig.Fragment.Dashboard
import com.example.mistrig.Fragment.SettingsFragment
import com.example.mistrig.Fragment.WalletFragment
import com.example.mistrig.Fragment.SearchFragment
import com.example.mistrig.Fragment.SideBarMenuDrawer
import com.example.mistrig.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        setContentView(R.layout.activity_app)


        drawerLayout = findViewById(R.id.drawer_layout)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val menuButton: View = findViewById(R.id.menu)


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


        menuButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.sidebar_menu_drawer, SideBarMenuDrawer())
                .commit()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
