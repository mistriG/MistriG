package com.example.mistrig.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.R
import com.example.mistrig.DataClass.PlumberService

class PlumberServiceAdapter(private val services: List<PlumberService>) :
    RecyclerView.Adapter<PlumberServiceAdapter.PlumberServiceViewHolder>() {

    class PlumberServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.service_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.service_description)
        val contactTextView: TextView = itemView.findViewById(R.id.service_contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlumberServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plumber_service, parent, false)
        return PlumberServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlumberServiceViewHolder, position: Int) {
        val service = services[position]
        holder.nameTextView.text = service.name
        holder.descriptionTextView.text = service.description
        holder.contactTextView.text = service.contact
    }

    override fun getItemCount() = services.size
}
