package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentNearLibraryMapBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.NearHospitalViewModel

class NearLibraryMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapNear: GoogleMap
    private lateinit var binding: FragmentNearLibraryMapBinding
    private lateinit var viewModelNear: NearHospitalViewModel
    private lateinit var coordinatesNear: LatLng
    private lateinit var coordinatCurrent: LatLng
    private lateinit var nearLibraryShared: SharedPreferences
    private lateinit var getLatitude: String
    private lateinit var getLongitude: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNearLibraryMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelNear = ViewModelProviders.of(this).get(NearHospitalViewModel::class.java)
        // mutablelivbe datalara veri ekleniyo guncelleniyo

        nearLibraryShared =
            requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        viewModelNear.refreshNearMapData()
        createMapFragment()

        getLatitude = nearLibraryShared.getString("latitude", "0.0")!!
        getLongitude = nearLibraryShared.getString("longitude", "0.0")!!
        Toast.makeText(
            requireContext(),
            "Latitude :" + getLatitude + " Longitude: " + getLongitude, Toast.LENGTH_SHORT
        ).show()
    }

    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.nearMapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapNear = googleMap

        mapNear.uiSettings.isMyLocationButtonEnabled = true; // konumumu göster butonu aktif edildi
        mapNear.uiSettings.isCompassEnabled = true; // pusula butonu aktif edildi
        mapNear.uiSettings.isZoomControlsEnabled = true; // zoom butonları aktif edildi
        mapNear.uiSettings.isZoomGesturesEnabled = true
        mapNear.mapType = GoogleMap.MAP_TYPE_NORMAL; // hibrit görünümü set edildi
        mapNear.isTrafficEnabled = true;
        coordinatCurrent = LatLng(getLatitude.toDouble(), getLongitude.toDouble())

        var marker = MarkerOptions().position(coordinatCurrent).title("Şu anda buradasınız!")
        //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mappin))
        mapNear.addMarker(marker)
        mapNear.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinatCurrent, 14f))


    }
}