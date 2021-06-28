package com.tugfeivecek.hastanetakipsistemi.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.gms.location.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentMainBinding

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var drawable: Drawable

    private lateinit var locationManager: LocationManager
    private var checkLocation = 0
    private lateinit var fusedLocation: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater)

        val toggle = ActionBarDrawerToggle(activity, binding.drawerMain, binding.toolbarHome, 0, 0)

        binding.drawerMain.addDrawerListener(toggle)

        toggle.isDrawerIndicatorEnabled = false
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.menu, activity?.theme)!!
        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener {
            if (binding.drawerMain.isDrawerVisible(GravityCompat.START)) {
                binding.drawerMain.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerMain.openDrawer(GravityCompat.START)
            }
        }
        toggle.syncState()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerNear.isSaveEnabled = false
        binding.navigationView.setNavigationItemSelectedListener(this)

        val adapter = MyViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(NearLibraryMapFragment(), "En Yakın Hastane")
        adapter.addFragment(NearLibraryPharmacyFragment(), "En Yakın Eczane")
        adapter.notifyDataSetChanged()
        binding.viewPagerNear.adapter = adapter


        TabLayoutMediator(binding.tablayoutNear, binding.viewPagerNear) { tab, position ->
            // sirayla fragmentlara basligi yazicak
            tab.text = adapter.getPageTitle(position)
            binding.viewPagerNear.setCurrentItem(tab.position, true)

        }.attach()


        binding.imageButtonHospital.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToHospitalSelectionFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }

        binding.imageButtonEmergency.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToEmergencyOccupancyFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }

        binding.imageButtonAnaliz.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAnalysisFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }


        checkLocation = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        if (checkLocation != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_home, menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.navHospital -> {
                val action = MainFragmentDirections.actionMainFragmentToSearchFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            R.id.navNotice -> {
                val action = MainFragmentDirections.actionMainFragmentToNoticeFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            R.id.navNearby -> {
                val action = MainFragmentDirections.actionMainFragmentToAroundHospitalFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            R.id.navKayitlarim -> {
                val action = MainFragmentDirections.actionMainFragmentToRecordsFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            R.id.navAbout -> {
                val action = MainFragmentDirections.actionMainFragmentToAboutFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }

            R.id.navMain -> {
                val action = MainFragmentDirections.actionMainFragmentSelf()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            R.id.navCikis -> {
                val sp = requireActivity().getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.remove("girisDurum")
                editor.remove("adsoyad")
                editor.apply()
                Toast.makeText(context, "Çıkış işlemi başarılı", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.mainFragment)
            }
        }
        return true
    }

    class MyViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(manager, lifecycle) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        // sirayla fragmentlari belirlememiz lazım poisitionşa indekslere eris
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

}
