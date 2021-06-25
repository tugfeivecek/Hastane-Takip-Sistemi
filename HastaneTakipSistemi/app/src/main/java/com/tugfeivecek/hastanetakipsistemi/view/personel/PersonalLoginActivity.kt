package com.tugfeivecek.hastanetakipsistemi.view.personel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityPersonalLoginBinding

class PersonalLoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPersonalLoginBinding
    var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPersonalLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment: PersonalLoginFragment = PersonalLoginFragment.newInstance()

        // check is important to prevent activity from attaching the fragment if already its attached
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, "fragment_login")
                .commit()
        }
    }
}