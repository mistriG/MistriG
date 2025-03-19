package com.example.mistrig

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.DataClass.ElectronicRepairService
import com.example.mistrig.Adapters.ElectronicRepairServiceAdapter

class ElectronicRepairServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_electronic_repair_service, container, false)

        // Generate dummy electronic repair service details
        val electronicRepairServices = listOf(
            ElectronicRepairService("TechFix Solutions", "Expert in repairing all types of electronic devices.", "555-1234"),
            ElectronicRepairService("ElectroCare", "High-quality electronic repair services.", "555-5678"),
            ElectronicRepairService("Gadget Masters", "Specializing in mobile and tablet repairs.", "555-8765"),
            ElectronicRepairService("ProRepair Tech", "Professional repair services for computers and laptops.", "555-4321"),
            ElectronicRepairService("Elite Electronics", "Experienced in repairing audio and video equipment.", "555-9876"),
            ElectronicRepairService("QuickFix Electronics", "Fast and efficient electronic repair services.", "555-6543"),
            ElectronicRepairService("Reliable Repairs", "Trusted and reliable electronic repair solutions.", "555-3456"),
            ElectronicRepairService("Precision Tech Repair", "Detail-oriented and precise repair services.", "555-7890"),
            ElectronicRepairService("Affordable Tech Repairs", "Affordable and high-quality electronic repairs.", "555-3210"),
            ElectronicRepairService("Device Doctor", "Expert in diagnosing and repairing electronic issues.", "555-1111")
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ElectronicRepairServiceAdapter(electronicRepairServices)

        return view
    }
}
