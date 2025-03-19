package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.LaundryServiceAdapter
import com.example.mistrig.DataClass.LaundryService

class LaundryServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_laundry_service, container, false)

        // Generate dummy laundry service details
        val laundryServices = listOf(
            LaundryService("Sparkle Laundry", "Expert in residential and commercial laundry services.", "555-1234"),
            LaundryService("QuickClean", "Fast and efficient laundry services.", "555-5678"),
            LaundryService("Eco Laundry", "Eco-friendly and sustainable laundry solutions.", "555-8765"),
            LaundryService("BrightWash", "Professional laundry services with a personal touch.", "555-4321"),
            LaundryService("Reliable Laundry", "Trusted and reliable laundry solutions.", "555-9876"),
            LaundryService("Precision Laundry", "High-quality laundry services at affordable rates.", "555-6543"),
            LaundryService("FreshWave", "Fresh and clean laundry services.", "555-3456"),
            LaundryService("Express Laundry", "Quick and reliable express laundry services.", "555-7890"),
            LaundryService("Premium Wash", "Premium laundry services for delicate fabrics.", "555-3210"),
            LaundryService("Laundry Experts", "Expert in handling all types of laundry.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = LaundryServiceAdapter(laundryServices)

        return view
    }
}
