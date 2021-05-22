package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentLocationBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LocationViewModel

class LocationFragment : Fragment() {
    private lateinit var viewModelLocation: LocationViewModel
    private lateinit var binding: FragmentLocationBinding
    private var nameList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationBinding.inflate(layoutInflater)
        binding.toolbarNearbyHospital.setNavigationIcon(R.drawable.back)
        binding.toolbarNearbyHospital.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelLocation = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        viewModelLocation.refreshLocationData()
        binding.viewPagerHospital.isSaveEnabled = false
        val adapter = MainFragment.MyViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(LocationGeneralEmergencyFragment(), "Genel Acil")
        adapter.addFragment(LocationGeneralEmergencyFragment(), "Çocuk Acil")
        adapter.notifyDataSetChanged()
        binding.viewPagerHospital.adapter = adapter
        observeLocationData()


        TabLayoutMediator(binding.tablayoutHospital, binding.viewPagerHospital) { tab, position ->
            // sirayla fragmentlara basligi yazicak
            tab.text = adapter.getPageTitle(position)
            binding.viewPagerHospital.setCurrentItem(tab.position, true)

        }.attach()

    }

    private fun observeLocationData() {
        viewModelLocation.locationData.observe(viewLifecycleOwner, Observer { locations ->
            locations?.let {
                for (data in locations) {
                    nameList.add(data.hospitalName!!)
                }
                binding.autoCompleteTextView.setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        nameList
                    )
                )
            }
        })
    }
}