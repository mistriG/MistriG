package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mistrig.R
import kotlin.jvm.java


class SideBarMenuDrawer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val view : View = inflater.inflate(R.layout.fragment_sidebar_menu_drawer, container, false)

        val msgBtn : ImageView = view.findViewById<ImageView>(R.id.btn_show_msg)

        val msgBoxIntent = Intent(view.context, MessageBox::class.java)

        msgBtn.setOnClickListener {
            startActivity(msgBoxIntent)
        }

        // settings
        val setting = view.findViewById<TextView>(R.id.settings)
        setting.setOnClickListener {
            val parentFragManager = parentFragmentManager.beginTransaction()
            parentFragManager.replace(R.id.fragment_container, SettingsFragment())
            parentFragManager.commit()
        }

        return view
    }

}