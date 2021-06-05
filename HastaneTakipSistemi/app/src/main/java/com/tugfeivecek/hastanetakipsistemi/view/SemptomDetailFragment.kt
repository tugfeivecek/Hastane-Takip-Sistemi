package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.SemptomAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentSemptomDetailBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.SemptomViewModel

class SemptomDetailFragment : Fragment() {
    private lateinit var binding: FragmentSemptomDetailBinding
    private lateinit var viewModel: SemptomViewModel
    private val semptomAdapter = SemptomAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSemptomDetailBinding.inflate(layoutInflater)
        binding.toolbarHome.setNavigationIcon(R.drawable.back)
        binding.toolbarHome.setNavigationOnClickListener {

            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SemptomViewModel::class.java)
        // mutablelivbe datalara veri ekleniyo guncelleniyo
        viewModel.refreshSemptomData()
        binding.rvSemptom.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )
        binding.rvSemptom.adapter = semptomAdapter

        observerSemptomData()
    }

    private fun observerSemptomData() {
        viewModel.semptom.observe(viewLifecycleOwner, Observer { semptoms ->

            semptoms?.let {
                semptomAdapter.updateSemptomList(semptoms)
            }
        })
    }
}