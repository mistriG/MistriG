package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.PaintingServiceAdapter
import com.example.mistrig.DataClass.PaintingService

class PaintingServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_painting_service, container, false)

        // Generate dummy painting service details
        val paintingServices = listOf(
            PaintingService("Master Painters", "Expert in residential and commercial painting.", "555-1234"),
            PaintingService("Pro Painting Services", "High-quality painting with a professional touch.", "555-5678"),
            PaintingService("Elite Painters", "Experienced in interior and exterior painting.", "555-8765"),
            PaintingService("Eco Painting Solutions", "Eco-friendly and sustainable painting services.", "555-4321"),
            PaintingService("QuickFix Painters", "Fast and efficient painting services.", "555-9876"),
            PaintingService("Reliable Painting", "Trusted and reliable painting solutions.", "555-6543"),
            PaintingService("Precision Painting", "Detail-oriented and precise painting services.", "555-3456"),
            PaintingService("Affordable Painters", "Affordable and high-quality painting.", "555-7890"),
            PaintingService("Professional Painters", "Professional painters for all your needs.", "555-3210"),
            PaintingService("ColorPerfect Painting", "Perfect color matching and painting services.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PaintingServiceAdapter(paintingServices)

        return view
    }
}
