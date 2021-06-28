package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonelDeviceSelectionBinding
import com.tugfeivecek.hastanetakipsistemi.view.ChoiceActivity

class PersonelDeviceSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonelDeviceSelectionBinding
    private var durum: Boolean = false
    private var adsoyad: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonelDeviceSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp = getSharedPreferences("PersonalGirisBilgi", Context.MODE_PRIVATE)
        durum = sp.getBoolean("personalGiris", false)
        adsoyad = sp.getString("personalAdSoyad", "Ad Soyad Yok").toString()

        binding.textViewActivePersonal.text = "Aktif Kişi: " + adsoyad

        binding.textViewLogout.setOnClickListener {
            val sp = getSharedPreferences("PersonalGirisBilgi", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.remove("personalGiris")
            editor.remove("personalAdSoyad")
            editor.apply()
            Toast.makeText(this, "Çıkış işlemi başarılı", Toast.LENGTH_LONG).show()

            val intent =
                Intent(this, ChoiceActivity::class.java)
            startActivity(intent)
        }

        binding.imageRectangleKurum.setOnClickListener {

            val intent =
                Intent(this, PersonelMedikalDeviceListActivity::class.java)
            startActivity(intent)
        }

        binding.imageRectangleDigerkurum.setOnClickListener {
            val intent =
                Intent(this, PersonalOtherDevices::class.java)
            startActivity(intent)
        }
    }
}