package com.example.mistrig.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mistrig.R
import kotlin.jvm.java


class sidebar_menu_drawer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_sidebar_menu_drawer, container, false)

        val msgBtn : ImageView = view.findViewById<ImageView>(R.id.btn_show_msg)

        val msgBoxIntent = Intent(view.context, MessageBox::class.java)

        msgBtn.setOnClickListener {
            startActivity(msgBoxIntent)
        }

        return view
    }

}