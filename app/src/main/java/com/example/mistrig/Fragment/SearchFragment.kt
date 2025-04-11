package com.example.mistrig.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.R
import com.example.mistrig.Adapters.SearchAdapter
import com.example.mistrig.CarpentryServiceFragment
import com.example.mistrig.DataClass.SearchResult
import com.example.mistrig.ElectricianServiceFragment
import com.example.mistrig.ElectronicRepairServiceFragment
import com.example.mistrig.LaundryServiceFragment
import com.example.mistrig.PaintingServiceFragment
import com.example.mistrig.PlumberServiceFragment
import com.example.mistrig.WelderServiceFragment
import java.util.*

class SearchFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var voiceSearchIcon: ImageView
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchEditText = view.findViewById<EditText>(R.id.search_edit_text)
        // voice search on search icon
//        voiceSearchIcon = view.findViewById(R.id.search_result_img)
        searchResultsRecyclerView = view.findViewById(R.id.search_results_recycler_view)

        searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        searchAdapter = SearchAdapter(generateDummySearchResults()) { searchResult ->
            navigateToServiceFragment(searchResult.title)
        }
        searchResultsRecyclerView.adapter = searchAdapter

        setupSearchFunctionality()
//        setupVoiceSearch()

        return view
    }

    private fun setupSearchFunctionality() {
        // Listen for text changes and filter the search results accordingly
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the search results as the text changes
                val filteredResults = generateDummySearchResults().filter {
                    it.title.contains(s.toString(), ignoreCase = true)
                }
                searchAdapter.updateResults(filteredResults)
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed after text changes
            }
        })
    }

    private fun setupVoiceSearch() {
        voiceSearchIcon.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to search")

            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                searchEditText.setText(result[0])
            }
        }
    }

    private fun generateDummySearchResults(): List<SearchResult> {
        return listOf(
            SearchResult(R.drawable.img_plumber_service, "Plumbing Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_gardener_service, "Gardener Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_handyman_service, "Handyman Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_electrician_service, "Electrician Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_carpenter, "Carpentry Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_painting_service, "Painting Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_electronic_service, "Electronic Repair", "email@fakedomain.net"),
            SearchResult(R.drawable.img_interior_designer, "Interior Designer Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_welder, "Welder Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_tile_installer_service, "Tile Installer Services", "email@fakedomain.net"),
            SearchResult(R.drawable.img_paint_service, "Painter Services", "email@fakedomain.net")
        )
    }

    private fun navigateToServiceFragment(serviceTitle: String) {
        val fragment = when (serviceTitle) {
            "Plumbing Services" ->{
                findNavController().navigate(R.id.action_navigation_search_to_plumberServiceFragment)
            }
            "Electrician Services" ->{
                findNavController().navigate(R.id.action_navigation_search_to_electricianServiceFragment)
            }
            "Carpentry Services" -> {
                findNavController().navigate(R.id.action_navigation_search_to_carpentryServiceFragment)
            }
            "Painting Services" -> {
                findNavController().navigate(R.id.action_navigation_search_to_paintingServiceFragment)
            }
            "Electronic Repair" -> {
                findNavController().navigate(R.id.action_navigation_search_to_electronicRepairServiceFragment)
            }
            "Laundry Services" -> {
                findNavController().navigate(R.id.action_navigation_search_to_laundryServiceFragment)
            }
            "Welder Services" -> {
                findNavController().navigate(R.id.action_navigation_search_to_welderServiceFragment)
            }
            else -> {
                null
            }
        }
    }

}
