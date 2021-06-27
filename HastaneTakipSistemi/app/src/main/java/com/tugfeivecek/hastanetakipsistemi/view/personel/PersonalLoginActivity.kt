package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonalLoginBinding

class PersonalLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalLoginBinding
    var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent =
                Intent(this@PersonalLoginActivity, PersonelDeviceSelectionActivity::class.java)
            startActivity(intent)
        }

    }
}