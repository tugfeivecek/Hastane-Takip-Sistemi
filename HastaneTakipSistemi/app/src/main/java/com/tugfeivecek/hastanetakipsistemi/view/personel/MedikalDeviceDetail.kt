package com.tugfeivecek.hastanetakipsistemi.view.personel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tugfeivecek.hastanetakipsistemi.adapter.MedicalDeviceAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityMedikalDeviceDetailBinding
import com.tugfeivecek.hastanetakipsistemi.utils.downloadFromUrl
import com.tugfeivecek.hastanetakipsistemi.utils.placeHolderProgressBar
import com.tugfeivecek.hastanetakipsistemi.viewmodel.DeviceViewModel

class MedikalDeviceDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMedikalDeviceDetailBinding
    private lateinit var deviceId: String
    private lateinit var viewModelDevice: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedikalDeviceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelDevice = ViewModelProviders.of(this).get(DeviceViewModel::class.java)
        deviceId = intent.getStringExtra("deviceId").toString()
        viewModelDevice.refreshDeviceDataByDeviceId(deviceId)
        observeDeviceDataByDeviceid()

    }

    private fun observeDeviceDataByDeviceid() {
        viewModelDevice.devices.observe(this, Observer { dev ->

            for (data in dev) {
                binding.tvTitleHospitalNameDetail.text = data.name
                binding.ivDeviceDetail.downloadFromUrl(
                    "http://kutuphanemnerede.com/" +
                            data.imageUrl,
                    placeHolderProgressBar(this)
                )
                binding.tvDetailMarka.text = "Marka: " + data.brands
                binding.tvDetailTanim.text = "Açıklama: " + data.description
                binding.tvDetailFixtureNo.text = "Demirbaş Numarası: " + data.fixtureNo
            }

        })
    }
}