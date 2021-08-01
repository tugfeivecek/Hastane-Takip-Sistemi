package com.tugfeivecek.hastanetakipsistemi

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var durum: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sp = getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
        durum = sp.getBoolean("girisDurum", false)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomBarMain)
        navController = findNavController(R.id.fragmentNavHost)
        bottomNavigationView.setupWithNavController(navController)

        Log.e("giris durum", durum.toString())
        if (durum == true) {
            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.profileFragment -> navController.navigate(R.id.userFragment)
                }

            }
        }


    }


}