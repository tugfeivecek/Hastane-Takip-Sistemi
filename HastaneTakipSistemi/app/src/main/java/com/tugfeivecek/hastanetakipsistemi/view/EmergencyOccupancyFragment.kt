package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.EmergencyOccuracyAdapter
import com.tugfeivecek.hastanetakipsistemi.adapter.RecordsAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentEmergencyOccupancyBinding
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentRecordsBinding
import com.tugfeivecek.hastanetakipsistemi.model.EmergencyOccuracy
import com.tugfeivecek.hastanetakipsistemi.model.Records


class EmergencyOccupancyFragment : Fragment() {
    private lateinit var binding: FragmentEmergencyOccupancyBinding
    private lateinit var adapter: EmergencyOccuracyAdapter
    private lateinit var emergencyOccuracy: ArrayList<EmergencyOccuracy>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmergencyOccupancyBinding.inflate(layoutInflater)
        binding.toolbarEmergencyOccupancy.setNavigationIcon(R.drawable.back)
        binding.toolbarEmergencyOccupancy.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvEmergencyOccupancy.setHasFixedSize(true)
        binding.rvEmergencyOccupancy.layoutManager = LinearLayoutManager(context)

        val u1 = EmergencyOccuracy(
            "Bezmialem Vakıf Üniversitesi Tıp Fakültesi Hastanesi",
            "56",
            "120",
        )
        val u2 = EmergencyOccuracy("Medipol Üniversitesi Hastanesi", "35", "70")
        val u3 = EmergencyOccuracy(
            "Sağlık Bilimleri Üniversitesi Sarıyer Hamidiye Etfal Eğitim ve Araştırma Hastanesi",
            "12",
            "80",
        )


        //araylist olusturma
        emergencyOccuracy = ArrayList<EmergencyOccuracy>()
        emergencyOccuracy.add(u1)
        emergencyOccuracy.add(u2)
        emergencyOccuracy.add(u3)
        adapter = EmergencyOccuracyAdapter(emergencyOccuracy)
        binding.rvEmergencyOccupancy.adapter = adapter

    }
}
