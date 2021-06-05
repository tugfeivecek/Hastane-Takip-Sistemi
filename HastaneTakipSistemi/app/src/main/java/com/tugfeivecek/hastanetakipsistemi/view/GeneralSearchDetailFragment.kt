package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentGeneralSearchDetailBinding


class GeneralSearchDetailFragment : Fragment() {
    private lateinit var binding: FragmentGeneralSearchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeneralSearchDetailBinding.inflate(layoutInflater)
        binding.toolbarGeneralSearch.setNavigationIcon(R.drawable.back)
        binding.toolbarGeneralSearch.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
