package com.tugfeivecek.hastanetakipsistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration
    private var permissionControl = 0
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //bottoönavigation gecisleri
        //bottom menudeki labelı direk toolbar olarak yazdırma
        /*val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment,R.id.favoriteFragment,R.id.reservationFragment,R.id.profileFragment,R.id.menuFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)*/
        //navigation upp button
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomBarMain)
        val navController = findNavController(R.id.fragmentNavHost)
        bottomNavigationView.setupWithNavController(navController)

    }
}