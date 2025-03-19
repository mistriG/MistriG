package com.example.mistrig.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.DataClass.SearchResult
import com.example.mistrig.R

class SearchAdapter(
    private var searchResults: List<SearchResult>,
    private val onItemClick: (SearchResult) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View, private val onItemClick: (SearchResult) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.result_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.result_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.result_description)

        fun bind(searchResult: SearchResult) {
            iconImageView.setImageResource(searchResult.imageResId)
            titleTextView.text = searchResult.title
            descriptionTextView.text = searchResult.description
            itemView.setOnClickListener { onItemClick(searchResult) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return SearchViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount() = searchResults.size

    fun updateResults(newResults: List<SearchResult>) {
        searchResults = newResults
        notifyDataSetChanged()
    }
}
