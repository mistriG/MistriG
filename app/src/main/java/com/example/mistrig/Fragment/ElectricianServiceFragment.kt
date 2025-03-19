package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.ElectricianServiceAdapter
import com.example.mistrig.DataClass.ElectricianService

class ElectricianServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_electrician_service, container, false)

        // Generate dummy electrician service details
        val electricianServices = listOf(
            ElectricianService("Bright Light Electricians", "Expert in residential electrical repairs.", "555-1234"),
            ElectricianService("PowerHouse Electric", "24/7 emergency electrical services.", "555-5678"),
            ElectricianService("Spark Electrical Solutions", "Specializing in circuit and wiring repairs.", "555-8765"),
            ElectricianService("Pro Electricians", "Professional electrical services for commercial properties.", "555-4321"),
            ElectricianService("Elite Electric", "Experienced in lighting and fixture installations.", "555-9876"),
            ElectricianService("Eco Electricians", "Eco-friendly electrical solutions.", "555-6543"),
            ElectricianService("Precision Electric", "High-quality electrical services at affordable rates.", "555-3456"),
            ElectricianService("Reliable Electricians", "Reliable and trustworthy electrical services.", "555-7890"),
            ElectricianService("EnergyWave Electric", "Expert in energy-efficient solutions and installations.", "555-3210"),
            ElectricianService("QuickFix Electricians", "Fast and efficient electrical services.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ElectricianServiceAdapter(electricianServices)

        return view
    }
}
