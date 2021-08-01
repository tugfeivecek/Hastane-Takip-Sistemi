package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.RecordsAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentRecordsBinding
import com.tugfeivecek.hastanetakipsistemi.model.Records

class RecordsFragment : Fragment() {

    private lateinit var binding: FragmentRecordsBinding
    private lateinit var adapter: RecordsAdapter
    private lateinit var records: ArrayList<Records>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordsBinding.inflate(layoutInflater)
        binding.toolbarRecord.setNavigationIcon(R.drawable.back)
        binding.toolbarRecord.setNavigationOnClickListener {

            activity?.onBackPressed()
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecord.setHasFixedSize(true)
        binding.rvRecord.layoutManager= LinearLayoutManager(context)

        val u1= Records("Bezmialem Vakıf Üniversitesi Tıp Fakültesi Hastanesi","12/01/2020","Kırmızı Alan","15:00")
        val u2= Records("Medipol Üniversitesi Hastanesi","07/01/2021","Yeşil Alan","09:30")
        val u3= Records("Sağlık Bilimleri Üniversitesi Sarıyer Hamidiye Etfal Eğitim ve Araştırma Hastanesi","23/04/2021","Sarı Alan","12:45")



        //araylist olusturma
        records = ArrayList<Records>()
        records.add(u1)
        records.add(u2)
        records.add(u3)
        adapter= RecordsAdapter(records)
        binding.rvRecord.adapter = adapter

    }
}