package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentLocationGeneralEmergencyBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LocationGeneralEmergencyViewModel


class LocationGeneralEmergencyFragment(var hastaneId: String) : Fragment() {
    private lateinit var binding: FragmentLocationGeneralEmergencyBinding
    private lateinit var viewModelLocation: LocationGeneralEmergencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationGeneralEmergencyBinding.inflate(layoutInflater)
        binding.tvGreenWait
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelLocation =
            ViewModelProviders.of(this).get(LocationGeneralEmergencyViewModel::class.java)
        viewModelLocation.refreshgeneralEmergencyData(hastaneId)


        observeLocationData()
    }

    private fun observeLocationData() {
        viewModelLocation.generalEmergencyData.observe(viewLifecycleOwner, Observer { locations ->
            locations?.let {
                for (data in locations) {

                    binding.tvGreenWait.text = "Bekleyen: ${data.greenArea}"
                    binding.tvRedWait.text = "Bekleyen: ${data.redArea}"
                    binding.tvYellowWait.text = "Bekleyen: ${data.yellowArea}"

                }

            }
        })
    }
}