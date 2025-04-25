package com.example.mistrig.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mistrig.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ServiceMap : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_searvice_map, container, false)

        // Useing childFragmentManager because the map fragment is nested in this fragment's layout
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        val text = view.findViewById<TextView>(R.id.service_map_loading_text)
        text.text = arguments?.getString("name") ?: "Searching for service man... please wait"

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // default marker in Delhi
        val delhi = LatLng(28.6139, 77.2090)
        mMap.addMarker(
            MarkerOptions()
                .position(delhi)
                .title("Marker in Delhi")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(delhi, 12f))
    }
}
