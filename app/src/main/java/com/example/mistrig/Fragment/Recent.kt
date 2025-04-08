package com.example.mistrig.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mistrig.R
import com.google.android.material.card.MaterialCardView

class Recent : Fragment() {

    private lateinit var requestBtn: MaterialCardView
    private lateinit var historyBtn: MaterialCardView

    // fragments initiated
    private var requestFragment = Request()
    private var historyFragment = History()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recent, container, false)

        requestBtn = view.findViewById(R.id.btn_recent_request)
        historyBtn = view.findViewById(R.id.btn_recent_history)



        // first time when fragment is created and by default request should be selected
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                add(R.id.fragment_recent_container, requestFragment, "request")
                add(R.id.fragment_recent_container, historyFragment, "history")
                hide(historyFragment) // this to hide history fragment
            }
        }

        else {
            val existingRequestFragment = childFragmentManager.findFragmentByTag("request")
            val existingHistoryFragment = childFragmentManager.findFragmentByTag("history")
            if (existingRequestFragment != null) requestFragment = existingRequestFragment as Request
            if (existingHistoryFragment != null) historyFragment = existingHistoryFragment as History
        }

        // on request button click, request fragment will be visible
        requestBtn.setOnClickListener {
            showRequestFragment()
        }
        // on request button click, request fragment will be visible
        historyBtn.setOnClickListener {
            showHistoryFragment()
        }

        // by default request si selected, request fragment will be visible
        showRequestFragment()
        return view
    }

    private fun showRequestFragment() {
        childFragmentManager.commit {
            show(requestFragment)
            hide(historyFragment)
        }
        updateButtonStyles(true)
    }

    private fun showHistoryFragment() {
        childFragmentManager.commit {
            show(historyFragment)
            hide(requestFragment)
        }
        updateButtonStyles(false)
    }

    // to update button styles
    private fun updateButtonStyles(isRequestSelected: Boolean) {
        if (isRequestSelected) {
            requestBtn.setCardBackgroundColor(resources.getColor(R.color.black, null))
            requestBtn.strokeWidth = 0
            historyBtn.setCardBackgroundColor(resources.getColor(R.color.white, null))
            historyBtn.strokeWidth = 2

            // to change inner text color of card view
            for(child in requestBtn.children){
                if(child is TextView){
                    child.setTextColor(resources.getColor(R.color.white, null))
                }
            }
            for(child in historyBtn.children){
                if(child is TextView){
                    child.setTextColor(resources.getColor(R.color.black, null))
                }
            }
        } else {
            requestBtn.setCardBackgroundColor(resources.getColor(R.color.white, null))
            requestBtn.strokeWidth = 2
            historyBtn.setCardBackgroundColor(resources.getColor(R.color.black, null))
            historyBtn.strokeWidth = 0

            // to change inner text color of card view
            for(child in requestBtn.children){
                if(child is TextView){
                    child.setTextColor(resources.getColor(R.color.black, null))
                }
            }
            for(child in historyBtn.children){
                if(child is TextView){
                    child.setTextColor(resources.getColor(R.color.white, null))
                }
            }
        }
    }
}