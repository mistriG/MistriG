package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.PlumberServiceAdapter
import com.example.mistrig.DataClass.PlumberService

class PlumberServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plumber_service, container, false)

        // Generate dummy plumber service details
        val plumberServices = listOf(
            PlumberService("John's Plumbing", "Expert in residential plumbing repairs.", "+91 1234567890"),
            PlumberService("Rapid Response Plumbers", "24/7 emergency plumbing services.", "+91 9876543210"),
            PlumberService("AquaFlow Plumbing", "Specializing in leak detection and repair.", "+91 4561237890"),
            PlumberService("Pro Plumbers", "Professional plumbing services for commercial properties.", "+91 789456123"),
            PlumberService("Superior Plumbing", "Experienced in bathroom and kitchen installations.", "+91 123789456"),
            PlumberService("Eco Plumbers", "Eco-friendly plumbing solutions.", "555-6543"),
            PlumberService("Precision Plumbing", "High-quality plumbing services at affordable rates.", "+91 5135745685"),
            PlumberService("Reliable Plumbers", "Reliable and trustworthy plumbing services.", "+91 123789456"),
            PlumberService("WaterWorks Plumbing", "Expert in water heater repairs and installations.", "+91 123789456"),
            PlumberService("QuickFix Plumbers", "Fast and efficient plumbing services.", "+91 123789456")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PlumberServiceAdapter(plumberServices)

        return view
    }
}
