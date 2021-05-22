package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.LocationGeneralEmergencyAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentLocationGeneralEmergencyBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LocationGeneralEmergencyViewModel


class LocationGeneralEmergencyFragment : Fragment() {
    private lateinit var binding:FragmentLocationGeneralEmergencyBinding
    private lateinit var viewModelGeneralEmergency: LocationGeneralEmergencyViewModel
    private val generalEmergencyAdapter = LocationGeneralEmergencyAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentLocationGeneralEmergencyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneralEmergency = ViewModelProviders.of(this).get(LocationGeneralEmergencyViewModel::class.java)
        viewModelGeneralEmergency.refreshgeneralEmergencyData()
        binding.rvGeneralEmergency.layoutManager = LinearLayoutManager(context)
        binding.rvGeneralEmergency.adapter = generalEmergencyAdapter
        observerGeneralEmergencyData()
    }
    private fun observerGeneralEmergencyData() {
        viewModelGeneralEmergency.generalEmergencyData.observe(
            viewLifecycleOwner,
            Observer { generalEmergencyData ->
                generalEmergencyData?.let {
                    generalEmergencyAdapter.updateGeneralEmergencyList(generalEmergencyData)

                }
            })
    }
    }