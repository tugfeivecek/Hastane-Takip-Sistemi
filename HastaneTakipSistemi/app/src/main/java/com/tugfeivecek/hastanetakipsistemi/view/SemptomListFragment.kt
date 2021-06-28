package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.SemptomDetailAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentSemptomListBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.SemptomListViewModel


class SemptomListFragment : Fragment() {

    private lateinit var viewModelSemptomList: SemptomListViewModel
    private lateinit var binding: FragmentSemptomListBinding
    private val semptomListAdapter = SemptomDetailAdapter(arrayListOf())
    private var typeId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSemptomListBinding.inflate(layoutInflater)
        binding.toolbarHome.setNavigationIcon(R.drawable.back)
        binding.toolbarHome.setNavigationOnClickListener {

            activity?.onBackPressed()
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewmodelproviders hangi model classla calısıyosun activiy mi fragment mı
        viewModelSemptomList = ViewModelProviders.of(this).get(SemptomListViewModel::class.java)
        // mutablelivbe datalara veri ekleniyo guncelleniyo

        arguments?.let {
            typeId = SemptomListFragmentArgs.fromBundle(it).typeId
        }

        viewModelSemptomList.refreshSemptomListData(typeId)
        binding.rvSemptomList.layoutManager = LinearLayoutManager(context)
        binding.rvSemptomList.adapter = semptomListAdapter

        observerSemptomLsitData()
    }

    private fun observerSemptomLsitData() {
        viewModelSemptomList.semptomsList.observe(viewLifecycleOwner, Observer { se ->
            se?.let {
                semptomListAdapter.updateSemptomDetailList(se)

            }
        })
    }
}