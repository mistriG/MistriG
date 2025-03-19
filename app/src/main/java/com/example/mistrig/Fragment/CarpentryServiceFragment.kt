package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.CarpentryServiceAdapter

class CarpentryServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_carpentry_service, container, false)

        // Generate dummy carpentry service details
        val carpentryServices = listOf(
            CarpentryService("Master Crafts Carpentry", "Expert in custom furniture and fittings.", "555-1234"),
            CarpentryService("WoodWorks", "Specializing in cabinetry and woodwork restoration.", "555-5678"),
            CarpentryService("Precision Carpentry", "High-quality carpentry services for residential properties.", "555-8765"),
            CarpentryService("ProCarpenter", "Professional carpentry services for commercial properties.", "555-4321"),
            CarpentryService("Elite Carpentry", "Experienced in all types of woodwork installations.", "555-9876"),
            CarpentryService("Eco Carpenters", "Eco-friendly and sustainable carpentry solutions.", "555-6543"),
            CarpentryService("Fine Finish Carpentry", "Expert in finishing touches and detailed woodwork.", "555-3456"),
            CarpentryService("Reliable Carpenters", "Reliable and trustworthy carpentry services.", "555-7890"),
            CarpentryService("Quality Craft Carpentry", "Crafting quality custom solutions for your home.", "555-3210"),
            CarpentryService("QuickFix Carpenters", "Fast and efficient carpentry services.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CarpentryServiceAdapter(carpentryServices)

        return view
    }
}
