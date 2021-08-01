package com.tugfeivecek.hastanetakipsistemi.view.personel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.adapter.MedicalDeviceAdapter
import com.tugfeivecek.hastanetakipsistemi.adapter.OtherDeviceAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonalOtherDevicesBinding
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DepartmentViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.HospitalListViewModel

class PersonalOtherDevices : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalOtherDevicesBinding
    private val otherDeviceAdapter = OtherDeviceAdapter(arrayListOf())
    private lateinit var viewModelHospitals: HospitalListViewModel
    private var hospitals = ArrayList<Hospital>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalOtherDevicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelHospitals = ViewModelProviders.of(this).get(HospitalListViewModel::class.java)
        viewModelHospitals.refreshAllHospitalListData()

        binding.rvPersonelOther.layoutManager = LinearLayoutManager(this)
        binding.rvPersonelOther.adapter = otherDeviceAdapter
        observerHospitalData()
    }

    private fun observerHospitalData() {
        viewModelHospitals.allHospital.observe(this, Observer { dep->
            otherDeviceAdapter.updateOtherList(dep)

        })
    }
}