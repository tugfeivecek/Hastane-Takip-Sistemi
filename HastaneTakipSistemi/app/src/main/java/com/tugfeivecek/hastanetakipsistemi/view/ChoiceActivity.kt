package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tugfeivecek.hastanetakipsistemi.MainActivity
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityChoiceBinding
import com.tugfeivecek.hastanetakipsistemi.view.personel.PersonalLoginActivity


class ChoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageUser.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.imageStaff.setOnClickListener {

            val intent = Intent(this, PersonalLoginActivity::class.java)
            startActivity(intent)
        }
    }


}