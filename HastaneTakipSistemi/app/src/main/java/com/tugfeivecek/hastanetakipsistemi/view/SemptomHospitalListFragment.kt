package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentSemptomHospitalListBinding


class SemptomHospitalListFragment : Fragment() {
    private lateinit var binding:FragmentSemptomHospitalListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSemptomHospitalListBinding.inflate(layoutInflater)
        binding.toolbarSemptomHospitalList.setNavigationIcon(R.drawable.back)
        binding.toolbarSemptomHospitalList.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }
}