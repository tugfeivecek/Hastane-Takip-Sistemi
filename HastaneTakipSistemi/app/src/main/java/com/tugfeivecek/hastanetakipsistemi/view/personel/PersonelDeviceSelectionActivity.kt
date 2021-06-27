package com.tugfeivecek.hastanetakipsistemi.view.personel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonelDeviceSelectionBinding

class PersonelDeviceSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonelDeviceSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonelDeviceSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}