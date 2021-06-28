package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.MedicalDeviceAdapter
import com.tugfeivecek.hastanetakipsistemi.adapter.SemptomDetailAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonelMedikalDeviceListBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.CityViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DepartmentViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DeviceViewModel
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DistrictViewModel

class PersonelMedikalDeviceListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonelMedikalDeviceListBinding
    private var durum: Boolean = false
    private var adsoyad: String = ""
    private var hospitalId: String = ""
    private var departmentId: String = ""
    private var hospitalName: String = ""
    private val medikalDeviceAdapter = MedicalDeviceAdapter(arrayListOf())
    private lateinit var viewModelDepartments: DepartmentViewModel
    private lateinit var viewModelDevice: DeviceViewModel
    private var departments = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonelMedikalDeviceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp = getSharedPreferences("PersonalGirisBilgi", Context.MODE_PRIVATE)
        durum = sp.getBoolean("personalGiris", false)
        adsoyad = sp.getString("personalAdSoyad", "Ad Soyad Yok").toString()
        hospitalId = sp.getString("personelHospitalId", "Ad Soyad Yok").toString()

        viewModelDepartments = ViewModelProviders.of(this).get(DepartmentViewModel::class.java)
        viewModelDepartments.refreshDepartmentData(hospitalId)

        viewModelDevice = ViewModelProviders.of(this).get(DeviceViewModel::class.java)

        hospitalName = intent.getStringExtra("hospitalNameData").toString()

        binding.tvTitleHospitalName.text = hospitalName


        observeDepartmentData()
        //observeDeviceDataByDeviceid()


    }

    private fun observeDepartmentData() {

        viewModelDepartments.departments.observe(this, Observer { dep ->

            dep.let {
                binding.tvTitleHospitalName.text = dep[0].HospitalName.toString()

                for (departmentList in dep) {
                    departments.add(departmentList.DepartmentName!!)
                }

                binding.autoCompleteTextViewPart.setAdapter(
                    ArrayAdapter(
                        this, R.layout.support_simple_spinner_dropdown_item, departments
                    )
                )

                binding.autoCompleteTextViewPart.setOnItemClickListener { _, _, position, _ ->


                    departmentId = dep[position].departmentId.toString()
                    viewModelDevice.refreshDeviceDataByDepartmentId(departmentId)
                    binding.rvPartList.layoutManager = LinearLayoutManager(this)
                    binding.rvPartList.adapter = medikalDeviceAdapter
                    observeDeviceDataByDepartmentId()


                }

            }

        })
    }


    private fun observeDeviceDataByDepartmentId() {
        viewModelDevice.devicesDepartment.observe(this, Observer { dep ->
            dep.let {

                medikalDeviceAdapter.updateMedikalList(dep)

            }
        })
    }
}