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
import com.tugfeivecek.hastanetakipsistemi.adapter.EmergencyOccuracyAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentEmergencyOccupancyBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.EmergencyStatuViewModel

class EmergencyOccupancyFragment : Fragment() {
    private lateinit var binding: FragmentEmergencyOccupancyBinding
    private var adapter = EmergencyOccuracyAdapter(arrayListOf())
    private lateinit var viewModelEmergencyStatu: EmergencyStatuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmergencyOccupancyBinding.inflate(layoutInflater)
        binding.toolbarEmergencyOccupancy.setNavigationIcon(R.drawable.back)
        binding.toolbarEmergencyOccupancy.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelEmergencyStatu =
            ViewModelProviders.of(this).get(EmergencyStatuViewModel::class.java)
        viewModelEmergencyStatu.refreshEmergencyStatu()
        binding.rvEmergencyOccupancy.setHasFixedSize(true)
        binding.rvEmergencyOccupancy.layoutManager = LinearLayoutManager(context)
        binding.rvEmergencyOccupancy.adapter = adapter
        observeEmergencyStatu()

    }

    private fun observeEmergencyStatu() {
        viewModelEmergencyStatu.emergencyStatuList.observe(
            viewLifecycleOwner,
            Observer { emergency ->

                emergency.let {
                    adapter.updateEmergencyOccupancy(emergency)
                }
            })
    }
}
