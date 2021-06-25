package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentHospitalSelectionBinding


class HospitalSelectionFragment : Fragment() {
    private lateinit var binding: FragmentHospitalSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalSelectionBinding.inflate(layoutInflater)

        binding.toolbarHome.setNavigationIcon(R.drawable.back)
        binding.toolbarHome.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imageButtonGeneral.setOnClickListener {
            val action =
                HospitalSelectionFragmentDirections.actionHospitalSelectionFragmentToGeneralSearchFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }
        binding.imageButtonNearby.setOnClickListener {
            val action =
                HospitalSelectionFragmentDirections.actionHospitalSelectionFragmentToNearbyHospitalFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }
        binding.imageButtonSemptom.setOnClickListener {
            val action =
                HospitalSelectionFragmentDirections.actionHospitalSelectionFragmentToSemptomDetailFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}