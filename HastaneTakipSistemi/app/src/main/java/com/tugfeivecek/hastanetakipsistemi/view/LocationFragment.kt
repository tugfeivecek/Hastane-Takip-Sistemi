package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentLocationBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.LocationViewModel

class LocationFragment : Fragment() {
    private lateinit var viewModelLocation: LocationViewModel
    private lateinit var binding: FragmentLocationBinding
    private var nameList = ArrayList<String>()
    private val listEmergency = ArrayList<Fragment>()
    private var hospitalId = ""

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

        observeLocationData()
    }

    class MyViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(manager, lifecycle) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        // sirayla fragmentlari belirlememiz lazım poisitionşa indekslere eris
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
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
                binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
                    hospitalId = locations[position].hospitalId.toString()
                    val adapter = MyViewPagerAdapter(childFragmentManager, lifecycle)
                    adapter.addFragment(LocationGeneralEmergencyFragment(hospitalId), "Genel Acil")
                    adapter.addFragment(LocationGeneralEmergencyFragment(hospitalId), "Çocuk Acil")

                    adapter.notifyDataSetChanged()
                    binding.viewPagerHospital.adapter = adapter
                    observeLocationData()


                    TabLayoutMediator(
                        binding.tablayoutHospital,
                        binding.viewPagerHospital
                    ) { tab, position ->
                        // sirayla fragmentlara basligi yazicak
                        tab.text = adapter.getPageTitle(position)
                        binding.viewPagerHospital.setCurrentItem(tab.position, true)
                    }.attach()
                }
            }
        })
    }
}