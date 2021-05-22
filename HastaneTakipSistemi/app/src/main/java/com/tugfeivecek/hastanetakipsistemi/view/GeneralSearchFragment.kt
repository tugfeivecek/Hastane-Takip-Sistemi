package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentGeneralSearchBinding
import com.tugfeivecek.hastanetakipsistemi.model.City
import com.tugfeivecek.hastanetakipsistemi.viewmodel.CityViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DistrictViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.HospitalListViewModel


class GeneralSearchFragment : Fragment() {
    private lateinit var binding: FragmentGeneralSearchBinding
    private lateinit var viewModelCity: CityViewModel
    private var cityList = ArrayList<String>()
    private lateinit var viewModelDistrict: DistrictViewModel
    private lateinit var viewModelHospitalList: HospitalListViewModel
    private var districtList = ArrayList<String>()
    private var hospitalList = ArrayList<String>()
    private var ilId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneralSearchBinding.inflate(layoutInflater)
        binding.toolbarGeneralSearch.setNavigationIcon(R.drawable.back)
        binding.toolbarGeneralSearch.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCity = ViewModelProviders.of(this).get(CityViewModel::class.java)
        viewModelCity.refreshCityData()

        viewModelDistrict = ViewModelProviders.of(this).get(DistrictViewModel::class.java)
        viewModelDistrict.refreshDistrictData(ilId)

        viewModelHospitalList = ViewModelProviders.of(this).get(HospitalListViewModel::class.java)
        viewModelHospitalList.refreshHospitalListData("20")

        binding.btnSearch.setOnClickListener {
            val action =
                GeneralSearchFragmentDirections.actionGeneralSearchFragmentToGeneralSearchDetailFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }
        observeCityData()
        observeDistrictData()
        observeHospitalListData()
        binding.etSelectDistrict.isEnabled = false
        binding.etSelectHospital.isEnabled = false


    }

    private fun observeCityData() {
        viewModelCity.cityData.observe(viewLifecycleOwner, Observer { cities ->
            cities?.let {
                for (data in cities) {
                    cityList.add(data.isim!!)
                }
                binding.etSelectCity.setAdapter(
                    ArrayAdapter(
                        requireContext(), R.layout.support_simple_spinner_dropdown_item, cityList
                    )
                )


                binding.etSelectCity.setOnItemClickListener { parent, view, position, id ->

                    ilId = cities[position].ilId.toString()

                    Toast.makeText(
                        context,
                        "Selected item id : $ilId",
                        Toast.LENGTH_LONG
                    ).show()
                }
                observeDistrictData()

            }
        })
    }

    private fun observeDistrictData() {
        viewModelDistrict.districtData.observe(viewLifecycleOwner, Observer { district ->
            district?.let {
                for (data in district) {
                    districtList.add(
                        data.ilceIsim!!
                    )
                }
                binding.etSelectDistrict.setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        districtList
                    )
                )
            }
        })
    }

    private fun observeHospitalListData() {
        viewModelHospitalList.hospitalListData.observe(
            viewLifecycleOwner,
            Observer { hospitalListData ->
                hospitalListData?.let {
                    for (data in hospitalListData) {
                        hospitalList.add(data.hospitalName!!)
                    }


                    binding.etSelectHospital.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            hospitalList
                        )
                    )
                }
            })
    }
}

