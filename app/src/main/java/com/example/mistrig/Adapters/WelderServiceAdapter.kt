package com.example.mistrig.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mistrig.R
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.DataClass.WelderService

class WelderServiceAdapter(private val services: List<WelderService>) :
    RecyclerView.Adapter<WelderServiceAdapter.WelderServiceViewHolder>() {

    class WelderServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.service_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.service_description)
        val contactTextView: TextView = itemView.findViewById(R.id.service_contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelderServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_welder_service, parent, false)
        return WelderServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WelderServiceViewHolder, position: Int) {
        val service = services[position]
        holder.nameTextView.text = service.name
        holder.descriptionTextView.text = service.description
        holder.contactTextView.text = service.contact
    }

    override fun getItemCount() = services.size
}
