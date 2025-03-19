package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.WelderServiceAdapter
import com.example.mistrig.DataClass.WelderService

class WelderServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welder_service, container, false)

        // Generate dummy welder service details
        val welderServices = listOf(
            WelderService("Precision Welding", "Expert in precise and clean welding jobs.", "555-1234"),
            WelderService("Ironclad Welders", "Specializing in structural welding and fabrication.", "555-5678"),
            WelderService("ProWeld Solutions", "Professional welding services for all materials.", "555-8765"),
            WelderService("Elite Welders", "Experienced in custom and intricate welding projects.", "555-4321"),
            WelderService("QuickFix Welders", "Fast and reliable welding services.", "555-9876"),
            WelderService("Eco Weld", "Eco-friendly and sustainable welding solutions.", "555-6543"),
            WelderService("Reliable Welding", "Trusted and dependable welding services.", "555-3456"),
            WelderService("Heavy Metal Welding", "Expert in heavy machinery and industrial welding.", "555-7890"),
            WelderService("Precision Arc Welding", "High-quality arc welding services.", "555-3210"),
            WelderService("WeldMasters", "Mastering the art of welding for all your needs.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WelderServiceAdapter(welderServices)

        return view
    }
}
